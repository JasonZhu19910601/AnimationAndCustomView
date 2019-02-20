package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName SaveLayerAlphaView
 * @date 2019/2/20 13:58
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class SaveLayerAlphaView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint: Paint = Paint()

    init {
        mPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(100f, 100f, 300f, 300f, mPaint)

        // 在saveLayerAlpha以后，我们画了一个绿色的矩形，
        // 由于把saveLayerAlpha新建的矩形的透明度是0x88（136）大概是50%透明度，
        // 从效果图中也可以看出在新建图像与上一画布合成后，是具有透明度的。
        val layerId = canvas.saveLayerAlpha(0f, 0f, 600f, 600f, 0x88, Canvas.ALL_SAVE_FLAG)
        mPaint.color = Color.GREEN
        canvas.drawRect(200f, 200f, 400f, 400f, mPaint)
        canvas.restoreToCount(layerId)
    }


}