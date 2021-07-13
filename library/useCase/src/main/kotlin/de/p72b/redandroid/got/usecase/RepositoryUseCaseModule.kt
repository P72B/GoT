package de.p72b.redandroid.got.usecase

import de.p72b.redandroid.got.repository.baseRepositoryModules
import org.koin.dsl.module

private val repositoryUseCaseModule = module {

    factory {
        GetHousesUseCase(
            repository = get()
        )
    }
}

val repositoryUseCaseModules = baseRepositoryModules + repositoryUseCaseModule