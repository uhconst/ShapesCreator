package com.uhc.shapescreator.editor

import androidx.lifecycle.LiveData
import com.uhc.shapescreator.ui.base.BaseViewModel
import com.uhc.shapescreator.utils.EventLiveData
import kotlin.random.Random

class EditorViewModel : BaseViewModel() {

    private val _modelsList: MutableList<ShapeModel> = mutableListOf()
    val modelsList: List<ShapeModel> get() = _modelsList

    private val _events = EventLiveData<Events>()
    val events: LiveData<Events> get() = _events

    private var widthMax: Int = 0
    private var heightMax: Int = 0

    fun onClickSquare() {
        _modelsList.add(generateSquareShape())
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    fun onClickCircle() {
        _modelsList.add(generateCircleShape())
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    fun onClickTriangle() {
        _modelsList.add(generateTriangleShape())
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    fun onClickUndo() {
        _modelsList.removeAt(_modelsList.size - 1)
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    fun onClickStats() {
        _events.postValue(Events.GO_TO_STATS)
    }

//    fun setWidthAndHeight(width: Int, height: Int) {
//        widthMax = width
//        heightMax = height
//    }

    fun generateSquareShape(): ShapeModel {
        return ShapeModel(
            radius = 0f,
            centreX = 0f,
            centreY = 0f,
            shapeType = ShapeType.SQUARE
        )
    }

    fun generateCircleShape(): ShapeModel {
        return ShapeModel(
            radius = 140f,
            centreX = Random.nextInt(0, 1200).toFloat(),
            centreY = Random.nextInt(0, 1200).toFloat(),
            shapeType = ShapeType.CIRCLE
        )
    }

    fun generateTriangleShape(): ShapeModel {
        return ShapeModel(
            radius = 0f,
            centreX = 0f,
            centreY = 0f,
            shapeType = ShapeType.CIRCLE
        )
    }

    enum class Events {
        UPDATE_SHAPE_CANVAS,
        GO_TO_STATS
    }
}