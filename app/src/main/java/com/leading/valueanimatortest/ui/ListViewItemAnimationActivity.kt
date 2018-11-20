package com.leading.valueanimatortest.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import com.leading.valueanimatortest.adapter.ListAdapter
import kotlinx.android.synthetic.main.activity_list_view_item_animation.*

class ListViewItemAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_item_animation)

        val drawables = ArrayList<Drawable>()
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))
        drawables.add(resources.getDrawable(R.drawable.ic_launcher_background))

        val adapter = ListAdapter(drawables, 9, this, list)
        list.adapter = adapter
    }
}
