package com.example.tongpao.interfaces.tongpao;


import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;
import com.example.tongpao.interfaces.IModel;

public interface IBigImage {

    interface View extends IBaseView {

        void downReturn(String path);

    }

    interface Persenter extends IBasePresenter<View> {
        void downImg(String url);
    }


    interface Model extends IModel {
        void getBigImage();
    }
}
