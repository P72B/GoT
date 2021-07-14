package de.p72b.redandroid.got.house.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import de.p72b.redandroid.got.data.House

class HouseDetailsViewModel(
    val house: House
) : ViewModel(), LifecycleObserver {

}