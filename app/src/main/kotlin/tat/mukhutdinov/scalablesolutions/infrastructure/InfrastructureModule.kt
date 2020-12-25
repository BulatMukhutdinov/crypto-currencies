package tat.mukhutdinov.scalablesolutions.infrastructure

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tat.mukhutdinov.android.structure.Dispatchers
import tat.mukhutdinov.scalablesolutions.infrastructure.util.AppDispatchers

object InfrastructureModule {

    val module = module {

        single<Dispatchers> {
            AppDispatchers()
        }

        single {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://data.messari.io/")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}