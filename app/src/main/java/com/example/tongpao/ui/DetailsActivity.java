package com.example.tongpao.ui;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.presenter.RecommendPresenter;
import com.example.tongpao.ui.adapter.recommend.RecommendsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<RecommendPresenter> implements IRecommend.View {

    @BindView(R.id.recycler_details)
    RecyclerView recycler_details;
    @BindView(R.id.fanhui_details)
    ImageView fanhui_details;
    List<RecommendBean.DataBean.PostDetailBean> recommendList;
    private RecommendsAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        recommendList = new ArrayList<>();

        fanhui_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(DetailsActivity.this, TongpaoActivity.class);
//                startActivity(intent);
            }
        });

        adapter = new RecommendsAdapter(this, recommendList);
        recycler_details.setLayoutManager(new LinearLayoutManager(this));
        recycler_details.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getRecommend();
    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {
        recommendList.clear();
        recommendList.add(result.getData().getPostDetail());
        adapter.notifyDataSetChanged();
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

    }

}
