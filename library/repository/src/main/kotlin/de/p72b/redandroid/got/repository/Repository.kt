package de.p72b.redandroid.got.repository

import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.repository.dto.HouseResponse
import de.p72b.redandroid.got.repository.http.IceAndFireApi
import de.p72b.redandroid.got.core.network.Resource
import de.p72b.redandroid.got.repository.http.ResponseHandler
import de.p72b.redandroid.got.repository.mapper.Mapper

class Repository(
    private val api: IceAndFireApi,
    private val houseMapper: Mapper<HouseResponse, House>,
    private val responseHandler: ResponseHandler
) {
    suspend fun getHouses(page: Int, pageSize: Int): Resource<List<House>> {
        return try {
            responseHandler.handleSuccess(mapHouses(api.getHouses(page, pageSize)))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private fun mapHouses(input: List<HouseResponse>): List<House> {
        return input.filter { it.name != null }.map {
            houseMapper.map(it)
        }
    }
}