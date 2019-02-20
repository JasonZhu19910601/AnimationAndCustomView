package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName BitmapCanvasView
 * @date 2019/2/20 10:11
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class BitmapCanvasView : View {

    private var mBmp: Bitmap

    private var mPaint: Paint = Paint()

    private var mBmpCanvas: Canvas

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mPaint.color = Color.RED
        mBmp = Bitmap.createBitmap(1000, 500, Bitmap.Config.ARGB_8888)
        mBmpCanvas = Canvas(mBmp)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.textSize = 100F
        mBmpCanvas.drawText("Android Canvas", 0F, 100F, mPaint)

        canvas?.drawBitmap(mBmp, 0f, 0f, mPaint)
    }
}