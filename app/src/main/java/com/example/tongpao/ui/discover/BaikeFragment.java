package com.example.tongpao.ui.discover;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.tongpao.IDiscover;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;
import com.example.tongpao.presenter.DiscoverPresenter;
import com.example.tongpao.ui.adapter.discover.BaikeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BaikeFragment extends BaseFragment<DiscoverPresenter> implements IDiscover.View {
    @BindView(R.id.recycler_discover_hotspot)
    RecyclerView recycler_discover_hotspot;
    private List<DiscoverTLBean.DataBean.ListBean> listBeans;
    private BaikeAdapter baikeAdapter;

    @Override
    public int getLayout() {
        return R.layout.layout_discover_hotspot;
    }

    @Override
    public void initView() {
        listBeans = new ArrayList<>();
        baikeAdapter = new BaikeAdapter(getContext(), listBeans);
        recycler_discover_hotspot.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_discover_hotspot.setAdapter(baikeAdapter);
        recycler_discover_hotspot.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public DiscoverPresenter createPresenter() {
        return new DiscoverPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getBaike();
    }

    @Override
    public void getRobe(RobeBean result) {

    }

    @Override
    public void getHot(HotBean result) {

    }

    @Override
    public void getTablayoutz(TablayoutBean result) {

    }

    @Override
    public void getHotspot(DiscoverTLBean result) {

    }

    @Override
    public void getSculpt(DiscoverTLBean result) {
    }

    @Override
    public void getPic(DiscoverTLBean result) {

    }

    @Override
    public void getBaike(DiscoverTLBean result) {
        listBeans.clear();
        listBeans.addAll(result.getData().getList());
        baikeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg, int time) {

    }
}
