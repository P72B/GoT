package de.p72b.redandroid.got.house.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.p72b.redandroid.got.core.Action
import de.p72b.redandroid.got.core.SingleLiveEvent
import de.p72b.redandroid.got.core.network.Resource
import de.p72b.redandroid.got.core.network.Status.ERROR
import de.p72b.redandroid.got.core.network.Status.SUCCESS
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.usecase.GetHousesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

const val PAGE_SIZE = 30

class HouseListViewModel(
    private val getHousesUseCase: GetHousesUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel(), LifecycleObserver {

    val viewAction = SingleLiveEvent<ViewAction>()
    val isLoading = mutableStateOf(false)
    val items = MutableLiveData<List<House>>(emptyList())
    val page = mutableStateOf(1)
    private var scrollPosition = 0

    init {
        viewModelScope.launch(defaultDispatcher) {
            isLoading.value = true
            val result = getHousesUseCase.invoke(page.value, PAGE_SIZE)
            handleResult(result)
            isLoading.value = false
        }
    }

    fun onItemClicked(item: House) {
        viewAction.postValue(ViewAction.ShowHouseDetails(item))
    }

    fun nextPage() {
        viewModelScope.launch(defaultDispatcher) {
            if ((scrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                isLoading.value = true
                incrementPage()
                if (page.value > 1) {
                    handleResult(getHousesUseCase.invoke(page.value, PAGE_SIZE))
                }
                isLoading.value = false
            }
        }
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }

    private fun handleResult(result: Resource<List<House>>) {
        when (result.status) {
            SUCCESS -> {
                result.data?.let {
                    appendItems(it)
                }
            }
            ERROR -> {
                // TODO: retry mechanism here
                viewAction.postValue(ViewAction.ShowNetworkError)
            }
        }
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
        object ShowNetworkError : ViewAction()
    }
}