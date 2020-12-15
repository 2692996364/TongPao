package com.example.tongpao.api;


import com.example.tongpao.model.discoverdata.HotBean;
import com.example.tongpao.model.discoverdata.DiscoverTLBean;
import com.example.tongpao.model.discoverdata.RobeBean;
import com.example.tongpao.model.discoverdata.TablayoutBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface DiscoverApi {
    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("discover/robe.json")//发现袍子链接
    Flowable<RobeBean> getRobe();

    @GET("discover/hot_activity.json")//发现热门活动链接
    Flowable<HotBean> getHot();

    @GET("discover/navigation.json")//发现tablayout链接
    Flowable<TablayoutBean> getTablayoutz();

    @GET("discover/news_1.json")
    Flowable<DiscoverTLBean> getHotspot();//发现热点链接

    @GET("discover/news_2.json")//发现妆造链接
    Flowable<DiscoverTLBean> getSculpt();

    @GET("discover/news_3.json")//发现图赏链接
    Flowable<DiscoverTLBean> getPic();

    @GET("discover/news_4.json")//发现百科链接
    Flowable<DiscoverTLBean> getBaike();

}
