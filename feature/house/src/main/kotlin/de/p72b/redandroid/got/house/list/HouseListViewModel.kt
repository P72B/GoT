package de.p72b.redandroid.got.house.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.p72b.redandroid.got.core.Action
import de.p72b.redandroid.got.core.SingleLiveEvent
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.usecase.GetHousesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PAGE_SIZE = 50

class HouseListViewModel(
    private val getHousesUseCase: GetHousesUseCase
) : ViewModel(), LifecycleObserver {

    val viewAction = SingleLiveEvent<ViewAction>()
    val isLoading = mutableStateOf(false)
    val items = MutableLiveData<List<House>>(emptyList())
    val page = mutableStateOf(1)
    private var scrollPosition = 0

    init {
        viewModelScope.launch {
            isLoading.value = true
            delay(500)
            appendItems(getHousesUseCase.invoke(page.value, PAGE_SIZE))
            isLoading.value = false
        }
    }

    fun onItemClicked(item: House) {
        viewAction.postValue(ViewAction.ShowHouseDetails(item))
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                isLoading.value = true
                incrementPage()
                if (page.value > 1) {
                    delay(200)
                    appendItems(getHousesUseCase.invoke(page.value, PAGE_SIZE))
                }
                isLoading.value = false
            }
        }
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }

    private fun incrementPage() {
        page.value++
    }

    private fun appendItems(input: List<House>) {
        val current = ArrayList(items.value)
        current.addAll(input)
        items.value = current
    }

    sealed class ViewAction : Action {
        data class ShowHouseDetails(val item: House) : ViewAction()
    }
}