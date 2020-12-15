package com.example.tongpao.ui.bigimage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;


import com.example.tongpao.R;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.interfaces.tongpao.IBigImage;
import com.example.tongpao.presenter.BigImagePresenter;
import com.example.tongpao.ui.TongpaoActivity;
import com.example.tongpao.ui.adapter.viewpage.ViewpageAdapter;
import com.example.tongpao.utils.ImageLoader;
import com.example.tongpao.utils.TxtUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BigImageActivity extends BaseActivity<BigImagePresenter> implements IBigImage.View {

    List<String> urls; //当前需要查看的所有图片的路径
    int currentPos; //当前操作的图片的位置
    @BindView(R.id.txt_return)
    TextView txtReturn;
    @BindView(R.id.txt_page)
    TextView txtPage;
    @BindView(R.id.txt_down)
    TextView txtDown;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_bigimage;
    }

    @Override
    protected void initView() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position + 1;
                updatePage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected BigImagePresenter createPresenter() {
        return new BigImagePresenter(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        //data存放图片数据和当前操作下标
        if (intent != null && intent.hasExtra("data")) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                urls = bundle.getStringArrayList("urls");
                currentPos = bundle.getInt("postion");
                updatePage();
            }
        }
        ViewpageAdapter viewpageAdapter = new ViewpageAdapter(urls, this);
        viewPager.setAdapter(viewpageAdapter);
        viewPager.setCurrentItem(currentPos);
    }

    @OnClick({R.id.txt_return, R.id.txt_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_return:
                finish();
//                Intent intent = new Intent(this, TongpaoActivity.class);
//                startActivity(intent);
                break;
            case R.id.txt_down:
                downImg();
                break;
        }
    }

    //更新当前选中的图片位置
    private void updatePage() {
        if (currentPos <= urls.size()) {
            String page = String.valueOf(currentPos + "/" + urls.size());
            TxtUtils.setTextView(txtPage, page);
            /*
            //判断是否有下载过
            String imgUrl = urls.get(currentPos);
            String[] arr = ImageLoader.splitUrl(imgUrl);
            String imgName = arr[1];
            String path = arr[2];
            File file = new File(path);
            if (file.exists()) {
                txtDown.setVisibility(View.GONE);
            } else {
                txtDown.setVisibility(View.VISIBLE);
            }
            */
        } else {
            showToast("当前的图片位置越界", Toast.LENGTH_SHORT);
        }
        if (currentPos < urls.size()) {
            //判断是否有下载过
            String imgUrl = urls.get(currentPos);
            String[] arr = ImageLoader.splitUrl(imgUrl);
            String imgName = arr[1];
            String path = arr[2];
            File file = new File(path);
            if (file.exists()) {
                txtDown.setVisibility(View.GONE);
            } else {
                txtDown.setVisibility(View.VISIBLE);
            }

        }
    }

    private void downImg() {
        String imgUrl = urls.get(currentPos);
        presenter.downImg(imgUrl);
    }

    /**
     * 下载成功回到
     *
     * @param path
     */
    @Override
    public void downReturn(String path) {
        Log.i("TAG", path);
    }
}
