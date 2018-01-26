package com.example.antony.androidexp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GraphicActivity4 extends AppCompatActivity {
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(getApplicationContext());
        setContentView(drawView);
    }

    class DrawView extends View {
        Paint paint;
        Path path;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            path = new Path();
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);

            path.reset();
            path.moveTo(150, 10);
            path.lineTo(200, 50);
            path.lineTo(100, 50);
            path.close();
            path.addRect(100, 50, 200, 150, Path.Direction.CW);
            path.addRect(120, 70, 180, 130, Path.Direction.CW);
            canvas.drawPath(path, paint);

            matrix.reset();
            matrix.setTranslate(-30, 150);
            matrix.preSkew(0, 0.5f, 150, 10);
            //matrix.setScale(1.5f, 2, 150, 10);
            //matrix.setRotate(30, 150, 10);

            path.transform(matrix);
            paint.setColor(Color.RED);
            canvas.drawPath(path, paint);
        }
    }
}
