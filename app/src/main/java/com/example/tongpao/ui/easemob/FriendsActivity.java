package com.example.tongpao.ui.easemob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recycler_ease;
    private List<String> userList;
    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        initView();
    }

    private void initView() {
        recycler_ease = (RecyclerView) findViewById(R.id.recycler_ease);
        userList = new ArrayList<>();
        Bundle bundle = getIntent().getBundleExtra("name");
        userList = bundle.getStringArrayList("list");
        initUserList();
    }

    private void initUserList() {
        friendsAdapter = new FriendsAdapter(this, userList);
        recycler_ease.setLayoutManager(new LinearLayoutManager(this));
        recycler_ease.setAdapter(friendsAdapter);
        friendsAdapter.notifyDataSetChanged();
        friendsAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int position) {
                String userId = userList.get(position).trim();
                if (TextUtils.isEmpty(userId))
                    return;
                Intent intent = new Intent(FriendsActivity.this, ChatActivity.class);
                intent.putExtra("touserid", userId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().logout(true);
    }
}
