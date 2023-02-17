package hu.bme.aut.tictactoedemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBackGround = Paint()
    private val paintLines = Paint()

    private var circles = mutableListOf<PointF>()
    init {
        paintBackGround.color = Color.BLACK
        paintBackGround.strokeWidth = 5f
        paintBackGround.style = Paint.Style.FILL

        paintLines.color = Color.WHITE
        paintLines.style = Paint.Style.STROKE
        paintLines.strokeWidth = 5f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)

        canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paintLines)

        for (circle in circles) {
            canvas.drawCircle(circle.x, circle.y, 70F, paintLines)
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        circles.add(PointF(event.x, event.y))
        invalidate()
        return super.onTouchEvent(event)
    }
    public fun reset() {
        circles.clear()
        invalidate()
    }
}