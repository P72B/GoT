package de.p72b.redandroid.got.repository.dto

import com.google.gson.annotations.SerializedName

data class HouseResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null
)