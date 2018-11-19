package com.leading.valueanimatortest.ui

import android.animation.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import kotlinx.android.synthetic.main.activity_xmlanimset.*

class XMLAnimsetActivity : AppCompatActivity() {
    lateinit var animator: Animator
    lateinit var animatorSet: AnimatorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xmlanimset)
        btn_start_anim.setOnClickListener {
            //            animator = startValueAnimatorFromXml()
//            animator = startObjectAnimatorFromXml()
            animatorSet = startAnimatorSetFromXml()
        }
        btn_cancel_anim.setOnClickListener {
            //            if (animator.isRunning) {
//                animator.cancel()
//            }
            if (animatorSet.isRunning) {
                animatorSet.cancel()
            }
        }
    }

    /**
     * 来自xml的ValueAnimator
     */
    private fun startValueAnimatorFromXml(): Animator {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.animator) as ValueAnimator;
        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val offset: Int = animation?.animatedValue as Int
                tv1.layout(offset, offset, tv1.width + offset, tv1.height + offset)
            }
        })
        animator.start()
        return animator
    }

    /**
     * 来自xml的 ObjectAnimator
     */
    private fun startObjectAnimatorFromXml(): Animator {
        val objectAnimator = AnimatorInflater.loadAnimator(this,
//                R.animator.object_animator
                R.animator.color_object_animator
        ) as ObjectAnimator
        objectAnimator.target = tv1
        objectAnimator.start()
        return objectAnimator
    }

    /**
     * 加载来自xml的 AnimatorSet
     */
    private fun startAnimatorSetFromXml(): AnimatorSet {
        val animatorSet = AnimatorInflater.loadAnimator(this, R.animator.set_animator) as AnimatorSet
        animatorSet.setTarget(tv1)
        animatorSet.start()
        return animatorSet
    }
}
