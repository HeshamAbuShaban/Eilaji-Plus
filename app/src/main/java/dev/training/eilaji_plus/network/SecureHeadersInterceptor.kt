package dev.training.eilaji_plus.network

import android.util.Log
import dev.training.eilaji_plus.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class SecureHeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val realToken = TokenManager.getToken() ?: "".also {
            Log.e("SecureHeadersInterceptor", "Token is null")
        }

        val request = original.newBuilder()
            .header("Authorization", "Bearer $realToken")
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(request)
    }
}