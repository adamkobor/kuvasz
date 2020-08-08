package com.kuvaszuptime.kuvasz.services

import arrow.core.Option
import arrow.core.toOption
import com.kuvaszuptime.kuvasz.models.MonitorNotFoundError
import com.kuvaszuptime.kuvasz.models.dto.MonitorCreateDto
import com.kuvaszuptime.kuvasz.models.dto.MonitorDetailsDto
import com.kuvaszuptime.kuvasz.models.dto.MonitorUpdateDto
import com.kuvaszuptime.kuvasz.repositories.MonitorRepository
import com.kuvaszuptime.kuvasz.tables.pojos.MonitorPojo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitorCrudService @Inject constructor(
    private val monitorRepository: MonitorRepository,
    private val checkScheduler: CheckScheduler
) {

    fun getMonitorDetails(monitorId: Int): Option<MonitorDetailsDto> = monitorRepository.getMonitorDetails(monitorId)

    fun getMonitorDetails(enabledOnly: Boolean): List<MonitorDetailsDto> =
        monitorRepository.getMonitorDetails(enabledOnly)

    fun createMonitor(monitorCreateDto: MonitorCreateDto): MonitorPojo {
        val monitorPojo =
            MonitorPojo()
                .setName(monitorCreateDto.name)
                .setUrl(monitorCreateDto.url)
                .setEnabled(monitorCreateDto.enabled)
                .setUptimeCheckInterval(monitorCreateDto.uptimeCheckInterval)

        return monitorRepository.returningInsert(monitorPojo).fold(
            { persistenceError -> throw persistenceError },
            { insertedMonitor ->
                checkScheduler.createChecksForMonitor(insertedMonitor).fold(
                    { schedulingError ->
                        monitorRepository.deleteById(insertedMonitor.id)
                        throw schedulingError
                    },
                    { insertedMonitor }
                )
            }
        )
    }

    fun deleteMonitorById(monitorId: Int) = monitorRepository.findById(monitorId).toOption().fold(
        { throw MonitorNotFoundError(monitorId) },
        { monitorPojo ->
            monitorRepository.deleteById(monitorPojo.id)
            checkScheduler.removeChecksOfMonitor(monitorPojo)
        }
    )

    fun updateMonitor(monitorId: Int, monitorUpdateDto: MonitorUpdateDto): MonitorPojo =
        monitorRepository.findById(monitorId).toOption().fold(
            { throw MonitorNotFoundError(monitorId) },
            { existingMonitor ->
                val updatedMonitor = MonitorPojo().apply {
                    id = existingMonitor.id
                    name = monitorUpdateDto.name ?: existingMonitor.name
                    url = monitorUpdateDto.url ?: existingMonitor.url
                    uptimeCheckInterval = monitorUpdateDto.uptimeCheckInterval ?: existingMonitor.uptimeCheckInterval
                    enabled = monitorUpdateDto.enabled ?: existingMonitor.enabled
                }

                updatedMonitor.saveAndReschedule(existingMonitor)
            }
        )

    private fun MonitorPojo.saveAndReschedule(existingMonitor: MonitorPojo): MonitorPojo =
        monitorRepository.returningUpdate(this).fold(
            { persistenceError -> throw persistenceError },
            { updatedMonitor ->
                if (updatedMonitor.enabled) {
                    checkScheduler.updateChecksForMonitor(existingMonitor, updatedMonitor).fold(
                        { schedulingError -> throw schedulingError },
                        { updatedMonitor }
                    )
                } else {
                    checkScheduler.removeChecksOfMonitor(existingMonitor)
                    updatedMonitor
                }
            }
        )
}
