package de.p72b.redandroid.got.usecase

import de.p72b.redandroid.got.core.network.Resource
import de.p72b.redandroid.got.core.network.Status
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.repository.Repository
import de.p72b.redandroid.got.usecase.GetHousesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetHousesUseCaseTest {

    private val repositoryMock = mockk<Repository>()
    private val sampleHouseList = listOf(House("A"), House("B"))
    private val page = 1
    private val pageSize = 30
    private val successResource = Resource(Status.SUCCESS, sampleHouseList, null)

    private lateinit var toBeTested: GetHousesUseCase

    @Before
    fun setUp() {
        coEvery { repositoryMock.getHouses(1, 30) } returns successResource
        toBeTested = GetHousesUseCase(repositoryMock)
    }

    @Test
    fun `useCase will trigger repository call`() = runBlockingTest {
        toBeTested.invoke(page, pageSize)

        coVerify(exactly = 1) { repositoryMock.getHouses(page, pageSize) }
    }
}