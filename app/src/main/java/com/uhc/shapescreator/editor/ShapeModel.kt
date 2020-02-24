package com.uhc.shapescreator.editor

data class ShapeModel(
    val centreX: Float = 0f,
    val centreY: Float = 0f,
    val shapeType: ShapeType

)

enum class ShapeType(val shapeName: String) {
    SQUARE("Square"),
    CIRCLE("Circle"),
    TRIANGLE("Triangle")
}