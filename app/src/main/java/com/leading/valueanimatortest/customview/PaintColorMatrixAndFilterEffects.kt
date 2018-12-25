package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.leading.valueanimatortest.R

/**
 * @author Zj
 * @package com.leading.valueanimatortest.customview
 * @fileName PaintColorMatrixAndFilterEffects
 * @date 2018/12/14 15:03
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class PaintColorMatrixAndFilterEffects(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint = Paint()
    private val bitmap: Bitmap// 位图

    init {
        mPaint.isAntiAlias = true
        // 获取位图
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sample)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制原始位图
        canvas.drawBitmap(bitmap, null, Rect(0, 0, 500, 500 * bitmap.height / bitmap.width), mPaint)

        canvas.translate(510f, 0f)
        // 生成色彩矩阵
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 1f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 绿色值上添加增量50，即增大绿色的饱和度
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                1f, 0f, 0f, 0f, 0f,
//                0f, 1f, 0f, 0f, 50f,
//                0f, 0f, 1f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 色彩反转/反相功能
//        //色彩反转就是求出每个色彩的补值来做为目标图像的对应颜色值：
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                -1f, 0f, 0f, 0f, 255f,
//                0f, -1f, 0f, 0f, 255f,
//                0f, 0f, -1f, 0f, 255f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 色彩的缩放运算
        // 我们可以针对某一个颜色值进行放大缩小运算，但当对R、G、B、A同时进行放大缩小时，就是对亮度进行调节！
//        // 色彩的缩放运算其实就是色彩的乘法运算。
//        // 在色彩矩阵对角线上的分别代表R、G、B、A的几个值，将其分别乘以指定的值。这就是所谓的缩放变换。
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                1.2f, 0f, 0f, 0f, 0f,
//                0f, 1.2f, 0f, 0f, 0f,
//                0f, 0f, 1.2f, 0f, 0f,
//                0f, 0f, 0f, 1.2f, 0f
//        ))

        // 缩放变换的特殊应用（通道输出）
        // 由于在色彩变换矩阵中，对角线上的数的取值范围是从0-1的，所以当取0时，这个色彩就完全不显示，
        // 所以当我们R、G都取0，而独有B取1时，就只显示了蓝色，所形成的图像也就是我们通常说的蓝色通道；
//        // 红色通道矩阵
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                1f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 绿色通道矩阵
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                0f, 0f, 0f, 0f, 0f,
//                0f, 1f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 蓝色通道矩阵
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f, 0f,
//                0f, 0f, 1f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

        // 当围绕红色轴进行色彩旋转时，由于当前红色轴的色彩是不变的，而仅利用三角函数来动态的变更绿色和蓝色的颜色值。
        // 这种改变就叫做色相调节！当围绕红色轴旋转时，是对图片就行红色色相的调节；
        // 同理，当围绕蓝色颜色轴旋转时，就是对图片就行蓝色色相调节；当然，当围绕绿色轴旋转时，就是对图片进行绿色色相的调节.
//        // 色彩投射的一个最简单应用就是变为黑白图片：
//        val colorMatrix = ColorMatrix(floatArrayOf(
//                0.213f, 0.715f, 0.072f, 0f, 0f,
//                0.213f, 0.715f, 0.072f, 0f, 0f,
//                0.213f, 0.715f, 0.072f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        ))

//        // 投射运算的另一个应用是：色彩反色
//        //当我们利用色彩矩阵将两个颜色反转，这种操作就叫做色彩反色
//        //比如，下面的的将红色和绿色反色（红绿反色）
//        val colorMatrix = floatArrayOf(
//                0f,1f,0f,0f,0f,
//                1f,0f,0f,0f,0f,
//                0f,0f,1f,0f,0f,
//                0f,0f,0f,1f,0f
//        )

//        // 变旧照片
//        //投射运算的另一个应用是照片变旧，对应矩阵如下：
//        val colorMatrix = floatArrayOf(
//                1 / 2f, 1 / 2f, 1 / 2f, 0f, 0f,
//                1 / 3f, 1 / 3f, 1 / 3f, 0f, 0f,
//                1 / 4f, 1 / 4f, 1 / 4f, 0f, 0f,
//                0f, 0f, 0f, 1f, 0f
//        )

//        setScale——色彩缩放
//        同样，对于色彩的缩放运算ColorMatrics也已经为我们封装了一个函数：
//        public void setScale(float rScale, float gScale, float bScale,float aScale)
//        总共有四个参数，分别对应R,G,B,A颜色值的缩放倍数。
        // 生成色彩矩阵
        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(1f, 1.3f, 1f, 1f)
        mPaint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, null, Rect(0, 0, 500, 500 * bitmap.height / bitmap.width), mPaint)
    }
}
