package com.example.tongpao.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.presenter.RecommendPresenter;
import com.example.tongpao.ui.adapter.recommend.PersonalAdapter;
import com.example.tongpao.ui.home.NullFragment;
import com.example.tongpao.ui.home.PersonalFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends BaseActivity<RecommendPresenter> implements IRecommend.View {

    private RecyclerView recycler_personal;
    private TabLayout tablayout_personal;

    String[] str = {"资料", "动态", "活动", "社团", "文章"};
    private List<Fragment> fragments;
    private ViewPager viewpager_personal;
    private List<PersonalBean.DataBean> list;
    private PersonalAdapter adapter;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
    }

    private void initView() {
        img_personal = (ImageView) findViewById(R.id.img_personal);
        img_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, TongpaoActivity.class);
                startActivity(intent);
            }
        });
        recycler_personal = (RecyclerView) findViewById(R.id.recycler_personal);
        tablayout_personal = (TabLayout) findViewById(R.id.tablayout_personal);

        list = new ArrayList<>();
        PersonalAdapter adapter = new PersonalAdapter(this, list);
        recycler_personal.setLayoutManager(new LinearLayoutManager(this));
        recycler_personal.setAdapter(adapter);

        fragments = new ArrayList<>();
        fragments.add(new PersonalFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        viewpager_personal = (ViewPager) findViewById(R.id.viewpager_personal);

        tablayout_personal.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager_personal.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout_personal.setupWithViewPager(viewpager_personal);
        tablayout_personal.getTabAt(1).select();
    }
*/

    @Override
    protected void initView() {
        setupCollapsingToolbar();
        recycler_personal = (RecyclerView) findViewById(R.id.recycler_personal);
        tablayout_personal = (TabLayout) findViewById(R.id.tablayout_personal);

        list = new ArrayList<>();
        adapter = new PersonalAdapter(this, this, list);
        recycler_personal.setLayoutManager(new LinearLayoutManager(this));
        recycler_personal.setAdapter(adapter);

        fragments = new ArrayList<>();
        fragments.add(new PersonalFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        viewpager_personal = (ViewPager) findViewById(R.id.viewpager_personal);

//        tablayout_personal.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager_personal.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout_personal.setupWithViewPager(viewpager_personal);
    }


    private void setupCollapsingToolbar() {
        AppBarLayout appbar_layout = (AppBarLayout) findViewById(
                R.id.appbar_layout);

        appbar_layout.setExpanded(false);
    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.getPersonal();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal;
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {

    }

    @Override
    public void getBannerReturn(BannerBean result) {

    }

    @Override
    public void getDiscussedReturn(DiscussedBean result) {

    }

    @Override
    public void getHotUserReturn(HotUserBean result) {

    }

    @Override
    public void getPersonalReturn(PersonalBean result) {
        list.clear();
        list.add(result.getData());
        adapter.notifyDataSetChanged();
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
