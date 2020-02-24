package com.uhc.shapescreator.stats

import com.uhc.shapescreator.editor.ShapeType

data class StatsModel(
    val shapeType: ShapeType,
    val quantity: Int
) {
    val shapeName = shapeType.shapeName
}