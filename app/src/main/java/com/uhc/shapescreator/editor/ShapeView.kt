package com.uhc.shapescreator.editor

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ShapeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val drawSquarePaint: Paint = Paint()
    private val drawCirclePaint: Paint = Paint()
    private val drawTrianglePaint: Paint = Paint()

    private var path = Path()

    private lateinit var modelsList: List<ShapeModel>

    /**
     * Setup paint colour for each shape
     */
    private fun setupPaint() {
        drawSquarePaint.color = Color.BLUE
        drawCirclePaint.color = Color.GREEN
        drawTrianglePaint.color = Color.RED
    }

    /**
     * Setup paint on init
     */
    init {
        setupPaint()
    }

    /**
     * Draws the UI for the view on the provided [Canvas].
     *
     * This method does nothing if a [ShapeModel] has not been provided by the view model.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (this::modelsList.isInitialized) {
            modelsList.forEach { shapeModel ->
                shapeModel.apply {
                    when (shapeType) {
                        /** Square */
                        ShapeType.SQUARE -> canvas.drawRect(toRectF(), drawSquarePaint)

                        /** Circle */
                        ShapeType.CIRCLE -> canvas.drawCircle(
                            centreX,
                            centreY,
                            SHAPE_WIDTH,
                            drawCirclePaint
                        )

                        /** Triangle */
                        ShapeType.TRIANGLE -> {
                            /** Top */
                            path.moveTo(centreX, centreY - SHAPE_WIDTH)
                            /** Bottom left */
                            path.lineTo(centreX - SHAPE_WIDTH, centreY + SHAPE_WIDTH)
                            /** Bottom right */
                            path.lineTo(centreX + SHAPE_WIDTH, centreY + SHAPE_WIDTH)
                            /** Back to top */
                            path.lineTo(centreX, centreY - SHAPE_WIDTH)

                            path.close()

                            canvas.drawPath(path, drawTrianglePaint)
                        }
                    }
                }
            }
        }
    }

    /**
     * Update to display the provided [ShapeModel].
     *
     * This method calls [View.invalidate] to force the view to be redrawn using [View.onDraw].
     */
    fun update(modelsList: List<ShapeModel>) {
        this.modelsList = modelsList
        invalidate()
    }

    /**
     * Convert a [ShapeModel] to a [RectF] so it can draw a Square
     */
    private fun ShapeModel.toRectF() = RectF(
        centreX,
        centreX,
        centreX + SHAPE_WIDTH * 2,
        centreX + SHAPE_WIDTH * 2
    )

    companion object {
        /** Shape width being used for all the shapes */
        private const val SHAPE_WIDTH = 150f
    }
}