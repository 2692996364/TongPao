package com.example.tongpao.ui.adapter.recommend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.ui.PersonalActivity;
import com.example.tongpao.ui.adapter.viewpage.CommentAdapter;
import com.example.tongpao.utils.DateUtils;
import com.example.tongpao.utils.RoundTransform;
import com.example.tongpao.utils.TxtUtils;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecommendsAdapter extends BaseAdapter {
    Context contexts;

    public RecommendsAdapter(Context context, List<RecommendBean.DataBean.PostDetailBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_recommend_dicussed;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        RequestOptions option = new RequestOptions().centerCrop().transform(new RoundTransform(contexts, 15));
        RecommendBean.DataBean.PostDetailBean bean = (RecommendBean.DataBean.PostDetailBean) data;
        ImageView img_head = (ImageView) vh.getViewById(R.id.img_head);
        TextView text_name = (TextView) vh.getViewById(R.id.text_name);
        TextView text_time = (TextView) vh.getViewById(R.id.text_time);
        TextView text_word = (TextView) vh.getViewById(R.id.text_word);
        NineGridImageView ninegrid = (NineGridImageView) vh.getViewById(R.id.nineGrid);

        List<RecommendBean.DataBean.PostDetailBean.LikeDetailsBean> likeDetails = bean.getLikeDetails();
        RecyclerView recycler_recommend_dicussed = (RecyclerView) vh.getViewById(R.id.recycler_recommend_dicussed);
        recycler_recommend_dicussed.setLayoutManager(new LinearLayoutManager(contexts));
        CommentAdapter adapter = new CommentAdapter(contexts, likeDetails);
        recycler_recommend_dicussed.setAdapter(adapter);

        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(contexts).load(bean.getHeadUrl()).apply(options).into(img_head);
        TxtUtils.setTextView(text_name, bean.getNickName());
        TxtUtils.setTextView(text_word, bean.getContent());
        Long time = DateUtils.getDateToTime(bean.getCreateTime(), null);
        String date = DateUtils.getStandardDate(time);
        TxtUtils.setTextView(text_time, date);

        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexts, PersonalActivity.class);
                contexts.startActivity(intent);
            }
        });

        String content = bean.getContent();
        int indexOf = content.indexOf("#");
        int lastIndexOf = content.lastIndexOf("#") + 1;
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        /*
        String str = "#你最喜欢的汉服#";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(builder);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            builder.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        */
        builder.setSpan(new ForegroundColorSpan(Color.BLUE), indexOf, lastIndexOf, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        text_word.setMovementMethod(LinkMovementMethod.getInstance());
        text_word.setText(builder);

        List<RecommendBean.DataBean.PostDetailBean.ImagesBean> images = bean.getImages();
        ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            imgs.add(images.get(i).getFilePath());
        }
        ninegrid.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                Glide.with(context).load(o).apply(option).into(imageView);
            }
        });
        ninegrid.setImagesData(imgs);
    }
}
