package de.p72b.redandroid.gothouses

import de.p72b.redandroid.got.house.baseHouseModules
import org.koin.dsl.module

object DependencyGraph {

    fun get() = baseHouseModules
}