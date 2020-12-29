package tat.mukhutdinov.scalablesolutions.infrastructure

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tat.mukhutdinov.scalablesolutions.infrastructure.util.ErrorParserInterceptor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class InfrastructureModule {

    companion object {

        @Provides
        @Singleton
        fun provideOkHttpClient(errorParserInterceptor: ErrorParserInterceptor): OkHttpClient {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addNetworkInterceptor(errorParserInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://data.messari.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        @Provides
        @Singleton
        fun provideGson(): Gson =
            Gson()
    }
}