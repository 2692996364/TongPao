package com.example.tongpao.ui.easemob;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.app.Constants;
import com.example.tongpao.model.data.EMUserInfo;
import com.example.tongpao.utils.GlideEngine;
import com.example.tongpao.utils.MSG;
import com.example.tongpao.utils.SpUtils;
import com.example.tongpao.utils.UserInfoManager;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_chat;
    private RecyclerView recycler_chat;
    private EditText input_chat;
    private Button btn_chat;
    private ImageView img_input;

    String toUserId;
    String selfId;
    List<EMMessage> msgsList;
//    List<MSG> msgsList;
    List<String> sList;
    ChatsAdapter chatsAdapter;
    ChatAdapter chatAdapter;
    private Button btn_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_chat);
        title_chat = (TextView) findViewById(R.id.title_chat);
        recycler_chat = (RecyclerView) findViewById(R.id.recycler_chat);
        input_chat = (EditText) findViewById(R.id.input_chat);
        btn_chat = (Button) findViewById(R.id.btn_chat);
        img_input = (ImageView) findViewById(R.id.img_input);
        btn_record = (Button) findViewById(R.id.btn_record);


        /*
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("touserid")) {
                toUserId = intent.getStringExtra("touserid");
            }
        }
        title_chat.setText(toUserId);
        */

        btn_chat.setOnClickListener(this);
        img_input.setOnClickListener(this);
        btn_record.setOnClickListener(this);
        initData();
        initMsgListner();
    }
/*

    private void initData() {
        */
/*
        msgsList = new ArrayList<>();
        msgsList.add(new MSG("日照香炉生紫烟，你与何人在聊天" , MSG.TYPE_SEND));
        msgsList.add(new MSG("黄河之水天上来，就是普通一女孩" , MSG.TYPE_RECEIVED));
        msgsList.add(new MSG("万水千山只等闲，微信闲扯这么甜" , MSG.TYPE_SEND));
        msgsList.add(new MSG("日出江花红胜火，我俩只是谈工作" , MSG.TYPE_RECEIVED));
        msgsList.add(new MSG("曾经沧海难为水，你俩肯定有一腿" , MSG.TYPE_SEND));
        msgsList.add(new MSG("除却巫山不是云，谁要骗你不是人" , MSG.TYPE_RECEIVED));
        *//*

        msgsList = new ArrayList<>();
        chatsAdapter = new ChatsAdapter(this, msgsList);

        recycler_chat.setLayoutManager(new LinearLayoutManager(this));
        recycler_chat.setAdapter(chatsAdapter);
    }
*/

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("touserid")) {
                toUserId = intent.getStringExtra("touserid");
                title_chat.setText(toUserId);
            }
        }
        selfId = EMClient.getInstance().getCurrentUser();
        msgsList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, msgsList);
        recycler_chat.setLayoutManager(new LinearLayoutManager(this));
        recycler_chat.setAdapter(chatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chat:
                sendMsg();
                break;
            case R.id.img_input:
                openPhoto();
                break;
            case R.id.btn_record:
//                chattingrecords();
                break;
        }
    }

    private void chattingrecords() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_pop, null);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(btn_record, Gravity.CENTER, 0, 0);
/*
        RecyclerView recycler_pop = inflate.findViewById(R.id.recycler_pop);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toUserId);
        //获取此会话的所有消息
        List<EMMessage> messages = conversation.getAllMessages();
        String result = messages.get(0).getBody().toString();
        String msgReceived = result.substring(5, result.length() - 1);

        MSG msg = new MSG(msgReceived, MSG.TYPE_RECEIVED);
        msgsList.add(msg);
        chatsAdapter = new ChatsAdapter(this, msgsList);
        recycler_pop.setLayoutManager(new LinearLayoutManager(this));
        recycler_pop.setAdapter(chatsAdapter);
        chatsAdapter.notifyDataSetChanged();
        inflate.findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        */
    }

    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(9)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for (LocalMedia item : selectList) {
                    sendMsgByImage(item.getPath());
                }
                break;
            default:
                break;
        }
    }

    private void sendMsg() {
        /*
        String content = input_chat.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {

            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
            EMMessage message = EMMessage.createTxtSendMessage(content, toUserId);
            //发送消息
            EMClient.getInstance().chatManager().sendMessage(message);

            MSG msg = new MSG(content, MSG.TYPE_SEND);
            msgsList.add(msg);
            chatsAdapter.notifyItemInserted(msgsList.size() - 1);
            recycler_chat.scrollToPosition(msgsList.size() - 1);
        }
        */

        String content = input_chat.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入消息内容", Toast.LENGTH_SHORT).show();
            return;
        }
        EMMessage message = EMMessage.createTxtSendMessage(content, toUserId);
        message.setChatType(EMMessage.ChatType.Chat);
        EMClient.getInstance().chatManager().sendMessage(message);
        msgsList.add(message);
        chatAdapter.notifyDataSetChanged();
    }

    /**
     * 发图片
     */
    private void sendMsgByImage(String path) {
        Uri uri = Uri.parse(path);
        EMMessage msg = EMMessage.createImageSendMessage(uri, false, toUserId);
        /*EMImageMessageBody body = new EMImageMessageBody(uri);
        msg.addBody(body);*/
        //如果是群聊，设置chattype，默认是单聊
        EMClient.getInstance().chatManager().sendMessage(msg);
        msgsList.add(msg);
        chatAdapter.notifyDataSetChanged();
    }

    private void initMsgListner() {

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
                /*
                //收到消息
                String result = messages.get(0).getBody().toString();
                String msgReceived = result.substring(5, result.length() - 1);

                final MSG msg = new MSG(msgReceived, MSG.TYPE_RECEIVED);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        msgsList.add(msg);
                        chatsAdapter.notifyDataSetChanged();
                        recycler_chat.scrollToPosition(msgsList.size() - 1);
                    }
                });
                */
            //收到消息
            msgsList.addAll(messages);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chatAdapter.notifyDataSetChanged();
                }
            });
                /*
                recycler_chat.post(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.notifyDataSetChanged();
                    }
                });
                */
                /*
                msgsList.addAll(messages);
                if (toUserId.equals(messages.get(0).getFrom())) {
                    //好友
                    messages.get(0).getBody();
                    //EMMessageBody messageBody;
                    if (messages.get(0).getType() == EMMessage.Type.TXT) {
                        EMTextMessageBody textMessageBody = (EMTextMessageBody) messages.get(0).getBody();
                        textMessageBody.getMessage();
                    } else if (messages.get(0).getType() == EMMessage.Type.LOCATION) {
                        //定位销
                    }

                } else if (selfId.equals(messages.get(0).getFrom())) {
                    //自己
                }
                */
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            //收到透传消息
            for (EMMessage item : messages) {
                if (item.getType() == EMMessage.Type.CMD) {
                    EMCmdMessageBody msg = (EMCmdMessageBody) item.getBody();
                    if (Constants.ACTION_UPDATEHEADER.equals(msg.action())) {
                        //刷新界面更新用户头像
                        String action = msg.action();
                        if (!TextUtils.isEmpty(action)) {
                            String uid = item.getFrom();
                            SpUtils.getInstance().setValue(uid, action);
                            EMUserInfo user = UserInfoManager.getInstance().getUserInfoByUid(uid);
                            if (user != null) {
                                user.setHeader(action);
                            }
                        }

                    } else if (Constants.ACTION_UPDATENICKNAME.equals(msg.action())) {
                        //刷新界面更新用户昵称

                    }
                }
            }
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

   /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
    */
}
