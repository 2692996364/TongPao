package com.example.tongpao.ui.discover;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.tongpao.IDiscover;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;
import com.example.tongpao.presenter.DiscoverPresenter;
import com.example.tongpao.ui.adapter.discover.HotAdapter;
import com.example.tongpao.ui.home.NullFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends BaseFragment<DiscoverPresenter> implements IDiscover.View {
    @BindView(R.id.robe_discover)
    TextView robe_discover;
    @BindView(R.id.mass_discover)
    TextView mass_discover;
    @BindView(R.id.ranking_discover)
    TextView ranking_discover;
    @BindView(R.id.recycler_discover)
    RecyclerView recycler_discover;
    @BindView(R.id.tablayout_discover)
    TabLayout tablayout_discover;
    @BindView(R.id.viewpager_discover)
    ViewPager viewpager_discover;
    String[] str = {"热点", "妆造", "图赏", "百科"};
    private List<Fragment> fragments;
    private List<HotBean.DataBean> hotList;
    private HotAdapter hotAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        setupCollapsingToolbar();
        ButterKnife.bind(getActivity());
        hotList = new ArrayList<>();

        fragments = new ArrayList<>();
        fragments.add(new HotspotFragment());
        fragments.add(new SculptFragment());
        fragments.add(new SculptFragment());
        fragments.add(new BaikeFragment());
//        tablayout_discover.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager_discover.setAdapter(new MyAdapter(getChildFragmentManager()));
        tablayout_discover.setupWithViewPager(viewpager_discover);

        hotAdapter = new HotAdapter(getContext(), hotList);
        recycler_discover.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recycler_discover.setAdapter(hotAdapter);
    }

    @Override
    public DiscoverPresenter createPresenter() {
        return new DiscoverPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getHot();
    }

    @Override
    public void getRobe(RobeBean result) {

    }

    @Override
    public void getHot(HotBean result) {
        hotList.clear();
        hotList.addAll(result.getData());
        hotAdapter.notifyDataSetChanged();
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

    }

    @Override
    public void showToast(String msg, int time) {

    }

    private void setupCollapsingToolbar() {
        AppBarLayout appbars_layout = (AppBarLayout) getActivity().findViewById(
                R.id.appbars_layout);
        appbars_layout.setExpanded(false);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }
}
