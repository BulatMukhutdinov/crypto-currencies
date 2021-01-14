package tat.mukhutdinov.cryptostock.infrastructure.interceptor

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import tat.mukhutdinov.cryptostock.infrastructure.structure.gateway.dto.BaseDto
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class ErrorParserInterceptor @Inject constructor(private val gson: Gson) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())

        if (originalResponse.isSuccessful) {
            return originalResponse
        }

        originalResponse.body?.let { responseBody ->
            val contentLength = responseBody.contentLength()
            val source = responseBody.source().apply { request(Long.MAX_VALUE) }
            var buffer = source.buffer

            if ("gzip".equals(originalResponse.headers["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }

            val contentType = responseBody.contentType()
            val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8

            if (contentLength != 0L) {
                val stringBody = buffer.clone().readString(charset)

                try {
                    val baseDto = gson.fromJson(stringBody, BaseDto::class.java)

                    if (baseDto.status?.isSuccessful == false) {
                        return errorResponse(originalResponse, chain, baseDto.status.errorCode ?: 400, baseDto.status.errorMessage ?: "")
                    }
                } catch (throwable: Throwable) {
                    return originalResponse
                }
            }
        }

        return originalResponse
    }

    private fun errorResponse(response: Response, chain: Interceptor.Chain, code: Int, message: String) =
        response.newBuilder()
            .code(code)
            .message(message)
            .request(chain.request())
            .build()
}