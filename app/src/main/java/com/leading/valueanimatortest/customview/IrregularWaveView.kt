package com.leading.valueanimatortest.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.leading.valueanimatortest.R

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName IrregularWaveView
 * @date 2019/1/28 15:33
 * @author Zj
 * @describe 就是在圆形遮罩上绘制不断移动的不规则的波浪图。
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class IrregularWaveView : View {
    private var mPaint: Paint;
    private var mItemWaveLength = 0
    private var dx = 0

    private val bmpDST: Bitmap
    private val bmpSRC: Bitmap

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        mPaint = Paint()

        bmpDST = BitmapFactory.decodeResource(resources, R.drawable.wave_bg)
        bmpSRC = BitmapFactory.decodeResource(resources, R.drawable.circle_shape)

        mItemWaveLength = bmpDST.width

        startAnim()
    }

    private fun startAnim() {
        val animator = ValueAnimator.ofInt(0, mItemWaveLength)
        animator.duration = 6000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            dx = it.animatedValue as Int
            postInvalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 先画上圆形
        canvas.drawBitmap(bmpSRC, 0f, 0f, mPaint)
        // 再画结果
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        // 只在源图像和目标图像相交的地方绘制目标图像
        canvas.drawBitmap(bmpDST, Rect(dx, 0, dx + bmpSRC.width, bmpSRC.height), Rect(0, 0, bmpSRC.width, bmpSRC.height), mPaint)
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas.drawBitmap(bmpSRC, 0f, 0f, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}