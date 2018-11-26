package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.view.View

class MyView : View {
    var m_context: Context

    constructor(context: Context) : super(context) {
        m_context = context
    }

    //重写OnDraw（）函数，在每次重绘时自主实现绘图
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 设置画笔基本属性
        val paint = Paint()
        // 抗锯齿
        paint.isAntiAlias = true
        // 设置画笔颜色
        paint.color = Color.RED
        // 设置填充样式
        paint.style = Paint.Style.FILL
        // 设置画笔宽度
        paint.strokeWidth = 5f
//        // 设置阴影
//        paint.setShadowLayer(10f, 15f, 15f, Color.GREEN)

        // 设置画布背景颜色
        canvas?.drawRGB(255, 255, 255)

        // 画圆
//        canvas?.drawCircle(190f,200f,150f,paint)

        // 画直线
//        canvas?.drawLine(100f, 100f, 200f, 200f, paint)

        // 画多条直线，
        // pts:是点的集合，大家下面可以看到，这里不是形成连接线，而是每两个点形成一条直线，pts的组织方式为｛x1,y1,x2,y2,x3,y3,……｝
//        val pts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
//        canvas?.drawLines(pts, paint)

        // 画点
//        canvas?.drawPoint(100f, 100f, paint)

        // 画多个点
        // float[] pts:点的合集，与上面直线一直，样式为｛x1,y1,x2,y2,x3,y3,……｝
        //int offset:集合中跳过的数值个数，注意不是点的个数！一个点是两个数值；
        //count:参与绘制的数值的个数，指pts[]里人数值个数，而不是点的个数，因为一个点是两个数值
        //
        //下面举例说明上面offset与count的含义：（跳过第一个点，画出后面两个点，第四个点不画），注意一个点是两个数值！
//        val pts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
//        canvas?.drawPoints(pts, 2, 4, paint)

        // 画矩形
        // 直接构造
//        canvas?.drawRect(10f, 10f, 100f, 100f, paint)
//        // 使用 RectF构造
//        val rectF = RectF(120f, 10f, 210f, 100f)
//        canvas?.drawRect(rectF, paint)
//        // 使用 Rect构造
//        val rect = Rect(230, 10, 320, 100)
//        canvas?.drawRect(rect, paint)

        // 画圆角矩形
//        val rectF = RectF(100f, 10f, 300f, 100f)
        // 参数：
        //RectF rect:要画的矩形
        //float rx:生成圆角的椭圆的X轴半径
        //float ry:生成圆角的椭圆的Y轴半径
//        canvas?.drawRoundRect(rectF, 20f, 20f, paint)

        // 画圆
//        canvas?.drawCircle(150f, 150f, 100f, paint)

//        // 画椭圆，椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形
//        val rectF = RectF(100f, 10f, 300f, 100f)
//        // 画矩形
//        canvas?.drawRect(rectF, paint)
//        // 画椭圆
//        paint.color = Color.GREEN
//        canvas?.drawOval(rectF, paint)

        // 画弧线
        // 弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；
        //
        //void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
        //
        //参数：
        //RectF oval:生成椭圆的矩形
        //float startAngle：弧开始的角度，以X轴正方向为0度
        //float sweepAngle：弧持续的角度
        //boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
        val rectF1 = RectF(100f, 10f, 300f, 100f)
        canvas?.drawArc(rectF1, 0f, 90f, true, paint)
        val rectF2 = RectF(400f, 10f, 600f, 100f)
        canvas?.drawArc(rectF2, 0f, 90f, false, paint)


    }
}