package com.example.tongpao.ui.adapter.discover;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.utils.RoundTransform;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class HotAdapter extends BaseAdapter {
    Context contexts;

    public HotAdapter(Context context, List<HotBean.DataBean> data) {
        super(context, data);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_hot;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        HotBean.DataBean dataBean = (HotBean.DataBean) data;
        ImageView img_hot = (ImageView) vh.getViewById(R.id.img_hot);
        TextView title_hot = (TextView) vh.getViewById(R.id.title_hot);
        TextView location_hot = (TextView) vh.getViewById(R.id.location_hot);
        TextView time_hot = (TextView) vh.getViewById(R.id.time_hot);
        RequestOptions options = new RequestOptions().centerCrop().transform(new RoundTransform(contexts, 20));
        Glide.with(contexts).load(dataBean.getCover()).apply(options).into(img_hot);
        TxtUtils.setTextView(title_hot, dataBean.getTitle());
        TxtUtils.setTextView(location_hot, dataBean.getLocation());
        TxtUtils.setTextView(time_hot, dataBean.getStartTime());
    }
}
