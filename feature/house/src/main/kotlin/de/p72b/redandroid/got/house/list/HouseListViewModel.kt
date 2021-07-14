package de.p72b.redandroid.got.house.list

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.p72b.redandroid.got.core.Action
import de.p72b.redandroid.got.core.SingleLiveEvent
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.usecase.GetHousesUseCase
import kotlinx.coroutines.launch

class HouseListViewModel(
    getHousesUseCase: GetHousesUseCase
) : ViewModel(), LifecycleObserver {

    val viewAction = SingleLiveEvent<ViewAction>()
    val items = MutableLiveData<List<House>>(emptyList())

    init {
        viewModelScope.launch {
            getHousesUseCase.invoke().let {
                items.postValue(it)
            }
        }
    }

    fun onItemClicked(item: House) {
        viewAction.postValue(ViewAction.ShowHouseDetails(item))
    }

    sealed class ViewAction : Action {
        data class ShowHouseDetails(val item: House) : ViewAction()
    }
}