package com.sprout.ui.mine;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.sprout.R;
import com.sprout.app.GlideEngine;
import com.sprout.app.MyApp;
import com.sprout.app.MyAppGlideModule;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.presenter.mine.UserInfoPresenter;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.SpUtils;
import com.sprout.utils.TextViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class UserInfoActivity extends BaseActivity<IMine.UserPresenter> implements IMine.UserView, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;

    private final int width = 400;
    private final int height = 400;

    /*********oss ????????????*****************/
    String bucketName = "sprout-app";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";
    String key = "LTAI5t7afAVVFZq84X6puaqa"; //appkey
    String secret = "K4t5BRAiSttIk9HNR8D1z1itxb1oiR"; //??????
    OSSClient ossClient; //ossclient??????

    @Override
    protected int getLayout() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initView() {
        String head = SpUtils.getInstance().getString("avatar");
        String nickname = SpUtils.getInstance().getString("nickname");
        String birthday = SpUtils.getInstance().getString("birthday");
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(head,imgHead,options);
        TextViewUtils.setTextView(nickname,txtNickname);
        TextViewUtils.setTextView(birthday,txtBirthday);

        imgHead.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        txtNickname.setOnClickListener(this);
        txtBirthday.setOnClickListener(this);

        initOssClient();

    }

    private void initOssClient(){
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key,secret,"");
        // ????????????????????????????????????????????????
        ClientConfiguration conf = new  ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // ?????????????????????15??????
        conf.setSocketTimeout(15 * 1000);  // socket???????????????15??????*
        conf.setMaxConcurrentRequest(5); // ??????????????????????????????5??????
        conf.setMaxErrorRetry(2);  // ????????????????????????????????????2??????
        ossClient = new OSSClient(MyApp.app, ossPoint, credentialProvider);
    }

    @Override
    protected IMine.UserPresenter createPersenter() {
        return new UserInfoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateUserInfoReturn(UpdateUserInfoBean result) {
        if(result.getData() != null){
            SpUtils.getInstance().setValue("avatar",result.getData().getAvatar());
            SpUtils.getInstance().setValue("nickname",result.getData().getNickname());
            SpUtils.getInstance().setValue("birthday",String.valueOf(result.getData().getBirthday()));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_head:
                openPhotos();
                break;
            case R.id.txt_nickname:
                break;
            case R.id.txt_birthday:
                break;
        }
    }

    /**
     * ????????????
     */
    private void openPhotos(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageSpanCount(4)
                .loadImageEngine(GlideEngine.createGlideEngine()) // ?????????Demo GlideEngine.java
                .forResult(new OnResultCallbackListener() {
                    @Override
                    public void onResult(List result) {
                        if(result.size() > 0){
                            LocalMedia media = (LocalMedia) result.get(0);
                            String path = media.getPath();
                            parseSmallImg(path);
                        }
                    }

                    @Override
                    public void onCancel() {
                        Log.i("TAG","oncancel");
                    }
                });

    }

    private void parseSmallImg(String path){
        //????????????
        Bitmap bitmap = ImageLoader.scaleBitmap(path,width,height);
        uploadBitmap(bitmap);
    }

    /**
     * ????????????
     * @param bmp
     */
    private void uploadBitmap(Bitmap bmp){
        byte[] bts = ImageLoader.getBytesByBitmap(bmp);
        String uid = SpUtils.getInstance().getString("uid");
        uid = "admin";
        String filename = uid+"/"+System.currentTimeMillis()/1000+Math.random()*10000+".png";

        PutObjectRequest put = new PutObjectRequest(bucketName, filename, bts);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.i("TAG",currentSize+"/"+totalSize);
            }
        });
        /**
         * ???????????????????????????
         */
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.i("TAG",result.toString());
                //?????????????????????????????????
                String url  = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                updateUserHead(url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                Log.i("TAG",serviceException.toString());
            }
        });
    }

    /**
     * ??????????????????????????????
     * @param avatar
     */
    private void updateUserHead(String avatar){
        //??????????????????????????????
        Map<String,String> map = new HashMap<>();
        map.put("avatar",avatar);
        presenter.updateUserInfo(map);
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(avatar,imgHead);
        Log.i("TAG","????????????");
    }
}
