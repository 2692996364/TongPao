package com.example.tongpao.ui.easemob;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;

public class FindUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_userid;
    private Button btn_find;
    private RecyclerView recy_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        initView();
    }

    private void initView() {
        input_userid = (EditText) findViewById(R.id.input_userid);
        btn_find = (Button) findViewById(R.id.btn_find);
        recy_users = (RecyclerView) findViewById(R.id.recy_users);

        btn_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:

                break;
        }
    }

    private void submit() {
        // validate
        String userid = input_userid.getText().toString().trim();
        if (TextUtils.isEmpty(userid)) {
            Toast.makeText(this, "输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
