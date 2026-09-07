package com.sea.pos.api.facade

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*

/**
 * Ktor HttpClient 单例
 * 使用 CIO 引擎，适合桌面应用
 */
object KtorClient {

    val httpClient: HttpClient by lazy {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    setLenient()
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            engine {
                requestTimeout = 60_000
            }
        }
        client
    }

    fun close() {
        httpClient.close()
    }

}