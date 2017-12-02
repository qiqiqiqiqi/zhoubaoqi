package com.qi.amination.amination

import android.graphics.Camera
import android.graphics.Matrix
import android.util.Log
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.Transformation

/**
 * Created by 13324 on 2016/10/14.
 */
class CustomAmination : Animation() {

    private var mCenterWidth: Int = 0
    private var mCenterHeight: Int = 0
    private var mCamera: Camera? = null
    private val mRotateY = 0f
    private val mRotateX = 90f
    private val mRotateZ = 0f

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        duration = 2000
        fillAfter = false
        interpolator = BounceInterpolator()
        Log.d("CustomAmination", "width=$width,height=$height")
        mCenterWidth = width / 2
        mCenterHeight = height / 2
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        super.applyTransformation(interpolatedTime, t)
        val matrix = t.matrix
        mCamera!!.save()
        //  mCamera.translate(0.f, 0.f, 1300 * interpolatedTime);
        mCamera!!.rotateY(mRotateY * interpolatedTime)
        mCamera!!.rotateX(mRotateX * interpolatedTime)
        mCamera!!.rotateZ(mRotateZ * interpolatedTime)
        mCamera!!.getMatrix(matrix)
        mCamera!!.restore()
        matrix.preTranslate(mCenterWidth.toFloat(), mCenterHeight.toFloat())
        matrix.postTranslate((-mCenterWidth).toFloat(), (-mCenterWidth).toFloat())

    }
}
