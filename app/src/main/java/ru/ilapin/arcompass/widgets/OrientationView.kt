package ru.ilapin.arcompass.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.ilapin.common.math.Vector4f

class OrientationView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    private val arrowPaint = Paint()

    val vector = Vector4f()

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    init {
        arrowPaint.color = Color.GREEN
        arrowPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)

        if (vector.length() > 0) {
            val offsetX = width / 2.0f
            val offsetY = height / 2.0f

            canvas.drawLine(offsetX, offsetY, vector.x + offsetX, -vector.y + offsetY, arrowPaint)
        }
    }
}