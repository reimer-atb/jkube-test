package org.example.jkubetest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.router
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
            val hostName = getLocalHost().hostAddress
            ok().body(fromValue("Hello from Container - HostName: $hostName"))
        }
    }
}
