package de.p72b.redandroid.got.repository.http

import de.p72b.redandroid.got.repository.dto.HouseResponse
import retrofit2.http.GET

interface IceAndFireApi {

    companion object {
        const val API_ROOT = "api"
    }

    @GET("$API_ROOT/houses")
    suspend fun getHouses(): List<HouseResponse>
}