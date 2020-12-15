package com.example.tongpao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tongpao.ui.TongpaoActivity;
import com.example.tongpao.ui.circleview.CircleviewActivity;
import com.example.tongpao.ui.easemob.EaseMobActivity;
import com.example.tongpao.ui.map.MapActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tongpao;
    private Button ditu;
    private Button zdy_view;
    private Button huanxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tongpao = (Button) findViewById(R.id.tongpao);
        ditu = (Button) findViewById(R.id.ditu);
        zdy_view = (Button) findViewById(R.id.zdy_view);
        huanxin = (Button) findViewById(R.id.huanxin);

        tongpao.setOnClickListener(this);
        ditu.setOnClickListener(this);
        zdy_view.setOnClickListener(this);
        huanxin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tongpao:
                Intent intent = new Intent(this, TongpaoActivity.class);
                startActivity(intent);
                break;
            case R.id.ditu:
                Intent intent1 = new Intent(this, MapActivity.class);
                startActivity(intent1);
                break;
            case R.id.zdy_view:
                Intent intent2 = new Intent(this, CircleviewActivity.class);
                startActivity(intent2);
                break;
            case R.id.huanxin:
                Intent intent3 = new Intent(this, EaseMobActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
