package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout


/**
 * @package com.leading.valueanimatortest.customview
 * @fileName RedPointView
 * @date 2019/2/20 15:12
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class RedPointView : FrameLayout {

    private lateinit var mStartPoint: PointF
    private lateinit var mCurPoint: PointF
    private var mRadius = 20
    private lateinit var mPaint: Paint
    private lateinit var mPath: Path
    private var mTouch: Boolean = false

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        mStartPoint = PointF(100f, 100f)
        mCurPoint = PointF()

        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL

        mPath = Path()
    }

    /**
     * 绘制子视图
     */
    override fun dispatchDraw(canvas: Canvas) {

        canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), mPaint, Canvas.ALL_SAVE_FLAG)
        canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius.toFloat(), mPaint)
        if (mTouch) {
            caculatePath()
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius.toFloat(), mPaint)
            canvas.drawPath(mPath, mPaint)
        }
        canvas.restore()

        super.dispatchDraw(canvas)
    }

    private fun caculatePath() {
        val x = mCurPoint.x
        val y = mCurPoint.y
        val startX = mStartPoint.x
        val startY = mStartPoint.y
        val dx = x - startX
        val dy = y - startY
        val a = Math.atan((dy / dx).toDouble())
        val offsetX = mRadius * Math.sin(a)
        val offsetY = mRadius * Math.cos(a)

        // 根据角度算出四边形的四个点
        val x1 = startX - offsetX
        val y1 = startY + offsetY

        val x2 = x - offsetX
        val y2 = y + offsetY

        val x3 = x + offsetX
        val y3 = y - offsetY

        val x4 = startX + offsetX
        val y4 = startY - offsetY

        val anchorX = (startX + x) / 2
        val anchorY = (startY + y) / 2

        mPath.reset()
        mPath.moveTo(x1.toFloat(), y1.toFloat())
        mPath.quadTo(anchorX, anchorY, x2.toFloat(), y2.toFloat())
        mPath.lineTo(x3.toFloat(), y3.toFloat())
        mPath.quadTo(anchorX, anchorY, x4.toFloat(), y4.toFloat())
        mPath.lineTo(x1.toFloat(), y1.toFloat())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mTouch = true
            MotionEvent.ACTION_UP -> mTouch = false
        }
        mCurPoint.set(event.x, event.y)
        postInvalidate()
        return true
    }


}