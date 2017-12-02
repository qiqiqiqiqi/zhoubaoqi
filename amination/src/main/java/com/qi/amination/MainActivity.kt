package com.qi.amination

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.TextView

import com.qi.amination.amination.CustomAmination

class MainActivity : Activity(), View.OnClickListener {

    private var mTextview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mTextview = findViewById(R.id.textviwe) as TextView
        mTextview!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val customAmination = CustomAmination()
        customAmination.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        mTextview!!.startAnimation(customAmination)

    }
}
