package de.p72b.redandroid.got.repository.http

import android.app.Application
import com.google.gson.GsonBuilder
import de.p72b.redandroid.got.repository.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CACHE_SIZE = 1024 * 1024 * 2L

fun createHttpCache(application: Application): Cache {
    return Cache(application.applicationContext.cacheDir, CACHE_SIZE)
}

fun createAdapter(cache: Cache): IceAndFireApi {
    val client = OkHttpClient.Builder().cache(cache)
    val gson = GsonBuilder()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ICE_AND_FIRE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client.build())
    return retrofit.build().create(IceAndFireApi::class.java)
}