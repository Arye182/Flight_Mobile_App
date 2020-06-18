package com.example.flightmobileapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class JoystickView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var outerJoyRadius: Float = 0f
    private var outerJoyCenter: PointF = PointF()
    private val outerJoyPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.parseColor("#FFC250")
        isAntiAlias = true
    }


    private var joyRadius: Float = 0f
    private var joyCenter: PointF = PointF()
    private val joyPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.parseColor("#FFC107")
        isAntiAlias = true
    }




    // calculate positions and sizes here, not when drawing
    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        // make sure actual code handles padding well.
        outerJoyRadius = 0.5f* Math.min(width, height).toFloat()
        outerJoyCenter = PointF(width/2.0f, height/2.0f)
        joyRadius = 0.2f* Math.min(width, height).toFloat()
        joyCenter = PointF(width/2.0f, height/2.0f)

    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(outerJoyCenter.x, outerJoyCenter.y, outerJoyRadius, outerJoyPaint)
        canvas.drawCircle(joyCenter.x, joyCenter.y, joyRadius, joyPaint)

        outerJoyPaint.setStyle(Paint.Style.FILL);
        outerJoyPaint.setColor(Color.YELLOW);
        joyPaint.setStyle(Paint.Style.FILL);
        joyPaint.setColor(Color.MAGENTA);

        canvas.drawCircle(outerJoyCenter.x, outerJoyCenter.y, outerJoyRadius, outerJoyPaint)
        canvas.drawCircle(joyCenter.x, joyCenter.y, joyRadius, joyPaint)




    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> touchDown(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp(event.x, event.y)
        }
        return true
    }


    private fun touchDown(x: Float, y: Float){
        joyCenter.set(x,y)
        // will render again the screen.
        invalidate()
    }


    private fun touchMove(x: Float, y: Float){
        joyCenter.set(x,y)
        // will render again the screen.
        invalidate()
    }

    private fun touchUp(x: Float, y: Float){
        joyCenter.set(width/2.0f, height/2.0f)
        // will render again the screen.
        invalidate()
    }

}

