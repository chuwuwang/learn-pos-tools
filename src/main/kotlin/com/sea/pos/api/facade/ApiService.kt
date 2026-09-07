package com.sea.pos.api.facade

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

open class ApiService {

    val client = KtorClient.httpClient

    suspend inline fun <reified T> get(url: String, params: Map<String, Any> = emptyMap()): Result<T> {
        return runCatching {
            val response = client.get(url) {
                params.forEach { (key, value) -> parameter(key, value) }
            }
            response.body<T>()
        }
    }

    suspend inline fun <reified T, reified R> post(url: String, body: T): Result<R> {
        return runCatching {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.body<R>()
        }
    }

    suspend inline fun <reified T> postForm(url: String, params: Map<String, Any>): Result<T> {
        return runCatching {
            val response = client.post(url) {
                params.forEach { (key, value) -> parameter(key, value) }
            }
            response.body<T>()
        }
    }

}