package com.leading.valueanimatortest.entity

/**
 * @package com.leading.valueanimatortest
 * @fileName Point
 * @date 2018/10/9 15:20
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class Point(private var radius: Int) {

    fun getRadius(): Int {
        return radius
    }

    fun setRadius(radius: Int) {
        this.radius = radius
    }
}