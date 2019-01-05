package natto.com.othelloapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity(){

    private val containerView: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.container)
    }

    /** 盤面の情報を格納しておく **/
    /** 0 -> 無 **/
    /** 1 -> 白 **/
    /** 2 -> 黒 **/
    /** 0で初期化 **/
    private val fieldArray = Array(8) {
        Array(8) {
            0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reload(this)
    }

    private fun reload(context: Context?) {
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        params.weight = 1f

        containerView.removeAllViews()

        for (y in 0..7) {
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.layoutParams = params
            for (x in 0..7) {
                val btn = Button(context)
                btn.layoutParams = params
                btn.text = fieldArray[y][x].toString()
                btn.tag = (y * 8) + x //0~63
                btn.setOnClickListener(onFieldClick)
                linearLayout.addView(btn)
            }
            containerView.addView(linearLayout)
        }
    }

    private val onFieldClick = View.OnClickListener {
        val tag = it.tag.toString().toInt()
        val y = tag / 8 //0~7
        val x = tag % 8 //0~7
        val nowState = fieldArray[y][x]
        if (nowState == 2) {
            fieldArray[y][x] = 0
        } else {
            fieldArray[y][x] = nowState + 1
        }
        reload(it.context)
    }
}
