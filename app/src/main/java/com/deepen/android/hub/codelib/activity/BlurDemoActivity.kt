package com.deepen.android.hub.codelib.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.deepen.android.hub.codelib.view.ItemBgView

class BlurDemoActivity constructor() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemBgView: ItemBgView = ItemBgView(this, 2, ItemBgView.MIDDLE_STYLE, true)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(400, 500)
        itemBgView.setLayoutParams(layoutParams)
        setContentView(itemBgView)

//        setContentView(R.layout.activity_blur_demo);

//        findViewById(R.id.iv_exit_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BlurDemoActivity.this.finish();
//            }
//        });
    }
}