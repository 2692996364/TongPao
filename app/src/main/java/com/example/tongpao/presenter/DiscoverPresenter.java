package com.example.tongpao.presenter;

import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IDiscover;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;
import com.example.tongpao.model.tongpao.DiscoverModel;

public class DiscoverPresenter extends BasePresenter<IDiscover.View> implements IDiscover.Presenter {
    private IDiscover.View view;
    IDiscover.Model model;

    public DiscoverPresenter(IDiscover.View view) {
        this.view = view;
        this.model = new DiscoverModel();
    }

    @Override
    public void getRobe() {
        this.model.loadRobe(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getRobe((RobeBean) o);
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
    public void getHot() {
        this.model.loadHot(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getHot((HotBean) o);
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
    public void getTablayoutz() {
        this.model.loadTablayoutz(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getTablayoutz((TablayoutBean) o);
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
    public void getHotspot() {
        this.model.loadHotspot(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getHotspot((DiscoverTLBean) o);
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
    public void getSculpt() {
        this.model.loadSculpt(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getSculpt((DiscoverTLBean) o);
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
    public void getPic() {
        this.model.loadPic(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getPic((DiscoverTLBean) o);
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
    public void getBaike() {
        this.model.loadBaike(new Callback() {
            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getBaike((DiscoverTLBean) o);
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
