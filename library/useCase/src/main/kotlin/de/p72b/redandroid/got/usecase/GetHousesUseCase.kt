package de.p72b.redandroid.got.usecase

import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.repository.Repository

class GetHousesUseCase(
    private val repository: Repository,
) {
    suspend fun invoke(page: Int, pageSize: Int = 10): List<House> {
        return repository.getHouses(page, pageSize)
    }
}