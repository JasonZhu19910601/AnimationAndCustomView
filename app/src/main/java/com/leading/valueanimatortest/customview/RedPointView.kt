package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.leading.valueanimatortest.R


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
    private val DEFAULT_RADIUS = 20
    private var mRadius = DEFAULT_RADIUS
    private lateinit var mPaint: Paint
    private lateinit var mPath: Path
    private var mTouch: Boolean = false
    private var isAnimStart = false

    /**
     * 添加 TextView
     */
    private lateinit var mTipTextView: TextView

    /**
     * 我们需要添加一个ImageView控件来单独来播放爆炸逐帧动画：
     */
    private lateinit var mExploredImageView: ImageView

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    /**
     * 添加TextView后需要添加三个功能：
     * 1、初始只显示TextView，而不显示原来的圆圈
     * 2、点击TextView所在区域才能移动TextVIew
     * 3、移动时，TextView跟随手指移动，同时显示原TextVIew所在的圆圈和贝赛尔连接线
     */
    private fun initView() {
        mStartPoint = PointF(100f, 100f)
        mCurPoint = PointF()

        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL

        mPath = Path()

        // 添加 TextView
        val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mTipTextView = TextView(context)
        mTipTextView.layoutParams = layoutParams
        mTipTextView.setPadding(10, 10, 10, 10)
        mTipTextView.setBackgroundResource(R.drawable.tv_bg)
        mTipTextView.setTextColor(Color.WHITE)
        mTipTextView.text = "99+"

        // 添加一个播放爆炸动画的ImageView
        mExploredImageView = ImageView(context)
        mExploredImageView.layoutParams = layoutParams
        mExploredImageView.setImageResource(R.drawable.tip_anim)
        mExploredImageView.visibility = View.INVISIBLE

        // 显示消息数的 TextView
        addView(mTipTextView)
        // 显示爆炸效果的 ImageView
        addView(mExploredImageView)
    }

    /**
     * 绘制子视图
     */
    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(RectF(0f, 0f, width.toFloat(), height.toFloat()), mPaint, Canvas.ALL_SAVE_FLAG)
        if (!mTouch || isAnimStart) {
            //先看用户没有点击时，把TextView设置在初始的位置点
            mTipTextView.x = mStartPoint.x - mTipTextView.width / 2
            mTipTextView.y = mStartPoint.y - mTipTextView.height / 2
        } else {
            // 只有移动时方绘制两圆和路径
            calculatePath()
            canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius.toFloat(), mPaint)
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius.toFloat(), mPaint)
            canvas.drawPath(mPath, mPaint)

            // 将TextView的中心放在放在当前手指位置
            mTipTextView.x = mCurPoint.x - mTipTextView.width / 2
            mTipTextView.y = mCurPoint.y - mTipTextView.height / 2
        }

        canvas.restore()

        // super.dispatchDraw(canvas);的作用是绘出该控件的所有子控件，所以这样结论就很明显了，
        // 如果是像第一个那样先做super.dispatchDraw(canvas);再做其它绘图操作的结果是，
        // 先把子控件绘制出来，然后再画自己，这样可能会造成自己把子控件给覆盖上；
        //相反，先做其它绘图操作然后再调用super.dispatchDraw(canvas)的结果是：先把自己给画出来，
        // 然后再画子控件，子控件会把自己的绘图结果给覆盖上；
        // 在这段代码中，我们是先绘制自己，然后再绘制它的子控件（TextView）,
        // 这样的结果就是TextView会把当前的绘图内容覆盖上
        super.dispatchDraw(canvas)
    }

    private fun calculatePath() {
        val x = mCurPoint.x
        val y = mCurPoint.y
        val startX = mStartPoint.x
        val startY = mStartPoint.y
        val dx = x - startX
        val dy = y - startY
        val a = Math.atan((dy / dx).toDouble())
        val offsetX = mRadius * Math.sin(a)
        val offsetY = mRadius * Math.cos(a)

        // 在拉伸时，跟根据用户的拉伸长度，动态的设置当前所画圆的半径
        // 根据勾股定理（a^2+b^2=c^2）求出两个圆心之间当前距离，
        // 然后按照一定的规则计算出当前的圆半径，我这里定的规则就是DEFAULT_RADIUS-distance/15；
        //但不要一直小到0，因为我们中间的连接线是两个相同半径的圆的切线来计算出来的，
        // 所以当圆心半径变小时，两个圆之间的连接矩形也在变小，所以小到一定程度后，就不能再小了，我这里这个临界值定为9；
        val distance = Math.sqrt(Math.pow((y - startY).toDouble(), 2.0) + Math.pow((x - startX).toDouble(), 2.0))
        mRadius = (DEFAULT_RADIUS - distance / 15).toInt()

        // 在绘图的时候，我们就要开启爆炸效果了，上面我们在半径小于9的时候，
        // 一直给它赋值9，现在我们当它小于9时，让它爆炸：
        if (mRadius < 9) {
//            mRadius = 9
            isAnimStart = true
            mExploredImageView.x = mCurPoint.x - mTipTextView.width / 2
            mExploredImageView.y = mCurPoint.y - mTipTextView.height / 2
            mExploredImageView.visibility = View.VISIBLE
            (mExploredImageView.drawable as AnimationDrawable).start()

            mTipTextView.visibility = View.GONE
        }

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

    /**
     * 我们需要在用户点击区域在TextView内部时才允许拖动TextView：
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
//                mTouch = true
                // 判断触摸点是否在tipImageView中
                // 这里主要是在MotionEvent.ACTION_DOWN的时候，
                // 判断当前当前手指区域是否在TextView内部，如果是就将mTouch赋值为true；
                val rect = Rect()
                val location = IntArray(2)

                // public void getLocationOnScreen(int[] location)
                // 该函数的功能是获取当前控件所在屏幕的位置，传进去一个location的数组，
                // 在执行以后会把left,top值赋给location[0]和location[1]
                mTipTextView.getLocationOnScreen(location)

                rect.left = location[0]
                rect.top = location[1]
                rect.right = mTipTextView.width + location[0]
                rect.bottom = mTipTextView.height + location[1]
                // event.getRawX()得到的就是相对屏幕的坐标
                // getX与getRawX的区别：getX()得到是相对当前控件左上角的坐标，而getRawX是得到在屏幕中的坐标
                if (rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    mTouch = true
                }
            }
            MotionEvent.ACTION_UP -> {
                // 抬起手指时还原位置
                mTouch = false
            }
        }
        mCurPoint.set(event.x, event.y)
        postInvalidate()
        return true
    }


}