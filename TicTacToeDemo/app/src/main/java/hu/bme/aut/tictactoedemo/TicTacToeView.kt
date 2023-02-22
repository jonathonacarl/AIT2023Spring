package hu.bme.aut.tictactoedemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBackGround = Paint()
    private val paintLines = Paint()

    private val paintText = Paint()

    private var myImg = BitmapFactory.decodeResource(
        resources, R.drawable.mountain)

    init {
        paintBackGround.color = Color.BLACK
        paintBackGround.strokeWidth = 5f
        paintBackGround.style = Paint.Style.FILL

        paintLines.color = Color.WHITE
        paintLines.style = Paint.Style.STROKE
        paintLines.strokeWidth = 5f

        paintText.color = Color.GREEN
        paintText.textSize = 100f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height / 3f

        myImg = Bitmap.createScaledBitmap(myImg,
            width/3, height/3, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)

        canvas.drawBitmap(myImg, 0f, 0f, null)

        drawGameArea(canvas)

        drawPlayers(canvas)

        canvas.drawText("2", 0f, height/3f, paintText)
    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLines)

        // two horizontal lines
        canvas.drawLine(
            0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLines
        )
        canvas.drawLine(
            0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLines
        )

        // two vertical lines
        canvas.drawLine(
            (width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLines
        )
        canvas.drawLine(
            (2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLines
        )
    }


    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLines)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine(
                        (i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLines
                    )
                    canvas.drawLine(
                        ((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLines
                    )
                }
            }
        }
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {

            if ((context as MainActivity).isFlagmodeOn()) {
                // place flag
            } else {
                // discover this field / step on this field
            }


            // get the right coordinates (0,1,2)
            val tX = event.x.toInt() / (width/3)
            val tY = event.y.toInt() / (height/3)
            if (tX<3 && tY<3
                && TicTacToeModel.getFieldContent(tX,tY) == TicTacToeModel.EMPTY) {

                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.nextPlayer)
                TicTacToeModel.changeNextPlayer()

                var nextMsg = context.getString(R.string.textOComes)
                if (TicTacToeModel.nextPlayer == TicTacToeModel.CROSS) {
                    nextMsg = "The next player is X"
                }
                (context as MainActivity).showText(nextMsg)



                invalidate() // the system will call the onDraw(...)

                if (TicTacToeModel.whoIsWinner() == TicTacToeModel.CIRCLE) {
                    (context as MainActivity).showMessage("Circle is the winner")
                }

            }

        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

    public fun reset() {
        TicTacToeModel.resetModel()
        invalidate()
    }
}