package com.example.tongpao.model.tongpao;

import com.example.tongpao.api.TongpaoApi;
import com.example.tongpao.base.BaseModel;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IRecommend;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class RecommendModel extends BaseModel implements IRecommend.Model {

    TongpaoApi api;

    public RecommendModel() {
        api = HttpManager.getInstance().getTongpaoApi();
    }


    @Override
    public void loadRecommend(Callback callback) {
        addDisposable(
                api.getRecommend()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendBean>(callback) {
                            @Override
                            public void onNext(RecommendBean recommendBean) {
                                callback.onSuccess(recommendBean);
                            }
                        })
        );
    }

    @Override
    public void loadBanner(Callback callback) {
        addDisposable(
                api.getBanner()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BannerBean>(callback) {
                            @Override
                            public void onNext(BannerBean bannerBean) {
                                callback.onSuccess(bannerBean);
                            }
                        })
        );
    }

    @Override
    public void loadDiscussed(Callback callback) {
        addDisposable(
                api.getDiscussed()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscussedBean>(callback) {
                            @Override
                            public void onNext(DiscussedBean discussedBean) {
                                callback.onSuccess(discussedBean);
                            }
                        })
        );
    }

    @Override
    public void loadHotUser(Callback callback) {
        addDisposable(
                api.getHotUser()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<HotUserBean>(callback) {
                            @Override
                            public void onNext(HotUserBean hotUserBean) {
                                callback.onSuccess(hotUserBean);
                            }
                        })
        );
    }

    @Override
    public void loadPersonal(Callback callback) {
        addDisposable(
                api.getPersonal()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<PersonalBean>(callback) {
                            @Override
                            public void onNext(PersonalBean personalBean) {
                                callback.onSuccess(personalBean);
                            }
                        })
        );
    }
}
