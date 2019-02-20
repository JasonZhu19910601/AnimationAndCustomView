package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.leading.valueanimatortest.R

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName SaveLayerUseExample_3_1
 * @date 2019/2/20 11:46
 * @author Zj
 * @describe 1.saveLayer后的所有动作都只对新建画布有效
 *            2.
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class SaveLayerUseExample_3_1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var mPaint: Paint = Paint()
    private var mBitmap: Bitmap

    init {
        mPaint.color = Color.RED
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.sample)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBitmap, 0F, 0F, mPaint)

//        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), mPaint, Canvas.ALL_SAVE_FLAG)
//        canvas.skew(1.732f, 0f)
//        canvas.drawRect(0f, 0f, 150f, 160f, mPaint)
//        canvas.restoreToCount(layerId)

        // 通过Rect指定矩形大小就是新建的画布大小
        val layerId = canvas.saveLayer(0f, 0f, 100f, 100f, mPaint, Canvas.ALL_SAVE_FLAG)
        canvas.drawRect(0f, 0f, 500f, 600f, mPaint)
        canvas.restoreToCount(layerId)
    }


}