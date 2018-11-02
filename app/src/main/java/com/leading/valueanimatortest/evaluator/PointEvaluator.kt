package com.leading.valueanimatortest.evaluator

import android.animation.TypeEvaluator
import com.leading.valueanimatortest.entity.Point

/**
 * @package com.leading.valueanimatortest.evaluator
 * @fileName `PointEvaluator`
 * @date 2018/10/9 15:38
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        val start = startValue.getRadius()
        val end = endValue.getRadius()
        val curValue = (start + fraction * (end - start)).toInt()
        return Point(curValue)
    }
}