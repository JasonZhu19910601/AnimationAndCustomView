package com.leading.valueanimatortest.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * @author Zj
 * @package com.leading.valueanimatortest.customview
 * @fileName MyWaveView
 * @date 2018/12/10 15:29
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyWaveView : View {

    private val mPaint: Paint
    private val mPath: Path
    private val mItemWaveLength = 1000

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        mPath = Path()
        mPaint = Paint()
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPath.reset()
        val originY = 300
        val halfWaveLen = mItemWaveLength / 2
//        mPath.moveTo((-mItemWaveLength).toFloat(), originY.toFloat())
        mPath.moveTo((-mItemWaveLength).toFloat() + dx, originY.toFloat())
        var i = -mItemWaveLength
        while (i <= width + mItemWaveLength) {
            mPath.rQuadTo((halfWaveLen / 2).toFloat(), -50f, halfWaveLen.toFloat(), 0f)
            mPath.rQuadTo((halfWaveLen / 2).toFloat(), 50f, halfWaveLen.toFloat(), 0f)
            i += mItemWaveLength
        }

        // 第一，将paint设置为填充：mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 第二，将path闭合：
        mPath.lineTo(width.toFloat(), height.toFloat())
        mPath.lineTo(0f, height.toFloat())
        mPath.close()

        canvas.drawPath(mPath, mPaint)
    }

    /**
     * 动画的长度为一个波长，将当前值保存在类的成员变量dx中；
     * 然后在画图的时候，在path.moveTo（）中加上现在的移动值dx:mPath.moveTo(-mItemWaveLength+dx,originY);
     */
    private var dx = 0

    /**
     * 让波纹动起来其实挺简单，
     * 利用调用在path.moveTo的时候，将起始点向右移动即可实现移动，
     * 而且只要我们移动一个波长的长度，波纹就会重合，就可以实现无限循环了。
     */
    fun startAnim() {
        val animator = ValueAnimator.ofInt(0, mItemWaveLength)
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(animation: ValueAnimator) {
                    dx = animation.animatedValue as Int
                    postInvalidate()
                }
            }
        }
        animator.start()
    }
}
