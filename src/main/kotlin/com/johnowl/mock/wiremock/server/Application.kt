package com.johnowl.mock.wiremock.server

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

fun main() {

    val wiremockServerPort = System.getenv("WIREMOCK_SERVER_PORT").toInt()

    val wireMockServer = WireMockServer(
        wireMockConfig()
        .extensions(AdminApiAuthorizer())
        .port(wiremockServerPort)
    )
    try {
        println("Starting wiremock on port $wiremockServerPort")
        wireMockServer.start()
        println("Wiremock started.")
    }catch (ex: Exception) {
        println("Stopping wiremock")
        println(ex)
        wireMockServer.stop()
    }
}