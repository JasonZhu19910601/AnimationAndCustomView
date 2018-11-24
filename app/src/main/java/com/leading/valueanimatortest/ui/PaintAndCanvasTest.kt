package com.leading.valueanimatortest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leading.valueanimatortest.R
import com.leading.valueanimatortest.customview.MyView
import kotlinx.android.synthetic.main.activity_paint_and_canvas_test.*

class PaintAndCanvasTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint_and_canvas_test)

        root.addView(MyView(this))
    }
}
