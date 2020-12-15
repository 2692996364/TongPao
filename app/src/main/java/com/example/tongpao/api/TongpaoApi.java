package com.example.tongpao.api;


import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TongpaoApi {
    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("home/recommend.json")
    Flowable<RecommendBean> getRecommend();

    @GET("home/banner.json")
    Flowable<BannerBean> getBanner();

    @GET("home/topic_discussed.json")
    Flowable<DiscussedBean> getDiscussed();

    @GET("home/hot_user.json")
    Flowable<HotUserBean> getHotUser();

    @GET("home/personal.json")
    Flowable<PersonalBean> getPersonal();
}
