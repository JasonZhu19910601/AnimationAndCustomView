package com.leading.valueanimatortest.ui

import android.animation.Keyframe
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R

//import kotlinx.android.synthetic.main.activity_animate_layout_changes_and_layout_transition.*

class AnimateLayoutChangesAndLayoutTransitionActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = AnimateLayoutChangesAndLayoutTransitionActivity::class.simpleName

    private lateinit var mLayoutTransition: LayoutTransition


    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.add_btn -> {
//                addBtnView()
//            }
//            R.id.remove_btn -> {
//                removeBtnView()
//            }
//        }
    }

    private fun addBtnView() {
        i++
        val button = Button(this)
        button.text = "btn$i"
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button.layoutParams = layoutParams
//        layoutTransitionGroup.addView(button, 0)
    }

    private fun removeBtnView() {
        if (i > 0) {
//            layoutTransitionGroup.removeViewAt(0)
            i--
        } else {
            Toast.makeText(this, "layoutTransitionGroup已经没有button了", Toast.LENGTH_SHORT).show()
        }
    }

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_animate_layout_changes_and_layout_transition)
//        add_btn.setOnClickListener(this)
//        remove_btn.setOnClickListener(this)

        mLayoutTransition = LayoutTransition()

//        //入场动画:view在这个容器中消失时触发的动画
//        val animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f)
//        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, animIn)
//        //出场动画:view显示时的动画
//        val animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f)
//        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut)

        /*
        * 1、LayoutTransition.CHANGE_APPEARING和LayoutTransition.CHANGE_DISAPPEARING必须使用PropertyValuesHolder所构造的动画才会有效果，
        * 不然无效！也就是说使用ObjectAnimator构造的动画，在这里是不会有效果的！
        *
        * 2、在构造PropertyValuesHolder动画时，”left”、”top”属性的变动是必写的
        *
        * 3、在构造PropertyValuesHolder时，所使用的ofInt,ofFloat中的参数值，
        * 第一个值和最后一个值必须相同，不然此属性所对应的的动画将被放弃，在此属性值上将不会有效果；
        *
        * 4、在构造PropertyValuesHolder时，所使用的ofInt,ofFloat中，如果所有参数值都相同，也将不会有动画效果。
        * */
        // LayoutTransition.CHANGE_APPEARING实现
//        val pvhLeft = PropertyValuesHolder.ofInt("left", 0, 100, 0)
//        val pvhTop = PropertyValuesHolder.ofInt("top", 1, 1)
//        val pvScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 2f, 1f)


        // LayoutTransition.CHANGE_DISAPPEARING实现

        // 第一步：由于left,top属性是必须的，但我们做响铃效果时，是不需要Left，top变动的，所有给他们设置为无效值：
        // (看到了没，必须设置的意思就是即使设置的值是无效的，也要设置！
        // 不然就会由于Left,top属性没有设置而整个PropertyValuesHolder动画无效，好恶心的用法……大家可以在源码注掉哪句话，
        // 或者把上面的所有无效设置尝试一遍，看看效果便知)
        val outLeft = PropertyValuesHolder.ofInt("left", 0, 0)
        val outTop = PropertyValuesHolder.ofInt("top", 0, 0)

        // 第二步：用KeyFrame构造PropertyValuesHolder
        val keyframes = ArrayList<Keyframe>()
        val keyframe0 = Keyframe.ofFloat(0f, 0f)
        keyframes.add(keyframe0)
        val keyframe1 = Keyframe.ofFloat(0.1f, -20f)
        keyframes.add(keyframe1)
        val keyframe2 = Keyframe.ofFloat(0.2f, 20f)
        keyframes.add(keyframe2)
        val keyframe3 = Keyframe.ofFloat(0.3f, -20f)
        keyframes.add(keyframe3)
        val keyframe4 = Keyframe.ofFloat(0.4f, 20f)
        keyframes.add(keyframe4)
        val keyframe5 = Keyframe.ofFloat(0.4f, -20f)
        keyframes.add(keyframe5)
        val keyframe6 = Keyframe.ofFloat(0.4f, 20f)
        keyframes.add(keyframe6)
        val keyframe7 = Keyframe.ofFloat(0.4f, -20f)
        keyframes.add(keyframe7)
        val keyframe8 = Keyframe.ofFloat(0.4f, 20f)
        keyframes.add(keyframe8)
        val keyframe9 = Keyframe.ofFloat(0.4f, -20f)
        keyframes.add(keyframe9)
        val keyframe10 = Keyframe.ofFloat(1f, 0f)
        keyframes.add(keyframe10)

        val mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                keyframe0, keyframe1, keyframe2, keyframe3, keyframe4, keyframe5,
                keyframe6, keyframe7, keyframe8, keyframe9, keyframe10)


//        val changeAppearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, outLeft, outTop)
//        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearAnimator)
//        val changeDisappearingAppearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, outLeft, outTop)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, outLeft, outTop, mPropertyValuesHolder)
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, objectAnimator)
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30)
        mLayoutTransition.addTransitionListener(object : LayoutTransition.TransitionListener {
            override fun startTransition(transition: LayoutTransition, container: ViewGroup, view: View, transitionType: Int) {
                Log.d(TAG, "start:" + "transitionType:" + transitionType + "count:" + container.childCount + "view:" + view::class.java.simpleName);
            }

            override fun endTransition(transition: LayoutTransition, container: ViewGroup, view: View, transitionType: Int) {
                Log.d(TAG, "end:" + "transitionType:" + transitionType + "count:" + container.childCount + "view:" + view::class.java.simpleName);
            }

        })
//        layoutTransitionGroup.layoutTransition = mLayoutTransition
    }


}
