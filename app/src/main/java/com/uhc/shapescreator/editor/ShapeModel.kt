package com.uhc.shapescreator.editor

data class ShapeModel(
    val radius: Float = 0f,
    val centreX: Float = 0f,
    val centreY: Float = 0f,
    val shapeType: ShapeType

)

enum class ShapeType {
    SQUARE,
    CIRCLE,
    TRIANGLE
}