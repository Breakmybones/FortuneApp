package com.example.network

import dagger.Module
import dagger.Provides
import okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


@Module
class NetworkModule {


    @Provides
    fun provideHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()



    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonFactory)
            .baseUrl("http://172.20.10.3:8080")
            .build()
        return retrofit
    }

//    @Provides
//    fun provideOkhttpRequest(
//        httpClient: OkHttpClient,
//        gsonFactory: GsonConverterFactory,
//    ): String {
//        val request = Request.Builder()
//            .url("https://192.168.1.22:8080")
//            .build()
//        val call = httpClient.newCall(request)
//        val response = call.execute()
//        return response.body?.string() ?: ""
//    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}