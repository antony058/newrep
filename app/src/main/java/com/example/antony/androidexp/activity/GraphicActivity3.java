package com.example.antony.androidexp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GraphicActivity3 extends AppCompatActivity {
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
        Point point1;
        Point point2;
        String text;

        public DrawView(Context context) {
            super(context);
            text = "Draw the text, with origin at (x,y) abc";
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(2);
            paint.setTextSize(12);
            path = new Path();
            point1 = new Point(90, 200);
            point2 = new Point(140, 150);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            path.addCircle(100, 60, 50, Path.Direction.CW);
            paint.setColor(Color.BLACK);
            canvas.drawTextOnPath(text, path, 0, 0, paint);

            path.reset();
            path.moveTo(40, 180);
            path.cubicTo(point1.x, point1.y, point2.x, point2.y, 230, 180);
            //paint.setTextSize(12);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            canvas.drawTextOnPath(text, path, 0, 0, paint);
            canvas.drawPath(path, paint);
        }
    }
}
