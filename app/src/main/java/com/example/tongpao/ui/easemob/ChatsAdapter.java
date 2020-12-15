package com.example.tongpao.ui.easemob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.utils.MSG;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.MyViewHolder> {

    private Context context;
    private List<MSG> mMsgList;

    public ChatsAdapter(Context context, List<MSG> mMsgList) {
        this.context = context;
        this.mMsgList = mMsgList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_msg, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MSG msg = mMsgList.get(position);
        if (msg.getType() == MSG.TYPE_RECEIVED){
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.llRight.setVisibility(View.GONE);
            holder.tv_Left.setText(msg.getContent());
        } else if (msg.getType() == MSG.TYPE_SEND){
            holder.llRight.setVisibility(View.VISIBLE);
            holder.llLeft.setVisibility(View.GONE);
            holder.tv_Right.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llLeft;
        LinearLayout llRight;

        TextView tv_Left;
        TextView tv_Right;

        ImageView img_msg_left;
        ImageView img_msg_right;


        public MyViewHolder(View itemView) {
            super(itemView);

            llLeft = (LinearLayout) itemView.findViewById(R.id.ll_msg_left);
            llRight = (LinearLayout) itemView.findViewById(R.id.ll_msg_right);

            tv_Left = (TextView) itemView.findViewById(R.id.tv_msg_left);
            tv_Right = (TextView) itemView.findViewById(R.id.tv_msg_right);

            img_msg_left = (ImageView) itemView.findViewById(R.id.img_msg_left);
            img_msg_right = (ImageView) itemView.findViewById(R.id.img_msg_right);

        }
    }
}
