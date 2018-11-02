package com.leading.valueanimatortest.evaluator

import android.animation.TypeEvaluator

/**
 * @package com.leading.valueanimatortest
 * @fileName CharEvaluator
 * @date 2018/10/9 15:04
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class CharEvaluator : TypeEvaluator<Char> {
    override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
        val startInt = startValue.toInt()
        val endInt = endValue.toInt()
        val curInt = (startInt + fraction * (endInt - startInt)).toInt()
        val result = curInt.toChar()
        return result
    }
}