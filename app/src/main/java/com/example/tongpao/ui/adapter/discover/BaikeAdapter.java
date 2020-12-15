package com.example.tongpao.ui.adapter.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class BaikeAdapter extends BaseAdapter {
    Context contexts;

    public BaikeAdapter(Context context, List<DiscoverTLBean.DataBean.ListBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_baike;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        DiscoverTLBean.DataBean.ListBean listBean = (DiscoverTLBean.DataBean.ListBean) data;
        ImageView img_baike = (ImageView) vh.getViewById(R.id.img_baike);
        TextView title_baike = (TextView) vh.getViewById(R.id.title_baike);
        TextView time_baike = (TextView) vh.getViewById(R.id.time_baike);
        if (listBean.getFilePathList().size() > 0) {
            Glide.with(contexts).load(listBean.getFilePathList().get(0).getFilePath()).into(img_baike);
        }
        TxtUtils.setTextView(title_baike, listBean.getTitle());
        TxtUtils.setTextView(time_baike, listBean.getCreateTime());
    }
}
