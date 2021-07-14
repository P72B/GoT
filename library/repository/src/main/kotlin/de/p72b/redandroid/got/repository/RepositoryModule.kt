package de.p72b.redandroid.got.repository

import de.p72b.redandroid.got.repository.http.createAdapter
import de.p72b.redandroid.got.repository.http.createHttpCache
import de.p72b.redandroid.got.repository.mapper.HouseMapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val repositoryModule = module {

    single {
        createHttpCache(androidApplication())
    }

    single {
        Repository(
            api = createAdapter(get()),
            houseMapper = HouseMapper()
        )
    }
}

val baseRepositoryModules = repositoryModule