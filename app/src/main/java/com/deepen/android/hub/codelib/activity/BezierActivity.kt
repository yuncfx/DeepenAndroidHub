package com.deepen.android.hub.codelib.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.deepen.android.hub.R
import com.deepen.android.hub.codelib.view.BezierPathAnimatorView


class BezierActivity constructor() : AppCompatActivity() {
    private var animatorView: BezierPathAnimatorView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier)

//        final BezierPathPointView bezierPathPointView = (BezierPathPointView) findViewById(R.id.bezier_path_point_view);
//
//        findViewById(R.id.reset_point_view).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                bezierPathPointView.reset();
//            }
//        });
        animatorView = findViewById<View>(R.id.bezier_path_animator_view) as BezierPathAnimatorView?
        animatorView!!.startAnim()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (animatorView != null) {
            animatorView!!.cancelAnim()
        }
    }
}