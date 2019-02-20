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
 * @fileName HeartMap
 * @date 2019/1/28 14:28
 * @author Zj
 * @describe 中间是一条心电图线，其余位置都是透明像素；大家先想想我们要怎么利用这张图片实现上面的动画呢？
 *            利用Mode.DST_IN模式，由于在这个模式中，相交区域优先显示目标图像，所以我们这里需要显示心电图，
 *            所以心电图就是目标图像。
 *            那么问题来了，源图像是啥？
 *            由于我们需要从右向左逐渐显示心电图图像，所以我们源图像就是自建的空白图像，在这个图像中，绘制一个矩形，逐渐增大矩形的区域，即相交区域也会跟着增大，由于相交区域会显示出目标图像，显示出来的结果就是心电图的动画
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class HeartMap : View {
    private var mPaint: Paint = Paint()
    private var mItemWaveLength = 0
    private var dx = 0

    private var bmpSRC: Bitmap
    private var bmpDST: Bitmap

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mPaint.color = Color.RED

        bmpDST = BitmapFactory.decodeResource(resources, R.drawable.heartmap, null)
        bmpSRC = Bitmap.createBitmap(bmpDST.width, bmpDST.height, Bitmap.Config.ARGB_8888)

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
        val c = Canvas(bmpSRC)

        // 清空bitmap
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)

        // 画上矩形
        c.drawRect((bmpDST.width - dx).toFloat(), 0f, bmpDST.width.toFloat(), bmpDST.height.toFloat(), mPaint)

        // 模式合成
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(bmpDST, 0f, 0f, mPaint)
        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_IN))
        canvas.drawBitmap(bmpSRC, 0f, 0f, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(layerId)
    }

}