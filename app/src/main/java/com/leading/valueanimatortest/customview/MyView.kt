package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View

class MyView : View {
    var m_context: Context

    constructor(context: Context) : super(context) {
        m_context = context
    }

    //重写OnDraw（）函数，在每次重绘时自主实现绘图
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        // 设置画笔基本属性
//        val paint = Paint()
//        // 抗锯齿
//        paint.isAntiAlias = true
//        // 设置画笔颜色
//        paint.color = Color.RED
//        // 设置填充样式
//        paint.style = Paint.Style.STROKE
//        // 设置画笔宽度
//        paint.strokeWidth = 2f
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

//        // 画弧线
//        // 弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；
//        //
//        //void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
//        //
//        //参数：
//        //RectF oval:生成椭圆的矩形
//        //float startAngle：弧开始的角度，以X轴正方向为0度
//        //float sweepAngle：弧持续的角度
//        //boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
//        val rectF1 = RectF(100f, 10f, 300f, 100f)
//        canvas?.drawArc(rectF1, 0f, 90f, true, paint)
//        val rectF2 = RectF(400f, 10f, 600f, 100f)
//        canvas?.drawArc(rectF2, 0f, 90f, false, paint)

//        // 直线路径
//        //void moveTo (float x1, float y1):直线的开始点；即将直线路径的绘制点定在（x1,y1）的位置；
//        //void lineTo (float x2, float y2)：直线的结束点，又是下一次绘制直线路径的开始点；lineTo（）可以一直用；
//        //void close ():如果连续画了几条直线，但没有形成闭环，调用Close()会将路径首尾点连接起来，形成闭环；
//        val path = Path()
//        // 设定起点
//        path.moveTo(10f, 10f)
//        path.lineTo(10f, 100f)
//        path.lineTo(300f, 100f)
//        path.lineTo(500f, 100f)
//        // 闭环
//        path.close()
//        canvas?.drawPath(path, paint)

//        // 矩形路径
//        //void addRect (float left, float top, float right, float bottom, Path.Direction dir)
//        //void addRect (RectF rect, Path.Direction dir)
//        //
//        //这里Path类创建矩形路径的参数与上篇canvas绘制矩形差不多，唯一不同的一点是增加了Path.Direction参数；
//        //Path.Direction有两个值：
//        //Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
//        //Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
//        // 逆时针路径
//        val CCWRectPath = Path()
//        val rectF1 = RectF(50f, 50f, 240f, 200f)
//        CCWRectPath.addRect(rectF1, Path.Direction.CCW)
//        // 顺时针路径
//        val CWPath = Path()
//        val rectF2 = RectF(290f, 50f, 480f, 200f)
//        CWPath.addRect(rectF2, Path.Direction.CW)
//        // 画出两个路径
//        canvas?.drawPath(CCWRectPath, paint)
//        canvas?.drawPath(CWPath, paint)
//
//        // 依据路径写文字
//        val text = "no pain no gain"
//        paint.color = Color.BLUE
//        paint.textSize = 20f
//        // 逆时针生成文字
//        canvas?.drawTextOnPath(text, CCWRectPath, 0f, 18f, paint)
//        // 顺时针生成文字
//        canvas?.drawTextOnPath(text, CWPath, 0f, 18f, paint)

//        // 圆角矩形路径
//        //void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
//        //void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
//        //
//        //这里有两个构造函数，部分参数说明如下：
//        //第一个构造函数：可以定制每个角的圆角大小：
//        //float[] radii：必须传入8个数值，分四组，分别对应每个角所使用的椭圆的横轴半径和纵轴半径，如｛x1,y1,x2,y2,x3,y3,x4,y4｝，其中，x1,y1对应第一个角的（左上角）用来产生圆角的椭圆的横轴半径和纵轴半径，其它类推……
//        //第二个构造函数：只能构建统一圆角大小
//        //float rx：所产生圆角的椭圆的横轴半径；
//        //float ry：所产生圆角的椭圆的纵轴半径；
//        val path = Path()
//        val rectF1 = RectF(50f, 50f, 240f, 200f)
//        path.addRoundRect(rectF1, 10f, 15f, Path.Direction.CCW)
//
//        val rectF2 = RectF(290f, 50f, 480f, 200f)
//        val radii = floatArrayOf(10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f)
//        path.addRoundRect(rectF2, radii, Path.Direction.CCW)
//
//        canvas?.drawPath(path, paint)

//        // 圆形路径
//        //void addCircle (float x, float y, float radius, Path.Direction dir)
//        //
//        //参数说明：
//        //float x：圆心X轴坐标
//        //float y：圆心Y轴坐标
//        //float radius：圆半径
//        val path = Path()
//        path.addCircle(200f, 200f, 100f, Path.Direction.CCW)
//        canvas?.drawPath(path, paint)

//        // 椭圆路径
//        //void addOval (RectF oval, Path.Direction dir)
//        //
//        //参数说明：
//        //RectF oval：生成椭圆所对应的矩形
//        //Path.Direction :生成方式，与矩形一样，分为顺时针与逆时针，意义完全相同，不再重复
//        val path = Path()
//        val rectF = RectF(50f, 50f, 240f, 200f)
//        path.addOval(rectF, Path.Direction.CCW)
//        canvas?.drawPath(path, paint)

//        // 弧形路径
//        //void addArc (RectF oval, float startAngle, float sweepAngle)
//        //
//        //参数：
//        //RectF oval：弧是椭圆的一部分，这个参数就是生成椭圆所对应的矩形；
//        //float startAngle：开始的角度，X轴正方向为0度
//        //float sweepAngel：持续的度数；
//        val path = Path()
//        val rectF = RectF(50f, 50f, 240f, 200f)
//        path.addArc(rectF, 0f, 100f)
//        canvas?.drawPath(path, paint)

