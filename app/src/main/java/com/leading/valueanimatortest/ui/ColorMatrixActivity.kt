package com.leading.valueanimatortest.ui

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import kotlinx.android.synthetic.main.activity_color_matrix.*

class ColorMatrixActivity : AppCompatActivity() {
    val TAG = ColorMatrixActivity::class.simpleName
    private lateinit var mOriginalBmp: Bitmap
    private lateinit var mTempBmp: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_matrix)

        mOriginalBmp = BitmapFactory.decodeResource(resources, R.drawable.sample)
        mTempBmp = Bitmap.createBitmap(mOriginalBmp.width, mOriginalBmp.height, Bitmap.Config.ARGB_8888)

//        seekbar.progress = 1
//        seekbar.max = 20

        seekbar.progress = 180
        seekbar.max = 360

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                img.setImageBitmap(handleColorMatrixBmp(progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val colorMatrix1 = ColorMatrix(floatArrayOf(
                0.1f, 0.2f, 0.3f, 0.4f, 0.5f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
        ));

        val colorMatrix2 = ColorMatrix(floatArrayOf(
                0.11f, 0f, 0f, 0f, 0f,
                0f, 0.22f, 0f, 0f, 0f,
                0f, 0f, 0.33f, 0f, 0f,
                0f, 0f, 0f, 0.44f, 0f
        ));

        val resultMatrix = ColorMatrix(floatArrayOf(
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f
        ));
//        resultMatrix.setConcat(colorMatrix1, colorMatrix2)
//
//        Log.i(TAG, printArray(colorMatrix1.array))
//        Log.i(TAG, printArray(colorMatrix2.array))
//        Log.i(TAG, printArray(resultMatrix.array))

        // 打印出原始的colorMatrix1
        Log.d(TAG, printArray(colorMatrix1.array))

        // preConcat的意义就是将当前矩阵乘以prematrix矩阵
        //postConcat(ColorMatrix postmatrix)
//        colorMatrix1.preConcat(colorMatrix2)

        Log.d(TAG, printArray(colorMatrix2.array))

        colorMatrix2.postConcat(colorMatrix1)

        // 打印出乘后的colorMatrix1矩阵
//        Log.d(TAG, printArray(colorMatrix1.array))

        Log.d(TAG, printArray(colorMatrix2.array))
    }

    private fun printArray(array: FloatArray): String {
        val stringBuilder = StringBuilder("array dump:\n")
        for (i in 0 until array.size) {
            if (i % 5 == 0) {
                stringBuilder.append("\n")
            }
            stringBuilder.append(array[i].toString() + " ")
        }
        return stringBuilder.toString()
    }

    private fun handleColorMatrixBmp(progress: Int): Bitmap {
        // 创建一个相同尺寸的可见的位图区，用于绘制调色后的图片
        val canvas = Canvas(mTempBmp)
        // 新建paint
        val paint = Paint()
        // 设置抗锯齿，即边缘做平滑处理
        paint.isAntiAlias = true

//        // 色彩饱和度矩阵
//        val saturationColorMatrix = ColorMatrix()
//        saturationColorMatrix.setSaturation(progress.toFloat())

        // 色彩旋转矩阵
        // setRotate——色彩旋转
        //上面在讲解色彩旋转运算时，给大家列出了在色彩旋转时的效果和原理，由于涉及到正余弦函数的计算，而且这些公式推导起来相当具有难度，所以Android的大大们，已经给我们封装好了色彩旋转的函数：
        ///**
        // * 将旋转围绕某一个颜色轴旋转
        // * axis=0 围绕红色轴旋转
        // * axis=1 围绕绿色轴旋转
        // * axis=2 围绕蓝色轴旋转
        // */
        //public void setRotate(int axis, float degrees)；
        //这里有两个参数：
        //int axis：表示围绕哪个轴旋转，取值为0，1，2；取0时表示围绕红色轴旋转；取值1时，表示围绕绿色轴旋转；取值2时，表示围绕蓝色轴旋转；
        //float degrees：表示旋转的度数
        val colorRotateMatrix = ColorMatrix()
        // 将当前progress位置减去180，即中间位置的数字。
        // 所以在中间位置的色彩旋转度数为0，整个旋转度数的范围是-180到180；360度正好是正余弦函数的一个最小正周期
        colorRotateMatrix.setRotate(1, (progress - 180).toFloat())

        // 设置颜色变换效果
        paint.colorFilter = ColorMatrixColorFilter(colorRotateMatrix)
        // 将变换颜色后的图片输出到新创建的位图区
        canvas.drawBitmap(mOriginalBmp, 0f, 0f, paint)
        // 返回新建的位图，也即调色处理后的图片
        return mTempBmp;
    }
}
