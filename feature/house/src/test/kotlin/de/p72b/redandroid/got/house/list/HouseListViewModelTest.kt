package de.p72b.redandroid.got.house.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.p72b.redandroid.got.core.network.Resource
import de.p72b.redandroid.got.core.network.Status
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.usecase.GetHousesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HouseListViewModelTest {

    private val getHousesUseCaseMock = mockk<GetHousesUseCase>()
    private val sampleHouseList = listOf(House("A"), House("B"))
    private val successResource = Resource(Status.SUCCESS, sampleHouseList, null)
    private val errorResource = Resource(Status.ERROR, emptyList<House>(), "Internal server error")

    private lateinit var toBeTested: HouseListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        coEvery { getHousesUseCaseMock.invoke(1, 30) } returns successResource
        coEvery { getHousesUseCaseMock.invoke(2, 30) } returns successResource
    }

    @Test
    fun `initial loading of houses`() = mainCoroutineRule.runBlockingTest {
        toBeTested = HouseListViewModel(getHousesUseCaseMock, mainCoroutineRule.testDispatcher)

        coVerify(exactly = 1) { getHousesUseCaseMock.invoke(1, 30) }
        Assert.assertEquals(sampleHouseList, toBeTested.items.value)
    }

    @Test
    fun `on item click should open details`() = mainCoroutineRule.runBlockingTest {
        val clickedItem = sampleHouseList[0]
        toBeTested = HouseListViewModel(getHousesUseCaseMock, mainCoroutineRule.testDispatcher)
        toBeTested.onItemClicked(clickedItem)

        Assert.assertEquals(
            HouseListViewModel.ViewAction.ShowHouseDetails(clickedItem),
            toBeTested.viewAction.value
        )
    }

    @Test
    fun `nextPage call will fetch new items`() = mainCoroutineRule.runBlockingTest {
        val scrollPosition = 120
        toBeTested = HouseListViewModel(getHousesUseCaseMock, mainCoroutineRule.testDispatcher)
        toBeTested.onChangeScrollPosition(scrollPosition)
        toBeTested.nextPage()

        coVerify(exactly = 1) { getHousesUseCaseMock.invoke(1, 30) }
        coVerify(exactly = 1) { getHousesUseCaseMock.invoke(2, 30) }
    }

    @Test
    fun `nextPage call without scroll position changed will not fetch new items`() = mainCoroutineRule.runBlockingTest {
        toBeTested = HouseListViewModel(getHousesUseCaseMock, mainCoroutineRule.testDispatcher)
        toBeTested.nextPage()

        coVerify(exactly = 1) { getHousesUseCaseMock.invoke(1, 30) }
        coVerify(exactly = 0) { getHousesUseCaseMock.invoke(2, 30) }
    }

    @Test
    fun `fetch error will show network error`() = mainCoroutineRule.runBlockingTest {
        coEvery { getHousesUseCaseMock.invoke(1, 30) } returns errorResource
        toBeTested = HouseListViewModel(getHousesUseCaseMock, mainCoroutineRule.testDispatcher)

        coVerify(exactly = 1) { getHousesUseCaseMock.invoke(1, 30) }
        Assert.assertEquals(
            HouseListViewModel.ViewAction.ShowNetworkError,
            toBeTested.viewAction.value
        )
    }
}