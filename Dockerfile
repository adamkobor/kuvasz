FROM oracle/graalvm-ce:20.3.0-java11 as graalvm
ARG VERSION=""
RUN gu install native-image

COPY . /home/app/kuvasz
WORKDIR /home/app/kuvasz

RUN native-image --no-server --verbose -cp build/libs/kuvasz-${VERSION}-all.jar \
--initialize-at-build-time=com.sun.mail.util.LineInputStream --report-unsupported-elements-at-runtime

FROM frolvlad/alpine-glibc:alpine-3.12_glibc-2.31
RUN apk --no-cache add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/kuvasz/kuvasz /app/kuvasz
ENTRYPOINT ["/app/kuvasz", "-Xms64M ", "-Xmx128m", "-Dio.netty.allocator.maxOrder=8"]
