package com.example.tongpao.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {
    protected Context context;
    List<D> mData;
    protected IListClick listClick;

    public BaseAdapter(Context context, List<D> data) {
        this.context = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listClick != null) {
                    listClick.itemClick(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mData.get(position), (VH) holder, position);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected  List<D> getData(){
        return mData;
    }

    protected abstract int getLayout();

    protected abstract void bindData(D data, VH vh, int position);

    public void addListClick(IListClick listClick){
        this.listClick = listClick;
    }

    public interface IListClick {
        void itemClick(int position);
    }

    public class VH extends RecyclerView.ViewHolder {
        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }
}
