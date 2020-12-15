package com.example.tongpao.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.presenter.RecommendPresenter;
import com.example.tongpao.ui.adapter.recommend.PersonalViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonalFragment extends BaseFragment<RecommendPresenter> implements IRecommend.View {

    @BindView(R.id.recycler_fragment_personal)
    RecyclerView recycler_fragment_personal;
    private List<PersonalBean.DataBean> list;
    private PersonalViewAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        adapter = new PersonalViewAdapter(getContext(), list);
        recycler_fragment_personal.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_fragment_personal.setAdapter(adapter);
    }

    @Override
    public RecommendPresenter createPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getPersonal();
    }

    @Override
    public void showToast(String msg, int time) {

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
}
