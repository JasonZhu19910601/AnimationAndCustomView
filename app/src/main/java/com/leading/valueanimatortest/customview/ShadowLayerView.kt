package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.leading.valueanimatortest.R

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName ShadowLayerView
 * @date 2019/3/5 16:21
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class ShadowLayerView : View {
    private val mPaint: Paint = Paint()
    private lateinit var mBitmap: Bitmap
    private var mRadius = 1f
    private var mDx = 10f
    private var mDy = 10f
    private var mSetShadow = true

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mPaint.color = Color.GREEN
        mPaint.textSize = 25f
//        mPaint.setShadowLayer(1f, 10f, 10f, Color.GRAY)
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.sample)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (mSetShadow) {
            mPaint.setShadowLayer(mRadius, mDx, mDy, Color.LTGRAY)
        } else {
            mPaint.clearShadowLayer()
        }

        canvas.drawText("AndroidShadowLayerTest", 100f, 100f, mPaint)

        canvas.drawCircle(200f, 200f, 50f, mPaint)

        canvas.drawBitmap(mBitmap, null, Rect(200, 300, 200 + mBitmap.width,
                300 + mBitmap.height), mPaint)
    }

    fun changeShadowRadius() {
        mRadius++
        postInvalidate()
    }

    fun changeDx() {
        mDx += 5
        postInvalidate()
    }

    fun changeDy() {
        mDy += 5
        postInvalidate()
    }

    fun clearShadow() {
        mSetShadow = false
        postInvalidate()
    }

    fun showShadow() {
        mSetShadow = true
        postInvalidate()
    }
}