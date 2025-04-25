package com.prabirkundu.techexactlyassignment.Retrofit

import com.genius.geniusjobskotlin.Retrofit.ApiInterface
import com.prabirkundu.techexactlyassignment.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class Retrofit {

    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                //.addInterceptor(TimeoutInterceptor()) //TODO: Only use for timeout testing purpose
                .connectTimeout(20, TimeUnit.SECONDS) // Set connection timeout
                .readTimeout(30, TimeUnit.SECONDS)    // Set read timeout
                .build()



            retrofit(client)
        }

        private fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}

class TimeoutInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // Delay the response to simulate a timeout
        try {
            Thread.sleep(6000) // Sleep for 6 seconds
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return chain.proceed(chain.request())
    }
}
