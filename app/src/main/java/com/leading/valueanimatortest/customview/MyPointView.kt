package com.leading.valueanimatortest.customview

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import com.leading.valueanimatortest.entity.Point
import com.leading.valueanimatortest.evaluator.PointEvaluator

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName MyPointView
 * @date 2018/10/9 15:27
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyPointView : View {
    private var mCurPoint = Point(0)
    private val paint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(300f, 300f, mCurPoint.getRadius().toFloat(), paint)
    }

    fun setPointRadius(radius: Int) {
        mCurPoint.setRadius(radius)
        invalidate()
    }

    fun doPointViewObjectAnim(){
        val animator = ObjectAnimator.ofInt(this, "pointRadius", 0, 300, 100)
        animator.duration = 2000
        animator.start()
    }

    fun doPointAnim() {
        val animator = ValueAnimator.ofObject(PointEvaluator(),
                Point(20), Point(200))
        animator.addUpdateListener {
            mCurPoint = it.animatedValue as Point
            invalidate()
        }
        animator.interpolator = BounceInterpolator()
        animator.duration = 1000
        animator.start()
    }

}