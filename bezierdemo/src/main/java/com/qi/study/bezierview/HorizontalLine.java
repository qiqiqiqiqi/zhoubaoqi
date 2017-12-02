package com.qi.study.bezierview;

import android.graphics.PointF;

/**
 * Created by feng on 2017/3/29.
 */

public class HorizontalLine {

    private final float c = 0.551915024494f;
    public PointF leftPoint, middlePoint, rightPoint;

    public HorizontalLine(float middleX, float middleY, float radius) {
        leftPoint = new PointF(-c * radius, middleY);
        middlePoint = new PointF(middleX, middleY);
        rightPoint = new PointF(c * radius, middleY);
    }
    /*public HorizontalLine(float middleX, float middleY, float radius) {
        leftPoint = new PointF(radius - c * radius, middleY);
        middlePoint = new PointF(middleX, middleY);
        rightPoint = new PointF(c * radius + radius, middleY);
    }*/

    public void setY(float y) {
        leftPoint.y = y;
        middlePoint.y = y;
        rightPoint.y = y;
    }
}
