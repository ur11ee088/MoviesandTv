package com.innoventesmovistv.myapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.innoventesmovistv.myapplication.exceptions.NoInternetException
import com.innoventesmovistv.myapplication.utils.Constant.NO_INTERNET
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent = System.getProperty("http.agent")

        if (!isInternetAvailable()) {
          //  Log.d("ExURL", chain.request().url().toString())
            throw NoInternetException(NO_INTERNET, "Make Sure you have an Active Data Connection")
        }
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("deviceplatform", "android")
            .addHeader("User-Agent", userAgent!!)
            .addHeader("Cookie", "1234")
            .addHeader("connection", "keep-alive")
            .build()
        Log.e("usragent", "User:" + request)

        return chain.proceed(request)

        //return chain.proceed(chain.request())
    }


    @Suppress("DEPRECATION")
    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let { cm ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            } else {
                connectivityManager.activeNetworkInfo.also {
                    result = it != null && it.isConnected
                }
            }
        }
        return result
    }
}