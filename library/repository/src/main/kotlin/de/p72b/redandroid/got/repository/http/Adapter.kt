package de.p72b.redandroid.got.repository.http

import com.google.gson.GsonBuilder
import de.p72b.redandroid.got.repository.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val iceAndFireApi: IceAndFireApi by lazy {
    val client = OkHttpClient.Builder()
    val gson = GsonBuilder()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ICE_AND_FIRE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client.build())
    retrofit.build().create(IceAndFireApi::class.java)
}