package com.leading.valueanimatortest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leading.valueanimatortest.R
import com.leading.valueanimatortest.customview.BezierCurveView
import kotlinx.android.synthetic.main.activity_paint_and_canvas_test.*

class PaintAndCanvasTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint_and_canvas_test)

//        root.addView(MyView(this))
//        root.addView(MyRegionView(this))
//        root.addView(CanvasChangeAndOperationView(this))
//        root.addView(MyDrawTextView(this))
        root.addView(BezierCurveView(this))
        btnReset.setOnClickListener {
            bezierView.reset()
        }
    }
}
