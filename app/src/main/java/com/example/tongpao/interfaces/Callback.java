package com.example.tongpao.interfaces;

public interface Callback<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
