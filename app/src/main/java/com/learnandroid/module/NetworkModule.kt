package com.learnandroid.module

import com.learnandroid.BuildConfig
import com.learnandroid.PreferenceUtil
import com.learnandroid.rest.ApiService
import com.learnandroid.util.AppConstants
import com.learnandroid.util.logError
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        provideOkHttpClient()
    }

    single {
        provideRetrofit(get())
    }

    single {
        provideApiService(get())
    }

}

fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(getHeaderInterceptor())
        .addInterceptor(getLoggingInterceptor())
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().callFactory(okHttpClient)
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(getConverterFactory())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

private fun getLoggingInterceptor(): Interceptor {

    return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            logError("api", message)
        }

    }).apply {
        level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
    }
}

private fun getHeaderInterceptor(): Interceptor {

    return object : Interceptor, KoinComponent {

        val pref by inject<PreferenceUtil>()

        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request().newBuilder()
                .header("auth_token", pref.getValueString(PreferenceUtil.ACCESS_TOKEN))
                .header("msisdn", pref.getValueString(PreferenceUtil.MSISDN))
                .header("deviceId", "")
                .header("appId", "")
                .header("appid", "")
                .header("operator", "")
                .header("Accept", "")
                .header("Content-Type", "")
                .header("channel", "")
                .build()

            return chain.proceed(request)
        }
    }

}

private fun getConverterFactory(): Converter.Factory {
    return GsonConverterFactory.create()
}
