package com.example.tongpao.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.ui.home.NullFragment;
import com.example.tongpao.ui.home.RecommendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    Context context;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    String[] tabs = {"关注", "推荐", "广场", "视频", "摄影", "知识文章"};
    private ArrayList<Fragment> fragments;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        ButterKnife.bind(getActivity());
        fragments = new ArrayList<>();
        fragments.add(new NullFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(1).select();
    }

    @Override
    public void showToast(String msg, int time) {

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
            return tabs[position];
        }
    }
}
