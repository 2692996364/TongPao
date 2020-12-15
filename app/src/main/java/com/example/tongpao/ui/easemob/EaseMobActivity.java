package com.example.tongpao.ui.easemob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class EaseMobActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_ease;
    private RecyclerView recycler_ease;
    private EditText name_ease;
    private EditText pass_ease;
    ArrayList<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease_mob);
        initView();
    }

    private void initView() {
        login_ease = (Button) findViewById(R.id.login_ease);
        recycler_ease = (RecyclerView) findViewById(R.id.recycler_ease);
        name_ease = (EditText) findViewById(R.id.name_ease);
        pass_ease = (EditText) findViewById(R.id.pass_ease);

        login_ease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_ease:
                login();
                break;
        }
    }

    private void login() {
        String username = name_ease.getText().toString();
        String password = pass_ease.getText().toString();
        Log.d("tag", "login: 环信");
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
        } else {
            EMClient.getInstance().login(username, password, new EMCallBack() {
                @Override
                public void onSuccess() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EaseMobActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    try {
                        List<String> friends = EMClient.getInstance().contactManager().getAllContactsFromServer();
                        userList.clear();
                        if (friends != null) {
                            /*
                            for (int i = 0; i < friends.size(); i++) {
                                userList.add(friends.get(i));
                            }
                            */
                            userList.addAll(friends);
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            Intent intent = new Intent(EaseMobActivity.this, FriendsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("list", userList);
                            intent.putExtra("name", bundle);
                            startActivity(intent);
                        }
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(int code, String error) {
                    Log.d("tag", "onError: " + error);
                    if (code == -1005) {
                        error = "用户名或密码错误";
                    }
                    final String msg = error;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Log.d("main", "登陆聊天服务器失败！");
                            Toast.makeText(EaseMobActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onProgress(int progress, String status) {
                    Log.d("tag", "onProgress: " + status);
                }
            });
        }

    }
}
