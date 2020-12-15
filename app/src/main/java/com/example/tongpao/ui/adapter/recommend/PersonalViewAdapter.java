package com.example.tongpao.ui.adapter.recommend;

import android.content.Context;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.utils.TxtUtils;

import java.util.List;

public class PersonalViewAdapter extends BaseAdapter {
    Context contexts;

    public PersonalViewAdapter(Context context, List<PersonalBean.DataBean> datas) {
        super(context, datas);
        this.contexts = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_item_personal;
    }

    @Override
    protected void bindData(Object data, VH vh, int position) {
        PersonalBean.DataBean dataBean = (PersonalBean.DataBean) data;
        TextView birthday = (TextView) vh.getViewById(R.id.birthday);
        TxtUtils.setTextView(birthday, "生日: " + dataBean.getBirthday());
    }
}
