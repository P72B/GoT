package de.p72b.redandroid.got.house

import de.p72b.redandroid.got.house.list.HouseListViewModel
import de.p72b.redandroid.got.usecase.repositoryUseCaseModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val houseModule = module {

    viewModel {
        HouseListViewModel(
            getHousesUseCase = get()
        )
    }
}

val baseHouseModules = houseModule + repositoryUseCaseModules