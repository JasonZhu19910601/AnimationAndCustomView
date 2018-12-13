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
 * @package com.leading.valueanimatortest.customview
 * @fileName PaintFunctionsTestView
 * @date 2018/12/13 9:18
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class PaintFunctionsTestView : View {
    private var mPaint: Paint = Paint()
    private var mPath: Path = Path()

    init {
        mPaint.strokeWidth = 4f
        mPaint.isAntiAlias = true
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.STROKE
        mPath = Path()
    }

    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        // 设置线帽样式，
//        // 取值有Cap.ROUND(圆形线帽)、Cap.SQUARE(方形线帽)、Paint.Cap.BUTT(无线帽)
//        mPaint.strokeCap = Paint.Cap.ROUND
//        canvas?.drawLine(100f, 200f, 400f, 200f, mPaint)
//
//        mPaint.strokeCap = Paint.Cap.SQUARE
//        canvas?.drawLine(100f, 400f, 400f, 400f, mPaint)
//
//        mPaint.strokeCap = Paint.Cap.BUTT
//        canvas?.drawLine(100f, 600f, 400f, 600f, mPaint)
//
//        // 垂直画出 x=100 这条线
//        mPaint.reset()
//        mPaint.color = Color.RED
//        mPaint.strokeWidth = 2f
//        mPaint.isAntiAlias = true
//        canvas?.drawLine(100f, 50f, 100f, 750f, mPaint)


//        // setStrokeJoin(Paint.Join join)
//        //设置线段连接处样式，取值有：Join.MITER（结合处为锐角）、Join.Round(结合处为圆弧)、Join.BEVEL(结合处为直线)
//        mPath.moveTo(100f, 100f)
//        mPath.lineTo(450f, 100f)
//        mPath.lineTo(100f, 300f)
//        mPaint.strokeJoin = Paint.Join.MITER
//
//        mPath.moveTo(100f, 400f)
//        mPath.lineTo(450f, 400f)
//        mPath.lineTo(100f, 600f)
//        mPaint.strokeJoin = Paint.Join.BEVEL
//
//        mPath.moveTo(100f, 700f)
//        mPath.lineTo(450f, 700f)
//        mPath.lineTo(100f, 900f)
//        mPaint.strokeJoin = Paint.Join.ROUND
//        canvas?.drawPath(mPath, mPaint)


        // setPathEffect(PathEffect effect)
        //设置路径样式;取值类型是所有派生自PathEffect的子类
//        // CornerPathEffect——圆形拐角效果 很明显能看出在半径不同情况下，连接位置也是不一样的。
//        mPath.moveTo(100f, 600f)
//        mPath.lineTo(400f, 100f)
//        mPath.lineTo(700f, 900f)
//        canvas?.drawPath(mPath, mPaint)
//
//        mPaint.color = Color.RED
//        mPaint.pathEffect = CornerPathEffect(100f)
//        canvas?.drawPath(mPath, mPaint)
//
//        mPaint.color = Color.YELLOW
//        mPaint.pathEffect = CornerPathEffect(200f)
//        canvas?.drawPath(mPath, mPaint)


//        // DashPathEffect——虚线效果
//        // intervals[]：表示组成虚线的各个线段的长度；整条虚线就是由intervals[]中这些基本线段循环组成的。
//        // 比如，我们定义new float[] {20,10}； 那这个虚线段就是由两段线段组成的，第一个可见的线段长为20，每二个线段不可见，长度为10；
//        //
//        // 对于intervals[]数组的有两个限定：
//        //长度必须大于等于2；因为必须有一个实线段和一个空线段来组成虚线。
//        //个数必须为偶数，如果是基数，最后一个数字将被忽略；这个很好理解，因为一组虚线的组成必然是一个实线和一个空线成对组成的。
//        // phase：开始绘制的偏移值
//        canvas?.drawColor(Color.BLACK)
//        mPath.moveTo(100f, 600f)
//        mPath.lineTo(400f, 100f)
//        mPath.lineTo(700f, 900f)
//        canvas?.drawPath(mPath, mPaint)
//
//        //使用DashPathEffect画线段
//        mPaint.color = Color.RED
//        mPaint.pathEffect = DashPathEffect(floatArrayOf(20f, 10f, 100f, 100f), 0f)
//        canvas?.translate(0f, 100f)
//        canvas?.drawPath(mPath, mPaint)
//
//        // 画同一条线段，偏移值为15
//        mPaint.color = Color.YELLOW
//        mPaint.pathEffect = DashPathEffect(floatArrayOf(20f, 10f, 100f, 100f), dashDx)
//        canvas?.translate(0f, 100f)
//        canvas?.drawPath(mPath, mPaint)


//        // DiscretePathEffect——离散路径效果
//        //
//        //同样，图中第一条线是原生的，第二条线加上离散路径效果后的样式。
//        //DiscretePathEffect就是将原来路径分隔成定长的线段，然后将每条线段随机偏移一段位置，我们可以用它来模拟一种类似生锈铁丝的效果；
//        //它的构造函数如下：
//        //public DiscretePathEffect(float segmentLength, float deviation)
//        //
//        //第一个参数segmentLength：表示将原来的路径切成多长的线段。如果值为2，那么这个路径就会被切成一段段由长度为2的小线段。
//        // 所以这个值越小，所切成的小线段越多；这个值越大，所切成的小线段越少。
//        //
//        //第二参数deviation：表示被切成的每个小线段的可偏移距离。
//        // 值越大，就表示每个线段的可偏移距离就越大，就显得越凌乱，值越小，每个线段的可偏移原位置的距离就越小。
//        //
//        // 第一条原生path
//        getRandomPath()
//        canvas?.drawPath(mPath, mPaint)
//
//        // 第二条path
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = DiscretePathEffect(2f, 5f)
//        canvas?.drawPath(mPath, mPaint)
//
//        // 第三条path
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = DiscretePathEffect(6f, 5f)
//        canvas?.drawPath(mPath, mPaint)
//
//        // 第四条path
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = DiscretePathEffect(6f, 15f)
//        canvas?.drawPath(mPath, mPaint)


        /* PathDashPathEffect——印章路径效果
         * 作用就是用另一个路径图案做为印章，沿着指定路径一个个盖上去。
         *
         * public PathDashPathEffect(Path shape, float advance, float phase,Style style)
         *
         * Path shape:表示印章路径，比如我们下面示例中的三角形加右上角一个点；
         *
         * float advance：表示两个印章路径间的距离,很容易理解，印章间距离越大，间距就越大。
         *
         * float phase：路径绘制偏移距离，与上面DashPathEffect中的float phase参数意义相同
         *
         * Style style：表示在遇到转角时，如何操作印章以使转角平滑过渡，
         * 取值有：Style.ROTATE，Style.MORPH，Style.TRANSLATE;
         * Style.ROTATE表示通过旋转印章来过渡转角；Style.MORPH表示通过变形印章来过渡转角；Style.TRANSLATE表示通过位移来过渡转角。
         */
//        // 画出原始路径
//        val path = Path()
//        path.moveTo(100f, 600f)
//        path.lineTo(400f, 100f)
//        path.lineTo(700f, 900f)
//        canvas?.drawPath(path, mPaint)
//
//        // 印章路径就是一个三角形加右上角一个点，构建印章路径
//        // 使用印章路径效果
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = PathDashPathEffect(getStampPath(), 35f, 0f, PathDashPathEffect.Style.TRANSLATE)
//        canvas?.drawPath(path, mPaint)

//        val randomPath = getRandomPath()
//        val stampPath = getStampPath()
//        // 原路径
//        canvas?.drawPath(randomPath, mPaint)
//
//        // 画笔路径转角处效果设置为变形 PathDashPathEffect.Style.MORPH
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = PathDashPathEffect(stampPath, 35f, 0f, PathDashPathEffect.Style.MORPH)
//        canvas?.drawPath(randomPath, mPaint)
//
//        // 画笔路径转角处效果设置为变形 PathDashPathEffect.Style.ROTATE
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = PathDashPathEffect(stampPath, 35f, 0f, PathDashPathEffect.Style.ROTATE)
//        canvas?.drawPath(randomPath, mPaint)
//
//        // 画笔路径转角处效果设置为变形 PathDashPathEffect.Style.TRANSLATE
//        canvas?.translate(0f, 200f)
//        mPaint.pathEffect = PathDashPathEffect(stampPath, 35f, 0f, PathDashPathEffect.Style.TRANSLATE)
//        canvas?.drawPath(randomPath, mPaint)


        /**
         * ComposePathEffect与SumPathEffect
         * 这两个都是用来合并两个特效的。但它们之间是有区别的：
         * public ComposePathEffect(PathEffect outerpe, PathEffect innerpe)
         * ComposePathEffect合并两个特效是有先后顺序的，它会先将第二个参数的PathEffect innerpe的特效作用于路径上，
         * 然后再在此加了特效的路径上作用第二个特效。
         * public SumPathEffect(PathEffect first, PathEffect second)
         * 而SumPathEffect是分别对原始路径分别作用第一个特效和第二个特效。然后再将这两条路径合并，做为最终结果。
         */

    }

    private var dashDx: Float = 0f

    /**
     * 使用ValueAnimator,动画长度值设为一个虚线的一个基线的长度，这里的基线是由20，10，100，100组成的，
     * 所以一个基线长度是230，然后设置成无限循环，把拿到的值设置为DashPathEffect偏移量即可;
     */
    fun startDashTranslationAnim() {
        val animator = ValueAnimator.ofFloat(0f, 230f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            dashDx = animation.animatedValue as Float
            postInvalidate()
        }
        animator.start()
    }

    /**
     * 获取一个随机 路径
     * 利用Math.random()产生一个随机数，然后每隔35px构造出一个点
     */
    fun getRandomPath(): Path {
        val path = Path()
        // 定义路径起点
        path.moveTo(0f, 0f)
        // 定义路径的各个点
        for (i in 0..40) {
            path.lineTo(35f * i, (((Math.random() * 150).toFloat())));
        }
        return path;
    }


    /**
     * 获取印章路径
     */
    fun getStampPath(): Path {
        val stampPath = Path()
        stampPath.moveTo(0f, 20f)
        stampPath.lineTo(10f, 0f)
        stampPath.lineTo(20f, 20f)
        stampPath.close()

        stampPath.addCircle(0f, 0f, 3f, Path.Direction.CCW)
        return stampPath
    }
}