package com.example.tongpao.ui.home;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.presenter.RecommendPresenter;
import com.example.tongpao.ui.adapter.recommend.DiscussedAdapter;
import com.example.tongpao.ui.adapter.recommend.HotUserAdapter;
import com.example.tongpao.ui.adapter.recommend.HotUserViewAdapter;
import com.example.tongpao.ui.adapter.recommend.RecommendAdapter;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements IRecommend.View {


    @BindView(R.id.banner_recommend)
    Banner banner_recommend;
    @BindView(R.id.recycler_talk)
    RecyclerView recycler_talk;
    @BindView(R.id.recycler_recommend)
    RecyclerView recycler_recommend;
    @BindView(R.id.recycler_hotuser)
    RecyclerView recycler_hotuser;

    List<DiscussedBean.DataBean> dataBeans;
    DiscussedAdapter adapter;
    private ArrayList<BannerBean.DataBean.ListBean> ban;
    RecommendAdapter recommendAdapter;
    List<RecommendBean.DataBean.PostDetailBean> recommendList;
    private ArrayList<HotUserBean.DataBean> hotuserBeans;
    private HotUserAdapter hotUserAdapter;

    private ArrayList<HotUserBean.DataBean.FileBeanListBean> list;
    private HotUserViewAdapter hotUserViewAdapter;
    @Override
    public int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {

        ban = new ArrayList<>();
        dataBeans = new ArrayList<>();
        recommendList = new ArrayList<>();
        hotuserBeans = new ArrayList<>();


        hotUserAdapter = new HotUserAdapter(getContext(), hotuserBeans);
        recycler_hotuser.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recycler_hotuser.setAdapter(hotUserAdapter);

        adapter = new DiscussedAdapter(getContext(), this.dataBeans);
        recycler_talk.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recycler_talk.setAdapter(adapter);

        recommendAdapter = new RecommendAdapter(getContext(), recommendList);
        recycler_recommend.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_recommend.setAdapter(recommendAdapter);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.mipmap.fje);
        integers.add(R.mipmap.fjf);
        integers.add(R.mipmap.fjl);
        BannerImageAdapter bannerAdapter = new BannerImageAdapter<Integer>(integers) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                Glide.with(getContext()).load(data).into(holder.imageView);
            }
        };
        /*
        //活数据只有一条
        BannerImageAdapter adapter = new BannerImageAdapter<BannerBean.DataBean.ListBean>(ban) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean.DataBean.ListBean data, int position, int size) {
                //                String bannerUrl = data.getBanner();
//                RequestOptions options = new RequestOptions()
//                        .transform(new RoundedCorners(20));
                Glide.with(getContext()).load(data.getBanner()).into(holder.imageView);
            }
        };
        */
//        banner.setIndicatorRadius(20);
        banner_recommend.setBannerGalleryEffect(15, 15);
        banner_recommend.setAdapter(bannerAdapter);

    }

    @Override
    public RecommendPresenter createPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getRecommend();
        presenter.getBanner();
        presenter.getDiscussed();
        presenter.getHotUser();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {
        recommendList.clear();
        recommendList.add(result.getData().getPostDetail());
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBannerReturn(BannerBean result) {
        Log.i("111", result.toString());
        List<BannerBean.DataBean.ListBean> list = result.getData().getList();
        ban.addAll(list);

    }

    @Override
    public void getDiscussedReturn(DiscussedBean result) {
        dataBeans.clear();
        dataBeans.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getHotUserReturn(HotUserBean result) {
        hotuserBeans.clear();
        hotuserBeans.addAll(result.getData());
        hotUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void getPersonalReturn(PersonalBean result) {

    }

    @Override
    public void showToast(String msg, int time) {

    }
}
