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

        // 蓝色通道矩阵
        val colorMatrix = ColorMatrix(floatArrayOf(
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
        ))
        mPaint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, null, Rect(0, 0, 500, 500 * bitmap.height / bitmap.width), mPaint)
    }
}
