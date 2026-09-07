package com.sea.pos.api.facade

/**
 * Ktor Client 使用示例
 *
 * 1. 简单 GET 请求
 * val result = ApiService.get<String>("https://api.example.com/data")
 *
 * 2. 带参数的 GET 请求
 * val result = ApiService.get<String>("https://api.example.com/search", mapOf("q" to "kotlin", "page" to 1))
 *
 * 3. POST JSON 请求
 * data class LoginRequest(val username: String, val password: String)
 * data class LoginResponse(val token: String, val userId: String)
 *
 * val result = ApiService.post<LoginRequest, LoginResponse>(
 *     "https://api.example.com/login",
 *     LoginRequest("user", "pass")
 * )
 * result.onSuccess { response -> println("Token: ${response.token}") }
 * result.onFailure { e -> println("Error: ${e.message}") }
 *
 * 4. 文件下载示例
 * suspend fun downloadFile(url: String, savePath: Path) {
 *     KtorClient.httpClient.get(url).body<ByteArray>().let {
 *         Files.writeByteArray(savePath, it)
 *     }
 * }
 *
 * 5. 自定义 HttpClient 配置
 * val customClient = HttpClient(CIO) {
 *     install(JsonFeature) {
 *         serializer = GsonSerializer()
 *     }
 *     engine {
 *         pipelining = true
 *     }
 *     install(HttpTimeout) {
 *         requestTimeoutMillis = 60_000
 *         connectTimeoutMillis = 30_000
 *         socketTimeoutMillis = 60_000
 *     }
 * }
 */
object ApiExamples
