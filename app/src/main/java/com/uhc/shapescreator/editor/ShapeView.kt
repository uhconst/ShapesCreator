package com.uhc.shapescreator.editor

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ShapeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val drawPaint: Paint = Paint()

    private var canvas: Canvas? = null

    private lateinit var modelsList: List<ShapeModel>

    private fun setupPaint() {
        drawPaint.color = Color.BLUE
    }

    init {
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        this.canvas = canvas

        if (this::modelsList.isInitialized) {
            modelsList.forEach { shapeModel ->
                shapeModel.apply {
                    when (shapeType) {
                        ShapeType.SQUARE -> {
                            val gapRect = RectF(
                                100f,
                                100f,
                                200f,
                                200f
                            )
                            canvas.drawRect(gapRect, drawPaint)
                        }
                        ShapeType.CIRCLE -> canvas.drawCircle(centreX, centreY, radius, drawPaint)
                        ShapeType.TRIANGLE -> TODO()
                    }
                }
            }
        }
    }

    fun update(modelsList: List<ShapeModel>) {
        this.modelsList = modelsList
        invalidate()
    }
}