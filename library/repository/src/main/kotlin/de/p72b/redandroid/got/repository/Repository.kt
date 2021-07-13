package de.p72b.redandroid.got.repository

import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.repository.dto.HouseResponse
import de.p72b.redandroid.got.repository.http.IceAndFireApi
import de.p72b.redandroid.got.repository.mapper.Mapper

class Repository(
    private val api: IceAndFireApi,
    private val houseMapper: Mapper<HouseResponse, House>
) {
    suspend fun getHouses(): List<House> {
        return mapHouses(api.getHouses())
    }

    private fun mapHouses(input: List<HouseResponse>): List<House> {
        return input.map {
            houseMapper.map(it)
        }
    }
}