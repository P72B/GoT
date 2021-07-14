package de.p72b.redandroid.got.repository.http

import de.p72b.redandroid.got.repository.dto.HouseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IceAndFireApi {

    companion object {
        const val API_ROOT = "api"
    }

    @GET("$API_ROOT/houses")
    suspend fun getHouses(@Query("page") page: Int, @Query("pageSize") pageSize: Int): List<HouseResponse>
}