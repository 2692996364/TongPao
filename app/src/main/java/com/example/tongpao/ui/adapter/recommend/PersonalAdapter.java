package com.example.tongpao.ui.adapter.recommend;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class PersonalAdapter extends BaseAdapter {
    Context contexts;
    Activity activity;

    public PersonalAdapter(Activity activity, Context context, List<PersonalBean.DataBean> datas) {
        super(context, datas);
        this.contexts = context;
        this.activity = activity;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_personal_centre;
    }

    @Override
    protected void bindData(Object data, BaseAdapter.VH vh, int position) {
        PersonalBean.DataBean dataBean = (PersonalBean.DataBean) data;
        RequestOptions options = new RequestOptions().circleCrop();
        ImageView img_personal = (ImageView) vh.getViewById(R.id.img_personal);
        ImageView img_title_personal = (ImageView) vh.getViewById(R.id.img_title_personal);
        TextView level = (TextView) vh.getViewById(R.id.level);
        TextView tongQian = (TextView) vh.getViewById(R.id.tongQian);
        TextView expScore = (TextView) vh.getViewById(R.id.expScore);
        img_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
//                Intent intent = new Intent(PersonalActivity.this, TongpaoActivity.class);
//                contexts.startActivity(intent);
            }
        });
        Glide.with(contexts).load(dataBean.getHeadUrl()).apply(options).into(img_title_personal);
        TxtUtils.setTextView(level, dataBean.getLevel() + "");
        TxtUtils.setTextView(tongQian, dataBean.getTongQian() + "");
        TxtUtils.setTextView(expScore, dataBean.getExpScore() + "");
    }

}
