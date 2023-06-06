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
            .baseUrl("http://192.168.226.18:8080")
            .build()
        return retrofit
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}