package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName MATRIX_SAVE_FLAG_View
 * @date 2019/2/20 14:51
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MATRIX_SAVE_FLAG_View(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var mPaint: Paint = Paint()

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mPaint.color = Color.GREEN
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.save(Canvas)
    }
}