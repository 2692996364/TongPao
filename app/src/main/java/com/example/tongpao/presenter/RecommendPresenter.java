package com.example.tongpao.presenter;

import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.model.tongpao.RecommendModel;

public class RecommendPresenter extends BasePresenter<IRecommend.View> implements IRecommend.Presenter {
    private IRecommend.View view;
    IRecommend.Model model;

    public RecommendPresenter(IRecommend.View view) {
        this.view = view;
        this.model = new RecommendModel();
    }

    @Override
    public void getRecommend() {
        this.model.loadRecommend(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getRecommendReturn((RecommendBean) o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getBanner() {
        this.model.loadBanner(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getBannerReturn((BannerBean) o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getDiscussed() {
        this.model.loadDiscussed(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getDiscussedReturn((DiscussedBean) o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getHotUser() {
        this.model.loadHotUser(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getHotUserReturn((HotUserBean) o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getPersonal() {

        this.model.loadPersonal(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getPersonalReturn((PersonalBean) o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }
}
