package org.example.jkubetest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono.fromCallable
import reactor.core.scheduler.Schedulers
import java.net.InetAddress.getLocalHost

@SpringBootApplication
class JKubeTestApplication

fun main(args: Array<String>) {
    runApplication<JKubeTestApplication>(*args)
}

@Configuration
class RouterConfiguration {

    @Bean
    fun router() = router {
        GET("/") {
            fromCallable { getLocalHost() }
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap {
                    ok().bodyValue("Hello from Container - HostName: ${it.hostAddress}")
                }
        }
    }
}
