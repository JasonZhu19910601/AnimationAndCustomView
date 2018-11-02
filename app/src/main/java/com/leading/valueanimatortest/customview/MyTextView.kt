package com.leading.valueanimatortest.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName MyTextView
 * @date 2018/10/10 11:32
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyTextView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setCharText(character: Char?) {
        text = character.toString()
    }

}