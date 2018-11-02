package com.leading.valueanimatortest.evaluator

import android.animation.TypeEvaluator

/**
 * @package com.leading.valueanimatortest
 * @fileName MyEvalutor
 * @date 2018/10/8 17:10
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyEvalutor : TypeEvaluator<Int> {
    /*float fraction, Integer startValue, Integer endValue*/
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        val startInt = startValue
        return (200 + startInt + (endValue - startInt) * fraction).toInt()
    }
}