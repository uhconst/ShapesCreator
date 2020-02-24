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

    fun onClickSquare() {
        addNewShapeCanvas(ShapeType.SQUARE)
    }

    fun onClickCircle() {
        addNewShapeCanvas(ShapeType.CIRCLE)
    }

    fun onClickTriangle() {
        addNewShapeCanvas(ShapeType.TRIANGLE)
    }

    fun onClickUndo() {
        _modelsList.removeAt(_modelsList.size - 1)
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    fun onClickStats() {
        _events.postValue(Events.GO_TO_STATS)
    }

    private fun addNewShapeCanvas(shapeType: ShapeType) {
        _modelsList.add(generateNewShape(shapeType))
        _events.postValue(Events.UPDATE_SHAPE_CANVAS)
    }

    private fun generateNewShape(shapeType: ShapeType) =
        ShapeModel(
            centreX = Random.nextInt(0, 1200).toFloat(),
            centreY = Random.nextInt(0, 1200).toFloat(),
            shapeType = shapeType
        )

    enum class Events {
        UPDATE_SHAPE_CANVAS,
        GO_TO_STATS
    }
}