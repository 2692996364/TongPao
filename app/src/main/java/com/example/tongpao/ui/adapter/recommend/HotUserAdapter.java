package com.example.tongpao.ui.adapter.recommend;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class HotUserAdapter extends BaseAdapter {
    Context contexts;
    private ArrayList<HotUserBean.DataBean.FileBeanListBean> list;
    private HotUserViewAdapter adapter;

    public HotUserAdapter(Context context, List<HotUserBean.DataBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_hotuser;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        HotUserBean.DataBean dataBean = (HotUserBean.DataBean) data;
        ImageView img_hotuser = (ImageView) vh.getViewById(R.id.img_hotuser);
        TextView text_hotuser_name = (TextView) vh.getViewById(R.id.text_hotuser_name);
        TextView text_hotuser_site = (TextView) vh.getViewById(R.id.text_hotuser_site);
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(contexts).load(dataBean.getHeadUrl()).apply(options).into(img_hotuser);
        TxtUtils.setTextView(text_hotuser_name, dataBean.getNickName());
        TxtUtils.setTextView(text_hotuser_site, dataBean.getCity());

        RecyclerView recycler_hotuser_view = (RecyclerView) vh.getViewById(R.id.recycler_hotuser_view);

        list = new ArrayList<>();
        adapter = new HotUserViewAdapter(contexts, list);
        recycler_hotuser_view.setLayoutManager(new LinearLayoutManager(contexts, RecyclerView.HORIZONTAL, false));
        recycler_hotuser_view.setAdapter(adapter);
        for (int i = 0; i < dataBean.getFileBeanList().size(); i++) {
            list.clear();
            list.addAll(dataBean.getFileBeanList());
            adapter.notifyDataSetChanged();
        }

    }
}
