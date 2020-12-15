package com.example.tongpao.ui.easemob;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.data.EMUserInfo;
import com.example.tongpao.utils.GlideEngine;
import com.example.tongpao.utils.SpUtils;
import com.example.tongpao.utils.UserInfoManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private List<EMMessage> list;
    private String selfUid;

    public ChatAdapter(Context context, List<EMMessage> list) {
        super(context, list);
        this.list = list;
        selfUid = EMClient.getInstance().getCurrentUser();
    }

    @Override
    protected int getLayout() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 1){
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_item_rt,parent,false);
        }else if(viewType == 2){
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_item_lt,parent,false);
        }
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //接口回调的调用
                if(listClick != null){
                    listClick.itemClick(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        EMMessage msg = (EMMessage) data;
        TextView txtWord = (TextView) vh.getViewById(R.id.txt_word);
        ImageView imgIcon = (ImageView) vh.getViewById(R.id.img_icon);
        ImageView imgHeader = (ImageView) vh.getViewById(R.id.img_header);
        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if(selfUid.equals(msg.getFrom())){
            String header = SpUtils.getInstance().getString(selfUid);
            if(!TextUtils.isEmpty(header)){
                Glide.with(imgHeader).load(header).into(imgHeader);
            }
        }else{
            EMUserInfo user = UserInfoManager.getInstance().getUserInfoByUid(msg.getFrom());
            if(user != null){
                if(!TextUtils.isEmpty(user.getHeader())){
                    Glide.with(imgHeader).load(user.getHeader()).into(imgHeader);
                }
            }
        }
        if(msg.getType() == EMMessage.Type.TXT){
            txtWord.setVisibility(View.VISIBLE);
            imgIcon.setVisibility(View.GONE);
            EMTextMessageBody textMessageBody = (EMTextMessageBody) msg.getBody();
            txtWord.setText(textMessageBody.getMessage());
        }else if(msg.getType() == EMMessage.Type.IMAGE){
            txtWord.setVisibility(View.GONE);
            imgIcon.setVisibility(View.VISIBLE);
            EMImageMessageBody imageMessageBody = (EMImageMessageBody) msg.getBody();
            if (selfUid.equals(msg.getFrom())){
                Uri localUri = imageMessageBody.getLocalUri();
                Glide.with(imgIcon).load(localUri).into(imgIcon);
            } else {
                String path = imageMessageBody.getThumbnailUrl();
                Glide.with(imgIcon).load(path).into(imgIcon);
            }
        }
    }


    private void openPhoto() {
        PictureSelector.create((Activity) context)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(9)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 如果消息是自己发送的 1   消息是其他人的 2
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        EMMessage msg = (EMMessage) getData().get(position);
        if(selfUid.equals(msg.getFrom())){
            return 1;
        }else{
            return 2;
        }
    }
}
