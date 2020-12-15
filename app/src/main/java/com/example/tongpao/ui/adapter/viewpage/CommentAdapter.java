package com.example.tongpao.ui.adapter.viewpage;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    Context contexts;

    public CommentAdapter(Context context, List<RecommendBean.DataBean.PostDetailBean.LikeDetailsBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.comment_recommend;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        RecommendBean.DataBean.PostDetailBean.LikeDetailsBean detailsBeans = (RecommendBean.DataBean.PostDetailBean.LikeDetailsBean) data;
        ImageView img_comment = (ImageView) vh.getViewById(R.id.img_comment);
        TextView name_comment = (TextView) vh.getViewById(R.id.name_comment);
        TextView date_comment = (TextView) vh.getViewById(R.id.date_comment);
        TextView level_comment = (TextView) vh.getViewById(R.id.level_comment);
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(contexts).load(detailsBeans.getHeadUrl()).apply(options).into(img_comment);
        TxtUtils.setTextView(name_comment, detailsBeans.getNickName());
        TxtUtils.setTextView(date_comment, detailsBeans.getLikeTime());
        TxtUtils.setTextView(level_comment, detailsBeans.getLevel() + "");
    }
}
