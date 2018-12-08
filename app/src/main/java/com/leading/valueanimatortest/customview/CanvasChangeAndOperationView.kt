package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName CanvasChangeAndOperationView
 * @date 2018/11/29 15:16
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class CanvasChangeAndOperationView(context: Context) : View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        // translate 平移，即改变坐标系位置
//        val mPaint = Paint()
//        mPaint.color = Color.GREEN
//        mPaint.style = Paint.Style.FILL
//        canvas.translate(100f, 100f)
//        val rect1 = Rect(0, 0, 400, 200)
//        canvas.drawRect(rect1, mPaint)

        // 构造红绿两个颜色的画笔
        val greenPaint = generatePaint(Color.GREEN, Paint.Style.STROKE, 3f)
        val redPaint = generatePaint(Color.RED, Paint.Style.STROKE, 3f)

//        // 构造一个矩形
//        val rect1 = Rect(0, 0, 400, 220)
//        // 在平移画布前用绿色画边框
//        canvas.drawRect(rect1, greenPaint)
//        // 平移画布后用红色画笔画出边框
//        canvas.translate(100f, 100f)
//        canvas.drawRect(rect1, redPaint)

//        // Canvas Rotate
//        val rect1 = Rect(300, 10, 500, 100)
//        // 画出原轮廓
//        canvas.drawRect(rect1, redPaint)
//        // 顺时旋转画布
//        canvas.rotate(30f)
//        // 画出旋转后的矩形
//        canvas.drawRect(rect1, greenPaint)

//        // scale 缩放坐标系密度
//        val rect1 = Rect(10, 10, 200, 100)
//        canvas.drawRect(rect1, greenPaint)
//        // 执行画布缩放
//        canvas.scale(0.5f, 1f)
//        canvas.drawRect(rect1, redPaint)

        // 扭曲（skew）
        //其实我觉得译成斜切更合适，在PS中的这个功能就差不多叫斜切。但这里还是直译吧，大家都是这个名字。看下它的构造函数：
        //void skew (float sx, float sy)
        //
        //参数说明：
        //float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
        //float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值，
        //
        //注意，这里全是倾斜角度的tan值哦，比如我们打算在X轴方向上倾斜60度，tan60=根号3，小数对应1.732
//        val rect1 = Rect(10, 10, 200, 100)
//        canvas.drawRect(rect1, greenPaint)
//        // x轴倾斜六十度，y轴不变
//        canvas.skew(1.732f, 0f)
//        canvas.drawRect(rect1, redPaint)

//        // 裁剪画布（clip系列函数）
//        //裁剪画布是利用Clip系列函数，通过与Rect、Path、Region取交、并、差等集合运算来获得最新的画布形状。除了调用Save、Restore函数以外，这个操作是不可逆的，一但Canvas画布被裁剪，就不能再被恢复！
//        //Clip系列函数如下：
//        //boolean	clipPath(Path mPath)
//        //boolean	clipPath(Path mPath, Region.Op op)
//        //boolean	clipRect(Rect rect, Region.Op op)
//        //boolean	clipRect(RectF rect, Region.Op op)
//        //boolean	clipRect(int left, int top, int right, int bottom)
//        //boolean	clipRect(float left, float top, float right, float bottom)
//        //boolean	clipRect(RectF rect)
//        //boolean	clipRect(float left, float top, float right, float bottom, Region.Op op)
//        //boolean	clipRect(Rect rect)
//        //boolean	clipRegion(Region region)
//        //boolean	clipRegion(Region region, Region.Op op)
//        // 先把背景色整个涂成红色。显示在屏幕上
//        //然后裁切画布，最后最新的画布整个涂成绿色。可见绿色部分，只有一小块，而不再是整个屏幕了。
//        //关于两个画布与屏幕合成，我就不再画图了，跟上面的合成过程是一样的。
//        canvas.drawColor(Color.RED)
//        canvas.clipRect(Rect(100, 100, 200, 200))
//        canvas.drawColor(Color.GREEN)

        // 画布的保存与恢复（save()、restore()）
        //前面我们讲的所有对画布的操作都是不可逆的，这会造成很多麻烦，比如，我们为了实现一些效果不得不对画布进行操作，但操作完了，画布状态也改变了，这会严重影响到后面的画图操作。如果我们能对画布的大小和状态（旋转角度、扭曲等）进行实时保存和恢复就最好了。
        //这小节就给大家讲讲画布的保存与恢复相关的函数——Save（）、Restore（）。
        //int save ()
        //void	restore()
        //
        //这两个函数没有任何的参数，很简单。
        //Save（）：每次调用Save（）函数，都会把当前的画布的状态进行保存，然后放入特定的栈中；
        //restore（）：每当调用Restore（）函数，就会把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布，并在这个画布上做画。
        //为了更清晰的显示这两个函数的作用，下面举个例子：
//        canvas.drawColor(Color.RED)
//        // 保存当前画布大小即整屏
//        canvas.save()
//        // 裁剪并绘制一个矩形
//        canvas.clipRect(Rect(100, 100, 800, 800))
//        canvas.drawColor(Color.GREEN)
//        // 恢复整屏画布
//        canvas.restore()
//        canvas.drawColor(Color.BLUE)
        canvas.drawColor(Color.RED)
        // 保存的画布大小为全屏幕大小
        canvas.save()

        canvas.clipRect(Rect(100, 100, 800, 800))
        canvas.drawColor(Color.GREEN)
        // 保存画布大小为 Rect(100,100,800,800)
        canvas.save()

        canvas.clipRect(Rect(200, 200, 700, 700))
        canvas.drawColor(Color.BLUE)
        // 保存画布大小为 Rect(200,200,700,700)
        canvas.save()

        canvas.clipRect(Rect(300, 300, 600, 600))
        canvas.drawColor(Color.BLACK)
        // 保存画布大小为 Rect(300,300,600,600)
        canvas.save()

        canvas.clipRect(Rect(400, 400, 500, 500))
        canvas.drawColor(Color.WHITE)
        // 保存画布大小为 Rect(400,400,500,500)

//        // 将栈顶的画布取出来，作为当前的画布，并画成黄色背景
//        canvas.restore()
//        canvas.drawColor(Color.YELLOW)

        // 连续出站三次，将最后一次出栈的canvas作为当前画布，并花城黄色背景
        canvas.restore()
        canvas.restore()
        canvas.restore()
        canvas.drawColor(Color.YELLOW)
    }

    fun generatePaint(color: Int, style: Paint.Style, width: Float): Paint {
        val paint = Paint()
        paint.color = color
        paint.style = style
        paint.strokeWidth = width
        return paint
    }
}