package com.workshop.workshop.di.module

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.workshop.workshop.BuildConfig
import com.workshop.workshop.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     * create a new [ApiService] instance
     *
     * @param moshi provided by [provideRetrofit]
     *
     * @return the newly created [ApiService] instance
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Create a [retrofit2.Retrofit] and configure it.
     * @param okHttpClient provided by [provideOkHttp]
     * @param moshi provided by [provideMoshi]
     *
     * @return the newly crealted  [retrofit2.Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /**
     * Create a [Moshi] with and configure it
     *
     * @return the newly created [Moshi] instance
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    /**
     * Create a [okhttp3.OkHttpClient] and configure it
     *
     * @return the newly created [okhttp3.OkHttpClient] instance
     */
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {

        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                //Could add a HttpLoggingInterceptor (never add it in production code)
            }

            //Could add custom header here
        }.build()
    }
}