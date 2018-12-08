package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName BezierCurveView
 * @date 2018/12/3 13:43
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class BezierCurveView : View {

    /**
     * 您应该避免在绘图或布局操作期间分配对象。 这些是经常调用的，因此可以通过由对象分配引起的垃圾收集暂停来中断平滑的UI。
     * 通常处理这种方式的方法是预先分配所需的对象，并为每个绘图操作重用它们。
     * 有些方法代表您分配内存（例如Bitmap.create），这些方法应该以相同的方式处理。 问题ID：DrawAllocation
     */
    private val mPath = Path()
    private val mPaint = Paint()

    private fun myInit() {
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.GREEN
//        mPaint.isAntiAlias = true
    }

    constructor(context: Context) : super(context) {
        myInit()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        myInit()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        // 整条线的起始点是通过Path.moveTo(x,y)来指定的，而如果我们连续调用quadTo()，前一个quadTo()的终点，就是下一个quadTo()函数的起点；
//        // 如果初始没有调用Path.moveTo(x,y)来指定起始点，则默认以控件左上角(0,0)为起始点
//        // 贝塞尔曲线起始点
//        mPath.moveTo(100f, 300f)
//        /**
//         *从最后一点添加二次贝塞尔曲线，接近控制点
//         *（x1，y1），并以（x2，y2）结束。 如果没有调用moveTo（）调用
//         *此轮廓，第一个点自动设置为（0,0）。
//         *
//         * @param x1二次曲线上控制点的x坐标
//         * @param y1二次曲线上控制点的y坐标
//         * @param x2二次曲线上终点的x坐标
//         * @param y2二次曲线上终点的y坐标
//         */
//        mPath.quadTo(200f, 200f, 300f, 300f)
//        mPath.quadTo(400f, 400f, 500f, 300f)
//
        canvas.drawPath(mPath, mPaint)
    }

    /**
     * 第一：有关在case MotionEvent.ACTION_DOWN时return true的问题：return true表示当前控件已经消费了下按动作，
     * 之后的ACTION_MOVE、ACTION_UP动作也会继续传递到当前控件中；
     * 如果我们在case MotionEvent.ACTION_DOWN时return false，那么后序的ACTION_MOVE、ACTION_UP动作就不会再传到这个控件来了。
     *
     * 第二：这里重绘控件使用的是postInvalidate();而我们以前也有用Invalidate()函数的。
     * 这两个函数的作用都是用来重绘控件的，但区别是Invalidate()一定要在UI线程执行，如果不是在UI线程就会报错。
     * 而postInvalidate()则没有那么多讲究，它可以在任何线程中执行，而不必一定要是主线程。
     * 其实在postInvalidate()就是利用handler给主线程发送刷新界面的消息来实现的，所以它是可以在任何线程中执行，而不会出错。
     * 而正是因为它是通过发消息来实现的，所以它的界面刷新可能没有直接调Invalidate()刷的那么快。
     * 所以在我们确定当前线程是主线程的情况下，还是以invalidate()函数为主。
     * 当我们不确定当前要刷新页面的位置所处的线程是不是主线程的时候，还是用postInvalidate为好；
     * 这里我是故意用的postInvalidate()，因为onTouchEvent()本来就是在主线程中的，使用Invalidate()是更合适的。
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 设置起点
                mPath.moveTo(event.x, event.y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // 连接触摸点
                mPath.lineTo(event.x, event.y)
                postInvalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    fun reset() {
        mPath.reset()
        invalidate()
    }
}