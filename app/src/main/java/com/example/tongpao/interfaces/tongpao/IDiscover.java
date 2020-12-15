package com.example.tongpao.interfaces.tongpao;

import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;
import com.example.tongpao.interfaces.IModel;
import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;

public interface IDiscover {
    interface View extends IBaseView{
        void getRobe(RobeBean result);
        void getHot(HotBean result);
        void getTablayoutz(TablayoutBean result);
        void getHotspot(DiscoverTLBean result);
        void getSculpt(DiscoverTLBean result);
        void getPic(DiscoverTLBean result);
        void getBaike(DiscoverTLBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getRobe();
        void getHot();
        void getTablayoutz();
        void getHotspot();
        void getSculpt();
        void getPic();
        void getBaike();
    }

    interface Model extends IModel {
        void loadRobe(Callback callback);
        void loadHot(Callback callback);
        void loadTablayoutz(Callback callback);
        void loadHotspot(Callback callback);
        void loadSculpt(Callback callback);
        void loadPic(Callback callback);
        void loadBaike(Callback callback);
    }
}
