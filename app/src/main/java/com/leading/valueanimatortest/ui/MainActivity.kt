package com.leading.valueanimatortest.ui

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import com.leading.valueanimatortest.evaluator.CharEvaluator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener { it ->
            //            val animator = ObjectAnimator.ofFloat(tv, "alpha", 1f, 0f, 1f)
//            val animator = ObjectAnimator.ofFloat(tv, "rotation", 0f, 180f, 0f)
//            val animator = ObjectAnimator.ofFloat(tv, "rotationY", 0f, 180f, 0f)
//            val animator = ObjectAnimator.ofFloat(tv, "rotationX", 0f, 180f, 0f)
//            val animator = ObjectAnimator.ofFloat(tv, "rotation", 0f, 270f, 0f)
//            val animator = ObjectAnimator.ofFloat(tv,
//                    "translationX", 0f, 200f, -200F, 0f)
//            val animator = ObjectAnimator.ofFloat(tv,
//                    "translationY", 0f, 200f, -200F, 0f)
//            val animator = ObjectAnimator.ofFloat(tv,
//                    "scaleX", 0f, 3f, 1f)
//            val animator = ObjectAnimator.ofFloat(tv,
//                    "scaleY", 0f, 3f, 1f)
//            animator.duration = 2000
//            animator.start()
//            pointview.doPointAnim()
//            //            val valueAnimator = ValueAnimator.ofInt(0, 600)
////            val valueAnimator = ValueAnimator.ofInt(0xffffff00.toInt(), 0xff0000ff.toInt())
//            val valueAnimator = ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z')
//            valueAnimator.addUpdateListener {
//                val curValue = it.animatedValue
////                tv.layout(tv.left, curValue as Int, tv.right, curValue + tv.height)
////                tv.setBackgroundColor(curValue as Int)
//                tv.text = curValue.toString()
//            }
//            valueAnimator.duration = 3000
//            valueAnimator.interpolator = AccelerateInterpolator()
////            valueAnimator.interpolator = BounceInterpolator()
////            valueAnimator.interpolator = MyInterpolator()
////            valueAnimator.setEvaluator(MyEvalutor())
////            valueAnimator.setEvaluator(ReverseEvaluator())
////            valueAnimator.setEvaluator(ArgbEvaluator())
//            valueAnimator.start()

//            pointview.doPointViewObjectAnim()

//            val animator = ObjectAnimator.ofInt(tv, "BackgroundColor",
//                    0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())
//            animator.setEvaluator(ArgbEvaluator())
//            animator.duration = 8000
//            animator.start()

//            val rotationHolder = PropertyValuesHolder.ofFloat("rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f)
//            val colorHolder = PropertyValuesHolder.ofInt("backgroundColor", 0xffffffff.toInt(), 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffffffff.toInt())
//            val animator = ObjectAnimator.ofPropertyValuesHolder(tv, rotationHolder, colorHolder)
//            animator.duration = 1000
//            animator.interpolator = AccelerateInterpolator()
//            animator.start()
//            doObjectAnim()
//            doOfFloatAnim()
//            doKeyframeObjectAnim()
            doRingAnim()
        }
    }

    fun doObjectAnim() {
        val charHolder = PropertyValuesHolder.ofObject("CharText", CharEvaluator(), 'A', 'Z')
        val animator = ObjectAnimator.ofPropertyValuesHolder(tv, charHolder)
        animator.duration = 3000
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

    fun doOfFloatAnim() {
//        val keyframe0 = Keyframe.ofFloat(0f, 0f)
//        val keyframe1 = Keyframe.ofFloat(0.1f, -20f)
//        val keyframe2 = Keyframe.ofFloat(0.2f, 20f)
//        val keyframe3 = Keyframe.ofFloat(0.3f, -20f)
//        val keyframe4 = Keyframe.ofFloat(0.4f, 20f)
//        val keyframe5 = Keyframe.ofFloat(0.5f, -20f)
//        val keyframe6 = Keyframe.ofFloat(0.6f, 20f)
//        val keyframe7 = Keyframe.ofFloat(0.7f, -20f)
//        val keyframe8 = Keyframe.ofFloat(0.8f, 20f)
//        val keyframe9 = Keyframe.ofFloat(0.9f, -20f)
//        val keyframe10 = Keyframe.ofFloat(1f, 0f)
//        val frameHolder = PropertyValuesHolder.ofKeyframe("rotation",
//                keyframe0,
//                keyframe1,
//                keyframe2,
//                keyframe3,
//                keyframe4,
//                keyframe5,
//                keyframe6,
//                keyframe7,
//                keyframe8,
//                keyframe9,
//                keyframe10
//        )
//        val animator = ObjectAnimator.ofPropertyValuesHolder(tv, frameHolder)
//        animator.duration = 1000
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.start()

        // 没有插值器
        val keyframe0 = Keyframe.ofFloat(0f, 0f)
        val keyframe1 = Keyframe.ofFloat(0.5f, 100f)
        val keyframe2 = Keyframe.ofFloat(1f)
        keyframe2.value = 0f
        // 使用插值器
        keyframe2.interpolator = BounceInterpolator()
        val rotationFrameHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe0, keyframe1, keyframe2)
        ObjectAnimator.ofPropertyValuesHolder(tv, rotationFrameHolder).setDuration(2000).start()
    }

    fun doKeyframeObjectAnim() {
        val keyframe0 = Keyframe.ofObject(0f, 'A')
        val keyframe1 = Keyframe.ofObject(0.1f, 'M')
        val keyframe2 = Keyframe.ofObject(1f, 'Z')
        val frameHolder = PropertyValuesHolder.ofKeyframe("charText", keyframe0, keyframe1, keyframe2)
        frameHolder.setEvaluator(CharEvaluator())
        ObjectAnimator.ofPropertyValuesHolder(tv, frameHolder).setDuration(3000).start()
    }

    /**
     * 响铃效果
     */
    fun doRingAnim() {
        // 左右振动
        val keyframe0 = Keyframe.ofFloat(0f, 0f)
        val keyframe1 = Keyframe.ofFloat(0.1f, -20f)
        val keyframe2 = Keyframe.ofFloat(0.2f, 20f)
        val keyframe3 = Keyframe.ofFloat(0.3f, -20f)
        val keyframe4 = Keyframe.ofFloat(0.4f, 20f)
        val keyframe5 = Keyframe.ofFloat(0.5f, -20f)
        val keyframe6 = Keyframe.ofFloat(0.6f, 20f)
        val keyframe7 = Keyframe.ofFloat(0.7f, -20f)
        val keyframe8 = Keyframe.ofFloat(0.8f, 20f)
        val keyframe9 = Keyframe.ofFloat(0.9f, -20f)
        val keyframe10 = Keyframe.ofFloat(1f, 0f)
        val frameHolder1 = PropertyValuesHolder.ofKeyframe("rotation",
                keyframe0,
                keyframe1,
                keyframe2,
                keyframe3,
                keyframe4,
                keyframe5,
                keyframe6,
                keyframe7,
                keyframe8,
                keyframe9,
                keyframe10
        )

        // 横向放大1.1倍
        val scaleXKeyframe0 = Keyframe.ofFloat(0f, 1f)
//        val scaleXKeyframe1 = Keyframe.ofFloat(0.1f, 1.1f)
//        val scaleXKeyframe2 = Keyframe.ofFloat(0.2f, 1.1f)
//        val scaleXKeyframe3 = Keyframe.ofFloat(0.3f, 1.1f)
//        val scaleXKeyframe4 = Keyframe.ofFloat(0.4f, 1.1f)
        val scaleXKeyframe5 = Keyframe.ofFloat(0.5f, 1.1f)
//        val scaleXKeyframe6 = Keyframe.ofFloat(0.6f, 1.1f)
//        val scaleXKeyframe7 = Keyframe.ofFloat(0.7f, 1.1f)
//        val scaleXKeyframe8 = Keyframe.ofFloat(0.8f, 1.1f)
//        val scaleXKeyframe9 = Keyframe.ofFloat(0.9f, 1.1f)
        val scaleXKeyframe10 = Keyframe.ofFloat(1f, 1f)
        val frameHolder2 = PropertyValuesHolder.ofKeyframe("scaleX",
                scaleXKeyframe0,
//                scaleXKeyframe1,
//                scaleXKeyframe2,
//                scaleXKeyframe3,
//                scaleXKeyframe4,
                scaleXKeyframe5,
//                scaleXKeyframe6,
//                scaleXKeyframe7,
//                scaleXKeyframe8,
//                scaleXKeyframe9,
                scaleXKeyframe10
        )

        // 纵向放大1.1倍
        val scaleYKeyframe0 = Keyframe.ofFloat(0f, 1f)
//        val scaleYKeyframe1 = Keyframe.ofFloat(0.1f, 1.1f)
//        val scaleYKeyframe2 = Keyframe.ofFloat(0.2f, 1.1f)
//        val scaleYKeyframe3 = Keyframe.ofFloat(0.3f, 1.1f)
//        val scaleYKeyframe4 = Keyframe.ofFloat(0.4f, 1.1f)
        val scaleYKeyframe5 = Keyframe.ofFloat(0.5f, 1.1f)
//        val scaleYKeyframe6 = Keyframe.ofFloat(0.6f, 1.1f)
//        val scaleYKeyframe7 = Keyframe.ofFloat(0.7f, 1.1f)
//        val scaleYKeyframe8 = Keyframe.ofFloat(0.8f, 1.1f)
//        val scaleYKeyframe9 = Keyframe.ofFloat(0.9f, 1.1f)
        val scaleYKeyframe10 = Keyframe.ofFloat(1f, 1f)
        val frameHolder3 = PropertyValuesHolder.ofKeyframe("scaleY",
                scaleYKeyframe0,
//                scaleYKeyframe1,
//                scaleYKeyframe2,
//                scaleYKeyframe3,
//                scaleYKeyframe4,
                scaleYKeyframe5,
//                scaleYKeyframe6,
//                scaleYKeyframe7,
//                scaleYKeyframe8,
//                scaleYKeyframe9,
                scaleYKeyframe10
        )
        // 测试
        val animator = ObjectAnimator.ofPropertyValuesHolder(tv, frameHolder1, frameHolder2, frameHolder3)
        animator.duration = 2000
        animator.start()
    }
}
