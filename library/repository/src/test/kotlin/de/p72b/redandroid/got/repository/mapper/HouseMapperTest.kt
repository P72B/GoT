package de.p72b.redandroid.got.repository.mapper

import de.p72b.redandroid.got.repository.dto.HouseResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetHousesUseCaseTest {

    private val name = "test_name"
    private val region = "test_region"
    private val houseResponse = HouseResponse(
        name = name,
        region = region
    )

    private lateinit var toBeTested: HouseMapper

    @Before
    fun setUp() {
        toBeTested = HouseMapper()
    }

    @Test
    fun `useCase will trigger repository call`() = runBlockingTest {
        val result = toBeTested.map(houseResponse)

        Assert.assertEquals(name, result.name)
        Assert.assertEquals(region, result.region)
    }
}