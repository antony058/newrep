package com.example.antony.androidexp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GraphicActivity extends AppCompatActivity {
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(getApplicationContext());
        setContentView(drawView);

    }

    class DrawView extends View {
        private Paint paint;
        private RectF rect;
        private float[] points = {100, 100, 50, 100, 50, 100, 200, 200, 200, 200, 100, 200, 200, 100, 200, 200, 50, 100};

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            rect = new RectF();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
            canvas.drawPoint(10, 10, paint);
            canvas.drawPoint(20, 10, paint);
            canvas.drawLine(10, 20, 120, 80, paint);

            paint.setColor(Color.CYAN);
            canvas.drawCircle(180, 50, 40, paint);

            rect.set(150, 100, 200, 150);
            canvas.drawRect(rect, paint);

            paint.setColor(Color.RED);
            canvas.drawLines(points, paint);

            rect.set(200, 200, 240, 240);
            canvas.drawRoundRect(rect, 10, 20, paint);
            rect.offset(0, 50);
            rect.inset(0, -10);
            canvas.drawOval(rect, paint);

            rect.offset(-50, 0);
            canvas.drawArc(rect, 0, 300, true, paint);
            rect.offset(-50, 0);
            canvas.drawArc(rect, 30, 200, false, paint);

            paint.setColor(Color.BLUE);
            canvas.drawLine(80, 150, 80, 250, paint);

            paint.setColor(Color.YELLOW);
            paint.setTextSize(14);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("align right", 80, 170, paint);

            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("align center", 80, 190, paint);

            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("align left", 80, 210, paint);

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, paint);
        }
    }
}
