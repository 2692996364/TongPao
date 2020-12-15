package com.example.tongpao.ui.circleview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tongpao.R;


public class CircleviewActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circleview);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.pcg);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bmp = bd.getBitmap();
        Bitmap bitmap = createWatermark(bmp, "落红不是无情物，化作春泥更护花");

        img.setImageBitmap(bitmap);
    }

    private Bitmap createWatermark(Bitmap bitmap, String mark) {
//        float scale = this.getResources().getDisplayMetrics().density;
        //创建一样大小的图片
        Bitmap newBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建画布
        Canvas canvas = new Canvas(newBmp);
        canvas.drawBitmap(bitmap, 0, 0, null);  //绘制原始图片
        canvas.save();
        canvas.rotate(45); //顺时针转45度
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED); //白色半透明
        paint.setAlpha(80);
        paint.setTextSize(40);
        paint.setShadowLayer(1f, 0f, 1f, Color.RED);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        Rect rectText = new Rect();  //得到text占用宽高， 单位：像素
        paint.getTextBounds(mark, 0, mark.length(), rectText);
        double beginX = (bitmap.getHeight() / 2 - rectText.width() / 2) * 1.4;  //45度角度值是1.414
        double beginY = (bitmap.getWidth() / 2 - rectText.width() / 2) * 1.4;
        canvas.drawText(mark, (int) beginX, (int) beginY, paint);
        canvas.restore();
        return newBmp;
    }
}
