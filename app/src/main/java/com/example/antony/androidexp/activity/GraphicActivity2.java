package com.example.antony.androidexp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GraphicActivity2 extends AppCompatActivity {
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(getApplicationContext());
        setContentView(drawView);
    }

    class DrawView extends View {
        Paint paint;
        RectF rect;
        Path path;
        Path path2;
        Paint paint2;
        Point point1;
        Point point2;
        Point point3;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            rect = new RectF();
            path = new Path();
            path2 = new Path();
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);

            paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            point1 = new Point(40, 70);
            point2 = new Point(40, 170);
            point3 = new Point(90, 260);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.YELLOW);
            path.reset();

            path.moveTo(10,10);
            path.lineTo(10, 50);
            path.lineTo(50,50);

            path.moveTo(60,10);
            path.lineTo(60, 50);
            path.lineTo(100,50);
            path.close();

            rect.set(110, 10, 150, 30);
            path.addRect(rect, Path.Direction.CW);
            path.addCircle(180, 30, 20, Path.Direction.CW);

            canvas.drawPath(path, paint);

            paint.setColor(Color.GREEN);
            path2.moveTo(5, 5);
            path2.lineTo(220, 60);
            path2.moveTo(5, 60);
            path2.lineTo(220, 5);

            canvas.drawPath(path2, paint);

            // кривые Безье

            path.reset();
            paint2.setColor(Color.BLACK);
            canvas.drawLine(5, 150, 200, 150, paint2);
            paint2.setColor(Color.RED);
            canvas.drawCircle(point1.x, point1.y, 10, paint2);

            path.moveTo(5, 150);
            path.quadTo(point1.x, point1.y, 200, 150);
            paint2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint2);

            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(Color.BLACK);
            canvas.drawLine(5, 210, 200, 210, paint2);
            paint2.setColor(Color.BLUE);
            canvas.drawCircle(point2.x, point2.y, 10, paint2);
            canvas.drawCircle(point3.x, point3.y, 10, paint2);

            path.reset();
            path.moveTo(5,210);
            path.cubicTo(point2.x, point2.y, point3.x, point3.y, 200, 210);
            paint2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint2);
        }
    }
}
