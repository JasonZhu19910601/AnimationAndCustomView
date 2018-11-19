package com.leading.valueanimatortest.ui

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Preconditions.checkNotNull
import com.leading.valueanimatortest.R
import kotlinx.android.synthetic.main.activity_anim_set.*

class AnimSetActivity : AppCompatActivity() {
    public companion object {
        const val TAG = "AnimSetActivity"
    }

    private lateinit var animatorSet: AnimatorSet;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_set)
        btn.setOnClickListener {
            //            doPlaySequentiallyAnimator()
//            animatorSet = doListenerAnim()
//            animatorSet = testAnimatorSet()
//            animatorSet = testSetTarget()
            animatorSet = testSetStartDelay1()
        };

        btn_cancel.setOnClickListener {
            animatorSet.cancel()
        }
    }

    /**
     * ObjectAnimatorSet  顺序执行
     */
    private fun doPlaySequentiallyAnimator() {
        val tv1BgAnimator = ObjectAnimator.ofInt(tv_1, "BackgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())
//        tv1BgAnimator.repeatCount=ObjectAnimator.INFINITE
//        tv1BgAnimator.startDelay = 2000

        val tv1TranslateYAnimator = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 300f, 0f)
//        tv1TranslateYAnimator.startDelay = 2000
//        tv1TranslateYAnimator.repeatCount = ObjectAnimator.INFINITE

        val tv2TranslateYAnimator = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)
//        tv2TranslateYAnimator.startDelay = 2000
//        tv2TranslateYAnimator.repeatCount = ObjectAnimator.INFINITE

        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(tv1BgAnimator, tv1TranslateYAnimator, tv2TranslateYAnimator)
//        animatorSet.playTogether(tv1BgAnimator, tv1TranslateYAnimator, tv2TranslateYAnimator)
//        animatorSet.duration = 2000

        // 更方便的组合动画 AnimatorSet.Builder
        //        AnimatorSet.Builder还有一些函数，声明如下：
        //和前面动画一起执行
        //        public Builder with(Animator anim)
        //执行前面的动画后才执行该动画
        //        public Builder before(Animator anim)
        //执行先执行这个动画再执行前面动画
        //        public Builder after(Animator anim)
        //延迟n毫秒之后执行动画
        //        public Builder after(long delay)

//        方式一：使用builder对象逐个添加动画
//        val builder = animatorSet.play(tv1TranslateYAnimator)
//        builder.with(tv2TranslateYAnimator)
//        builder.after(tv1BgAnimator)
//        animatorSet.start()
        // 方式二：串行方式
        animatorSet.play(tv1TranslateYAnimator).with(tv2TranslateYAnimator).after(tv1BgAnimator)
        animatorSet.duration = 2000
        animatorSet.start()
    }

    fun doListenerAnim(): AnimatorSet {
        val tv1BgAnimator = ObjectAnimator.ofInt(tv_1, "BackgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())

        val tv1TranslateYAnimator = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 300f, 0f)

        val tv2TranslateYAnimator = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)
        tv2TranslateYAnimator.repeatCount = ObjectAnimator.INFINITE

        val animatorSet = AnimatorSet()
        animatorSet.play(tv1TranslateYAnimator).with(tv2TranslateYAnimator).after(tv1BgAnimator)
        animatorSet.duration = 2000

        // 添加listener
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
                Log.d(TAG, "animator repeat")
            }

            override fun onAnimationEnd(p0: Animator?) {
                Log.d(TAG, "animator end")
            }

            override fun onAnimationCancel(p0: Animator?) {
                Log.d(TAG, "animator cancel")
            }

            override fun onAnimationStart(p0: Animator?) {
                Log.d(TAG, "animator start")
            }
        })

        animatorSet.start()
        return animatorSet
    }

    private fun testAnimatorSet(): AnimatorSet {
        val tv1TranslationY = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 400f, 0f)
        tv1TranslationY.duration = 2000000
        tv1TranslationY.interpolator = BounceInterpolator()

        val tv2TranslationY = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)
        tv2TranslationY.interpolator = AccelerateDecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.duration = 2000
        animatorSet.play(tv1TranslationY).with(tv2TranslationY)
        animatorSet.start()
        return animatorSet
    }

    /**
     * 测试 AnimatorSet 的 setTarget() 函数
     * 在这段代码中，我们给tv1设置了改变背景色，给tv2设置了上下移动。但由于我们通过animatorSet.setTarget(mTv2);
     * 将各个动画的目标控件设置为mTv2，所以tv1将不会有任何动画，所有的动画都会发生在tv2上。
     *
     * 所以AnimatorSet.setTarget()的作用就是将动画的目标统一设置为当前控件，
     * AnimatorSet中的所有动画都将作用在所设置的target控件上
     */
    private fun testSetTarget(): AnimatorSet {
        val tv1BgAnim = ObjectAnimator.ofInt(tv_1, "BackgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt(), 0xffff00ff.toInt())
        val tv2TranslationYAnim = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(tv1BgAnim, tv2TranslationYAnim)
        animatorSet.duration = 2000
        animatorSet.setTarget(tv_2)
        animatorSet.start()
        return animatorSet
    }

    /**
     * AnimatorSet的延时是仅针对性的延长AnimatorSet激活时间的，对单个动画的延时设置没有影响。
     */
    private fun testSetStartDelay1(): AnimatorSet {
        var tv1TranslateY = ObjectAnimator.ofFloat(tv_1, "translationY", 0f, 400f, 0f);
        var tv2TranslateY = ObjectAnimator.ofFloat(tv_2, "translationY", 0f, 400f, 0f);
        tv2TranslateY.startDelay = 2000;

        animatorSet = AnimatorSet()
        animatorSet.play(tv2TranslateY).with(tv1TranslateY);
        animatorSet.startDelay = 2000;
        animatorSet.duration = 2000;
        animatorSet.start()
        return animatorSet
    }
}
