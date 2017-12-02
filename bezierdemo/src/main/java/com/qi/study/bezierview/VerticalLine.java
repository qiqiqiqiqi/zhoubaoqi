package com.qi.study.bezierview;

import android.graphics.PointF;

/**
 * Created by feng on 2017/3/29.
 */

public class VerticalLine {

    private final float c = 0.551915024494f;
    public PointF topPoint, middlePoint, bottomPoint;

    public VerticalLine(float middleX, float middleY, float radius) {
        topPoint = new PointF(middleX, -c * radius);
        middlePoint = new PointF(middleX, middleY);
        bottomPoint = new PointF(middleX, c * radius);
    }
//    public VerticalLine(float middleX, float middleY, float radius) {
//        topPoint = new PointF(middleX, radius - c * radius);
//        middlePoint = new PointF(middleX, middleY);
//        bottomPoint = new PointF(middleX, radius + c * radius);
//    }

    public void setX(float x) {
        topPoint.x = x;
        middlePoint.x = x;
        bottomPoint.x = x;
    }
}
