package com.example.tongpao.presenter;


import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IBigImage;
import com.example.tongpao.model.tongpao.BigImageModel;

public class BigImagePresenter extends BasePresenter<IBigImage.View> implements IBigImage.Persenter {

    IBigImage.View view;
    //接口的规范是DownModel mode
    BigImageModel mode;


    public BigImagePresenter(IBigImage.View view){
        this.view = view;
        mode = new BigImageModel();
    }

    @Override
    public void downImg(String url) {
        mode.downImage(url, new Callback() {
            @Override
            public void onSuccess(Object o) {
                if(view != null){
                    view.downReturn((String) o);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
        if(mode != null){
            mode.clear();
            mode = null;
        }
    }
}
