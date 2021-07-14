package de.p72b.redandroid.got.repository.mapper

import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.repository.dto.HouseResponse

class HouseMapper : Mapper<HouseResponse, House> {

    override fun map(input: HouseResponse): House =
        House(
            name = input.name!!,
            region = input.region
        )
}