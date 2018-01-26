package com.example.antony.androidexp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GraphicActivity5 extends AppCompatActivity {
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(getApplicationContext());
        setContentView(drawView);
    }

    class DrawView extends View {
        Path path;
        Paint paint;
        Matrix matrix;
        RectF rectBounds;
        RectF rectDst;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            path = new Path();
            matrix = new Matrix();
            rectBounds = new RectF();
            rectDst = new RectF();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            path.reset();
            path.moveTo(150, 10);
            path.lineTo(200, 50);
            path.lineTo(100, 50);
            path.close();
            path.addRect(100, 50, 200, 150, Path.Direction.CW);
            path.addRect(120, 70, 180, 130, Path.Direction.CW);
            canvas.drawPath(path, paint);

            // охватывающий прямоугольник для исходного домика
            path.computeBounds(rectBounds, true);
            paint.setColor(Color.RED);
            canvas.drawRect(rectBounds, paint);

            // охват. прям. для целевого домика
            rectDst.set(20, 160, 230, 210);
            paint.setColor(Color.BLACK);
            canvas.drawRect(rectDst, paint);

            matrix.reset();
            matrix.setRectToRect(rectBounds, rectDst, Matrix.ScaleToFit.CENTER);
            path.transform(matrix);
            paint.setColor(Color.BLUE);
            canvas.drawPath(path, paint);

            // filling rect
            paint.setColor(Color.BLACK);
            path.computeBounds(rectBounds, true);
            rectDst.offset(0, 60);
            canvas.drawRect(rectDst, paint);
            matrix.reset();
            matrix.setRectToRect(rectBounds, rectDst, Matrix.ScaleToFit.FILL);
            path.transform(matrix);
            paint.setColor(Color.BLUE);
            canvas.drawPath(path, paint);
        }
    }
}
