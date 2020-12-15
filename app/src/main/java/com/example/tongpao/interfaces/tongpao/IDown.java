package com.example.tongpao.interfaces.tongpao;

import com.example.tongpao.interfaces.Callback;

/**
 * 提供给所有的业务使用的公用接口
 */
public interface IDown {

    interface DownModel{
        void downImage(String url, Callback callback);
    }

}
