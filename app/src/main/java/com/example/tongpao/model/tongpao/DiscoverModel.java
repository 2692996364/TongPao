package com.example.tongpao.model.tongpao;

import com.example.tongpao.api.DiscoverApi;
import com.example.tongpao.base.BaseModel;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IDiscover;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class DiscoverModel extends BaseModel implements IDiscover.Model {

    DiscoverApi api;

    public DiscoverModel() {
        api = HttpManager.getInstance().getDiscoverApi();
    }


    @Override
    public void loadRobe(Callback callback) {
        addDisposable(
                api.getRobe()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RobeBean>(callback) {
                    @Override
                    public void onNext(RobeBean robeBean) {
                        callback.onSuccess(robeBean);
                    }
                })
        );
    }

    @Override
    public void loadHot(Callback callback) {
        addDisposable(
                api.getHot()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotBean>(callback) {
                    @Override
                    public void onNext(HotBean hotBean) {
                        callback.onSuccess(hotBean);
                    }
                })
        );
    }

    @Override
    public void loadTablayoutz(Callback callback) {
        addDisposable(
                api.getTablayoutz()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TablayoutBean>(callback) {
                            @Override
                            public void onNext(TablayoutBean tablayoutBean) {
                                callback.onSuccess(tablayoutBean);
                            }
                        })
        );
    }

    @Override
    public void loadHotspot(Callback callback) {
        addDisposable(
                api.getHotspot()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTLBean>(callback) {
                            @Override
                            public void onNext(DiscoverTLBean discoverTLBean) {
                                callback.onSuccess(discoverTLBean);
                            }
                        })
        );
    }

    @Override
    public void loadSculpt(Callback callback) {
        addDisposable(
                api.getSculpt()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTLBean>(callback) {
                            @Override
                            public void onNext(DiscoverTLBean discoverTLBean) {
                                callback.onSuccess(discoverTLBean);
                            }
                        })
        );
    }

    @Override
    public void loadPic(Callback callback) {
        addDisposable(
                api.getPic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTLBean>(callback) {
                            @Override
                            public void onNext(DiscoverTLBean discoverTLBean) {
                                callback.onSuccess(discoverTLBean);
                            }
                        })
        );
    }

    @Override
    public void loadBaike(Callback callback) {
        addDisposable(
                api.getBaike()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTLBean>(callback) {
                            @Override
                            public void onNext(DiscoverTLBean discoverTLBean) {
                                callback.onSuccess(discoverTLBean);
                            }
                        })
        );
    }
}
