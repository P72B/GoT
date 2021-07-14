package de.p72b.redandroid.got.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val FILTER_HOUSE = "House "

@Parcelize
class House(
    val name: String,
    val region: String? = null
) : Parcelable {

    fun getDisplayedName(): String {
        return name.replace(FILTER_HOUSE, "")
    }
}