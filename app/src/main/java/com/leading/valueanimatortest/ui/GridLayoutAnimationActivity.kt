package com.leading.valueanimatortest.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import kotlinx.android.synthetic.main.activity_grid_layout_animation.*


class GridLayoutAnimationActivity : AppCompatActivity() {
    private lateinit var mGridAdapter: GridAdapter
    private val mData = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout_animation)

        mData.addAll(getData())
        mGridAdapter = GridAdapter()
        grid.adapter = mGridAdapter

        add_data.setOnClickListener {
            addData()
        }

//        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
//        val gridLayoutAnimationController = GridLayoutAnimationController(animation)
//        gridLayoutAnimationController.columnDelay = 0.75f
//        gridLayoutAnimationController.rowDelay = 0.5f
//        gridLayoutAnimationController.direction = (GridLayoutAnimationController.DIRECTION_BOTTOM_TO_TOP and DIRECTION_LEFT_TO_RIGHT)
//        grid.layoutAnimation = gridLayoutAnimationController
//        grid.startLayoutAnimation()
    }

    private fun addData() {
        mData.addAll(getData())
        mGridAdapter.notifyDataSetChanged()
    }

    /**
     * 创建数据源
     */
    private fun getData(): ArrayList<String> {
        val data = ArrayList<String>()
        for (i in 1..34) {
            data.add("DATA $i")
        }
        return data;
    }

    /**
     * 内部类可以访问外部类成员,可看作外部类对象的一个成员
     */
    inner class GridAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(parent?.context)
            textView.text = mData.get(position)
            textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return textView
        }

        override fun getItem(position: Int): Any {
            return Any();
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getCount(): Int {
            return mData.size
        }
    }

}
