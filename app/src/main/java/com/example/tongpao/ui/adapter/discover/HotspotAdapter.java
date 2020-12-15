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
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.utils.RoundTransform;

import java.util.List;

public class HotspotAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<DiscoverTLBean.DataBean.ListBean> listBeans;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;

    public HotspotAdapter(Context context, List<DiscoverTLBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_hotspot_one, parent, false);
            return new VH_ONE(view);
        } else if (viewType == TWO) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_hotspot_two, parent, false);
            return new VH_TWO(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_hotspot_three, parent, false);
            return new VH_THREE(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return ONE;
        } else if (position % 3 == 1) {
            return TWO;
        } else {
            return THREE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        DiscoverTLBean.DataBean.ListBean listBean = listBeans.get(position);
        RequestOptions options = new RequestOptions().centerCrop().transform(new RoundTransform(context, 20));
        if (viewType == ONE) {
            VH_ONE vh_one = (VH_ONE) holder;
            Glide.with(context).load(listBean.getFilePathList().get(0).getFilePath()).apply(options).into(vh_one.img_hotspot_onea);
            Glide.with(context).load(listBean.getFilePathList().get(1).getFilePath()).apply(options).into(vh_one.img_hotspot_oneb);
            Glide.with(context).load(listBean.getFilePathList().get(2).getFilePath()).apply(options).into(vh_one.img_hotspot_onec);
            vh_one.text_hotspot_one.setText(listBean.getTitle());
            vh_one.time_hotspot_one.setText(listBean.getCreateTime());
        } else if (viewType == TWO) {
            VH_TWO vh_two = (VH_TWO) holder;
            Glide.with(context).load(listBean.getFilePathList().get(0).getFilePath()).apply(options).into(vh_two.img_hotspot_two);
            vh_two.title_hotspot_two.setText(listBean.getTitle());
            vh_two.time_hotspot_two.setText(listBean.getCreateTime());
        } else {

            VH_THREE vh_three = (VH_THREE) holder;
            vh_three.text_hotspot_three.setText(listBean.getTitle());
            vh_three.time_hotspot_three.setText(listBean.getCreateTime());
        }

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class VH_ONE extends RecyclerView.ViewHolder {

        private final ImageView img_hotspot_onea;
        private final ImageView img_hotspot_oneb;
        private final ImageView img_hotspot_onec;
        private final TextView text_hotspot_one;
        private final TextView time_hotspot_one;

        public VH_ONE(@NonNull View itemView) {
            super(itemView);
            img_hotspot_onea = itemView.findViewById(R.id.img_hotspot_onea);
            img_hotspot_oneb = itemView.findViewById(R.id.img_hotspot_oneb);
            img_hotspot_onec = itemView.findViewById(R.id.img_hotspot_onec);
            text_hotspot_one = itemView.findViewById(R.id.text_hotspot_one);
            time_hotspot_one = itemView.findViewById(R.id.time_hotspot_one);
        }
    }

    class VH_TWO extends RecyclerView.ViewHolder {
        private final ImageView img_hotspot_two;
        private final TextView title_hotspot_two;
        private final TextView time_hotspot_two;

        public VH_TWO(@NonNull View itemView) {
            super(itemView);
            img_hotspot_two = itemView.findViewById(R.id.img_hotspot_two);
            title_hotspot_two = itemView.findViewById(R.id.title_hotspot_two);
            time_hotspot_two = itemView.findViewById(R.id.time_hotspot_two);
        }
    }

    class VH_THREE extends RecyclerView.ViewHolder {
        private final TextView text_hotspot_three;
        private final TextView time_hotspot_three;

        public VH_THREE(@NonNull View itemView) {
            super(itemView);
            text_hotspot_three = itemView.findViewById(R.id.text_hotspot_three);
            time_hotspot_three = itemView.findViewById(R.id.time_hotspot_three);
        }
    }
}
