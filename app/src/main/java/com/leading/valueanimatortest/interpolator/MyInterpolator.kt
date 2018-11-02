package com.leading.valueanimatortest.interpolator

import android.animation.TimeInterpolator

/**
 * @package com.leading.valueanimatortest
 * @fileName MyInterpolator
 * @date 2018/10/8 16:54
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyInterpolator : TimeInterpolator {
    override fun getInterpolation(intput: Float): Float {
        return 1 - intput
    }
}