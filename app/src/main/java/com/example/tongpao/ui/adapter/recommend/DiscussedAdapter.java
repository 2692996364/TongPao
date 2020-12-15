package com.example.tongpao.ui.adapter.recommend;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.utils.RoundTransform;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class DiscussedAdapter extends BaseAdapter {
    Context contexts;

    public DiscussedAdapter(Context context, List<DiscussedBean.DataBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_dicussed;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        DiscussedBean.DataBean dataBean = (DiscussedBean.DataBean) data;
        ImageView img_icon_one = (ImageView) vh.getViewById(R.id.img_icon_one);
        TextView name_dicussed = (TextView) vh.getViewById(R.id.name_dicussed);
        TextView text_dicussed = (TextView) vh.getViewById(R.id.text_dicussed);
        RequestOptions options = new RequestOptions().centerCrop().transform(new RoundTransform(contexts,30));
        Glide.with(contexts).load(dataBean.getImageUrl()).apply(options).into(img_icon_one);
        TxtUtils.setTextView(name_dicussed, "#" + dataBean.getName() + "#");
        TxtUtils.setTextView(text_dicussed, dataBean.getUseTime() + "人参与");

        if (position % 2 == 0) {
            vh.itemView.setBackgroundResource(R.drawable.shape_dicussed_bg);
        }else {
            vh.itemView.setBackgroundResource(R.drawable.shape_dicussed_two);
        }
    }

}
