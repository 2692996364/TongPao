package com.example.tongpao.ui.map;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;

import java.util.List;

public class SuggestAdapter extends BaseAdapter<SuggestionResult.SuggestionInfo> {

    public SuggestAdapter(Context context, List<SuggestionResult.SuggestionInfo> list){
        super(context,list);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_map_suggest_item;
    }

    @Override
    protected void bindData(SuggestionResult.SuggestionInfo data, VH vh, int position) {
        ImageView imgType = (ImageView) vh.getViewById(R.id.img_type);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        txtName.setText(data.getKey());

    }
}
