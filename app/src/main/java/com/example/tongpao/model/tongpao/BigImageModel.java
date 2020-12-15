package com.example.tongpao.model.tongpao;

import com.example.tongpao.app.Constants;
import com.example.tongpao.base.BaseModel;
import com.example.tongpao.interfaces.Callback;
import com.example.tongpao.interfaces.tongpao.IBigImage;
import com.example.tongpao.interfaces.tongpao.IDown;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.ImageLoader;
import com.example.tongpao.utils.RxUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class BigImageModel extends BaseModel implements IBigImage.Model, IDown.DownModel {


    /**
     * 下载图片
     * @param url
     * @param callback
     */
    @Override
    public void downImage(String url, Callback callback) {
        String[] arr = ImageLoader.splitUrl(url);
        String baseUrl = arr[0];
        String imgName = arr[1];
        String path = arr[2];
        Disposable disposable = HttpManager.getInstance().getImageApi(baseUrl)
                .downImage(url)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ResponseBody>(callback) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        FileOutputStream stream = null;
                        //拿到流写入本地
                        try {
                            //判断当前的流是否有数据
                            if(inputStream.available() > 0){
                                //判断当前本地的路径是否存在
                                File file = new File(Constants.PATH_IMGS);
                                if(file.isDirectory() && !file.exists()){
                                    boolean bool = file.createNewFile();
                                    if(bool){
                                        stream = new FileOutputStream(path);
                                        int n = 0;
                                        byte[] bytes = new byte[4096];
                                        while((n=inputStream.read(bytes)) != -1){
                                            stream.write(bytes);
                                        }
                                        stream.flush(); //刷新到sd卡
                                    }else{
                                        callback.onFail("创建本地目录失败");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                inputStream.close();
                                stream.close();
                                callback.onSuccess(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void getBigImage() {

    }
}
