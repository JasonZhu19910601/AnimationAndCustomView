package com.leading.valueanimatortest.evaluator

import android.animation.TypeEvaluator


/**
 * @package com.leading.valueanimatortest
 * @fileName ReverseEvaluator
 * @date 2018/10/8 17:24
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class ReverseEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return (endValue - fraction * (endValue - startValue)).toInt()
    }

}