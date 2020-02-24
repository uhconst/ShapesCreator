package com.uhc.shapescreator.stats

import android.os.Parcelable
import com.uhc.shapescreator.editor.ShapeModel
import com.uhc.shapescreator.editor.ShapeType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatsModel(
    val statsList: List<SingleStats>
) : Parcelable

@Parcelize
data class SingleStats(
    val shapeType: ShapeType,
    val quantity: String
) : Parcelable {
    val shapeName get() = shapeType.shapeName
}

fun List<ShapeModel>.toStatsModel(): StatsModel {
    val singleStatsList = mutableListOf<SingleStats>()

    groupBy {
        it.shapeType
    }.map { (shapeType, shapeList) ->
        singleStatsList.add(
            SingleStats(
                shapeType,
                shapeList.size.toString()
            )
        )
    }

    return StatsModel(singleStatsList)
}