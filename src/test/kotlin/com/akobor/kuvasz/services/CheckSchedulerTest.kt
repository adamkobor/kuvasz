package com.akobor.kuvasz.services

import com.akobor.kuvasz.DatabaseBehaviorSpec
import com.akobor.kuvasz.repositories.MonitorRepository
import com.akobor.kuvasz.tables.pojos.MonitorPojo
import com.akobor.kuvasz.utils.getBean
import com.akobor.kuvasz.utils.startTestApplication
import io.kotest.core.spec.autoClose
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CheckSchedulerTest : DatabaseBehaviorSpec() {
    init {
        service = autoClose(startTestApplication())
        val checkScheduler = service.getBean<CheckScheduler>()
        val monitorRepository = service.getBean<MonitorRepository>()

        given("the CheckScheduler service") {
            `when`("there is an enabled monitor in the database and initialize has been called") {
                val monitor = MonitorPojo()
                    .setId(99999)
                    .setName("testMonitor")
                    .setUptimeCheckInterval(30000)
                    .setUrl("http://irrelevant.com")
                    .setEnabled(true)
                monitorRepository.insert(monitor)

                checkScheduler.initialize()

                then("it should schedule the check for it") {
                    val expectedCheck = checkScheduler.getScheduledChecks().find { it.monitorId == monitor.id }
                    expectedCheck shouldNotBe null
                    expectedCheck!!.task.isCancelled shouldBe false
                    expectedCheck.task.isDone shouldBe false
                }
            }

            `when`("there is an enabled but unschedulable monitor in the database and initialize has been called") {
                val monitor = MonitorPojo()
                    .setId(88888)
                    .setName("testMonitor")
                    .setUptimeCheckInterval(0)
                    .setUrl("http://irrelevant.com")
                    .setEnabled(true)
                monitorRepository.insert(monitor)

                checkScheduler.initialize()

                then("it should not schedule the check for it") {
                    checkScheduler.getScheduledChecks().any { it.monitorId == monitor.id } shouldBe false
                }
            }

            `when`("there is a disabled monitor in the database and initialize has been called") {
                val monitor = MonitorPojo()
                    .setId(11111)
                    .setName("testMonitor")
                    .setUptimeCheckInterval(30000)
                    .setUrl("http://irrelevant.com")
                    .setEnabled(false)
                monitorRepository.insert(monitor)

                checkScheduler.initialize()

                then("it should not schedule the check for it") {
                    checkScheduler.getScheduledChecks().any { it.monitorId == monitor.id } shouldBe false
                }
            }
        }
    }
}