        val text = "Hello Custom View"
        // 文字
        val paint = Paint()
        paint.color = Color.RED
        // 画笔宽度
        paint.strokeWidth = 5f
        // 指定是否使用抗锯齿功能，使用抗锯齿功能会使绘制变慢
        paint.isAntiAlias = true
        // 设置文字大小
        paint.textSize = 80f
        // 绘图样式，设置为填充
        paint.style = Paint.Style.FILL

//        // 示例1：绘图样式的区别：
//        canvas?.drawText("Hello Custom View",10f,100f,paint)
//        // 绘图样式设置为描边
//        paint.style = Paint.Style.STROKE
//        canvas?.drawText("Hello Custom View",10f,200f,paint)
//        // 绘图样式设置填充且描边
//        paint.style = Paint.Style.FILL_AND_STROKE
//        canvas?.drawText("Hello Custom View",10f,300f,paint)

//        // 示例二：文字样式设置及倾斜度正负区别
//        // 样式设置
//        // 加粗
//        paint.isFakeBoldText = true
//        // 下划线
//        paint.isUnderlineText = true
//        // 删除线
//        paint.isStrikeThruText = true;
//
//        // 设置字体水平倾斜度，普通斜体字是 -0.25，可见往右斜
//        paint.textSkewX = -0.25f
//        canvas?.drawText("示例二：文字样式设置及倾斜度正负区别", 10f, 100f, paint)
//
//        // 水平倾斜度设置为 0.25f，往左斜
//        paint.textSkewX = 0.25f
//        canvas?.drawText("示例二：文字样式设置及倾斜度正负区别", 10f, 200f, paint)

//        // 示例三：水平拉伸设置（ paint.setTextScaleX(2);）
//        // 普通样式字体
//        canvas?.drawText(text, 10f, 100f, paint)
//        // 水平方向拉伸两倍
//        paint.textScaleX = 2f
//        canvas?.drawText(text, 10f, 200f, paint)
//
//        //写在同一位置,不同颜色,看下高度是否看的不变
//        // 还原拉伸效果
//        paint.textScaleX = 1f
//        canvas?.drawText(text, 10f, 300f, paint)
//
//        paint.color = Color.GREEN
//        // 重新设置为拉伸效果
//        paint.textScaleX = 2f
//        canvas?.drawText(text, 10f, 300f, paint)

//        // 指定文字位置
//        // void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
//        //void drawPosText (String text, float[] pos, Paint paint)
//        //
//        //说明：
//        //第一个构造函数：实现截取一部分文字绘制；
//        //
//        //参数说明：
//        //char[] text：要绘制的文字数组
//        //int index:：第一个要绘制的文字的索引
//        //int count：要绘制的文字的个数，用来算最后一个文字的位置，从第一个绘制的文字开始算起
//        //float[] pos：每个字体的位置，同样两两一组，如｛x1,y1,x2,y2,x3,y3……｝
//        val pos = floatArrayOf(
//                80f,100f,
//                80f,200f,
//                80f,300f,
//                80f,400f
//                )
//        canvas?.drawPosText("画图示例",pos,paint)

//        // 沿路径绘制
//        // void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
//        //void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
//        //
//        //参数说明：
//        //
//        //有关截取部分字体绘制相关参数（index,count），没难度，就不再讲了，下面首重讲hOffset、vOffset
//        //float hOffset  : 与路径起始点的水平偏移距离
//        //float vOffset  : 与路径中心的垂直偏移量
//        val string = "no pain no gain"
        val string = "字体样式设置"
//
//        // 先创建并绘制两个圆形路径
//        val circlePath = Path()
//        // 逆向绘制
//        circlePath.addCircle(220f, 200f, 100f, Path.Direction.CCW)
//        canvas?.drawPath(circlePath, paint)
//
//        val circlePath2 = Path()
//        circlePath2.addCircle(750f, 200f, 100f, Path.Direction.CCW)
//        canvas?.drawPath(circlePath2, paint)
//
//        paint.style = Paint.Style.FILL
//        paint.color = Color.GREEN
//        //hoffset、voffset参数值全部设为0，看原始状态是怎样的
//        canvas?.drawTextOnPath(string, circlePath, 0f, 0f, paint)
//        //第二个路径，改变hoffset、voffset参数值
//        canvas?.drawTextOnPath(string, circlePath2, 80f, 30f, paint)

        // 字体样式设置（Typeface）
        // 使用系统中的字体
        // 使用系统中自带的字体有下面两种方式来构造Typeface：
        //
        // Typeface	defaultFromStyle(int style)//创建默认字体
        // Typeface	create(String familyName, int style) //直接通过指定字体名来加载系统中自带的文字样式
        val familyName = "宋体"
        val typeface = Typeface.create(familyName, Typeface.NORMAL)
        paint.typeface = typeface;
        canvas?.drawText(string, 10f, 100f, paint)

    }
}