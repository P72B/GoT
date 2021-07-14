package de.p72b.redandroid.got.repository.http

import okhttp3.Interceptor
import okhttp3.Response


const val HEADER_VERSION = "application/vnd.anapioficeandfire+json"
const val API_VERSION = "version=1"

class VersionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(HEADER_VERSION, API_VERSION)
            .build()

        return chain.proceed(request)
    }
}