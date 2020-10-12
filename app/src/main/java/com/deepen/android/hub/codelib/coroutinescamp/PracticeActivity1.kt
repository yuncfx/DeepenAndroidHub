package com.deepen.android.hub.codelib.coroutinescamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deepen.android.hub.R
import kotlinx.android.synthetic.main.activity_practice1.*

class PracticeActivity1 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_practice1)

    val data = getData()
    val processedData = processData(data)
    // 练习内容：用协程让上面 ↑ 这两行放在后台执行，然后把代码截图贴到腾讯课堂的作业里
    textView.text = processedData
  }

  // 耗时函数 1
  private fun getData(): String {
    // 假设这个函数比较耗时，需要放在后台
    return "hen_coder"
  }

  // 耗时函数 2
  private fun processData(data: String): String {
    // 假设这个函数也比较耗时，需要放在后台
    return data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
      .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
      .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
  }
}