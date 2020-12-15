package com.example.tongpao.interfaces.tongpao;

import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;
import com.example.tongpao.interfaces.IModel;
import com.example.tongpao.model.recommenddata.BannerBean;
import com.example.tongpao.model.recommenddata.DiscussedBean;
import com.example.tongpao.model.recommenddata.HotUserBean;
import com.example.tongpao.model.recommenddata.PersonalBean;
import com.example.tongpao.model.recommenddata.RecommendBean;


public interface IRecommend {
    interface View extends IBaseView {
        void getRecommendReturn(RecommendBean result);

        void getBannerReturn(BannerBean result);

        void getDiscussedReturn(DiscussedBean result);

        void getHotUserReturn(HotUserBean result);

        void getPersonalReturn(PersonalBean result);

    }

    interface Presenter extends IBasePresenter<View> {
        void getRecommend();

        void getBanner();

        void getDiscussed();

        void getHotUser();

        void getPersonal();
    }

    interface Model extends IModel {
        void loadRecommend(Callback callback);

        void loadBanner(Callback callback);

        void loadDiscussed(Callback callback);

        void loadHotUser(Callback callback);

        void loadPersonal(Callback callback);
    }
}
