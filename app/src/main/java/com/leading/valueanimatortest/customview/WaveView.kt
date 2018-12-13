package com.leading.valueanimatortest.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator


/**
 * @author Zj
 * @package com.leading.valueanimatortest.customview
 * @fileName WaveView
 * @date 2018/12/10 16:35
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class WaveView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint: Paint
    private val mPath: Path
    private val mItemWaveLength = 1000
    private var dx: Int = 0
    private var dy: Int = 0

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

        //  波浪不向下移动
//        mPath.moveTo((-mItemWaveLength + dx).toFloat(), (originY - dy).toFloat())

        // 波浪向下移动
        dy += 1
        mPath.moveTo((-mItemWaveLength + dx).toFloat(), (originY + dy).toFloat())

        var i = -mItemWaveLength
        while (i <= width + mItemWaveLength) {
            mPath.rQuadTo((halfWaveLen / 2).toFloat(), -100f, halfWaveLen.toFloat(), 0f)
            mPath.rQuadTo((halfWaveLen / 2).toFloat(), 100f, halfWaveLen.toFloat(), 0f)
            i += mItemWaveLength
        }

        mPath.lineTo(width.toFloat(), height.toFloat())
        mPath.lineTo(0f, height.toFloat())
        mPath.close()

        canvas.drawPath(mPath, mPaint)
    }

    fun startAnim(height: Int) {
        Log.e("height--> ", height.toString())
        val animator = ValueAnimator.ofInt(0, mItemWaveLength)
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            dx = animation.animatedValue as Int
            postInvalidate()
        }

        animator.start()

//        val animator1 = ValueAnimator.ofInt(0, 300)
//        animator1.duration = 2000
//        animator1.interpolator = LinearInterpolator()
//        animator1.addUpdateListener { animation ->
//            dy = animation.animatedValue as Int
//        }
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(animator, animator1)
//        animatorSet.start()
    }

    private fun getScreenHeight(windowManager: WindowManager): Int {
        val outMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(outMetrics)
//        val width = outMetrics.widthPixels
        return outMetrics.heightPixels;
    }
}
