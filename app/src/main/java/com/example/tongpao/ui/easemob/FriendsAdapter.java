package com.example.tongpao.ui.easemob;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class FriendsAdapter extends BaseAdapter {
    public FriendsAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_friend_item;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        TextView text_username = (TextView) vh.getViewById(R.id.text_username);
        text_username.setText(data.toString());
    }
}
