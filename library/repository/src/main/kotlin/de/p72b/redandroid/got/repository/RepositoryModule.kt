package de.p72b.redandroid.got.repository

import de.p72b.redandroid.got.repository.http.iceAndFireApi
import de.p72b.redandroid.got.repository.mapper.HouseMapper
import org.koin.dsl.module

private val repositoryModule = module {

    single {
        Repository(
            api = iceAndFireApi,
            houseMapper = HouseMapper()
        )
    }
}

val baseRepositoryModules = repositoryModule