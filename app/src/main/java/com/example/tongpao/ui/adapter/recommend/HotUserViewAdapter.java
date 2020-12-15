package com.example.tongpao.ui.adapter.recommend;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.HotUserBean;

import java.util.List;

public class HotUserViewAdapter extends BaseAdapter {
    Context contexts;

    public HotUserViewAdapter(Context context, List<HotUserBean.DataBean.FileBeanListBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.hotuser_recycler;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        HotUserBean.DataBean.FileBeanListBean bean = (HotUserBean.DataBean.FileBeanListBean) data;
        ImageView img_hotuser_recycler = (ImageView) vh.getViewById(R.id.img_hotuser_recycler);
        Glide.with(contexts).load(bean.getFilePath()).into(img_hotuser_recycler);
    }
}
