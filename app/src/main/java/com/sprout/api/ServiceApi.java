package com.sprout.api;


import com.sprout.mode.data.AddCarBean;
import com.sprout.mode.data.AlipayBean;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
import com.sprout.mode.data.DeleteCarBean;
import com.sprout.mode.data.DetailBean;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.mode.data.TopicBean;
import com.sprout.mode.data.UpdateCarBean;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.mode.data.VersionBean;
import com.sprout.mode.data.WXOrderBean;
import com.sprout.mode.data.WxTokenBean;
import com.sprout.mode.data.WxTokenRefreshBean;
import com.sprout.mode.data.WxUserInfoBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BASE_URL = "https://cdplay.cn/";

    String BASE_PAY_URL = "http://cdwan.cn:9000/";

    String BASE_WX_URL = "https://api.weixin.qq.com/";

    String BASE_VERSION = "http://cdwan.cn:7000/";


    @GET("api/index")
    Flowable<HomeBean> getHome();

    @GET("api/goods/list")
    Flowable<NewGoodsBean> getNewGoods(@QueryMap Map<String,String> map);

    //专题专栏
    @GET("api/topic/list")
    Flowable<TopicBean> getTopic(@QueryMap Map<String, String> map);

    //专题专栏详情
    @GET("api/topic/detail")
    Flowable<DetailBean> getDetail(@QueryMap Map<String, String> map);


    //热门商品顶部的数据
    @GET("api/goods/hot")
    Flowable<NewGoodTopBean> getNewGoodsTop();

    /**
     * 分类竖导航的数据接口
     * @return
     */
    @GET("api/catalog/index")
    Flowable<CatalogTabBean> getCatalogTab(@Query("id") int id);

    /**
     * 竖导航所对应的分类数据
     * @param id
     * @return
     */
    @GET("api/catalog/current")
    Flowable<CatalogBean> getCatalog(@Query("id") int id);

    /**
     * 分类商品详情也的顶部数据接口  主页频道入口的商品数据
     * @param id
     * @return
     */
    @GET("api/goods/category")
    Flowable<CategoryTopBean> getCategoryTop(@Query("id") int id);

    /**
     * 分类详情页列表数据
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GET("api/goods/list")
    Flowable<CategoryListBean> getCategoryList(@Query("categoryId") int id,@Query("page") int page,@Query("size") int size);

    /**
     * 商品详情页
     * @param id
     * @return
     */
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);

    /**
     * 购物车数据
     * @return
     */
    @GET("api/cart/index")
    Flowable<CarBean> getCarData();


    /**
     * 登录
     */
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username,@Field("password") String pw);

    /**
     * 添加到购物车
     * @param goodid
     * @param number
     * @param pid
     * @return
     */
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@Field("goodsId") int goodid,@Field("number") int number,@Field("productId") int pid);


    /**
     * 获取购物车
     * @return
     */
    @GET("/api/cart/index")
    Flowable<CarBean> getCarList();


    /**
     * 更新购物车数据
     * @param map  goodsId  productId number id(购物车的ID)
     * @return
     */
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    /**
     * 删除购物车数据
     * @param ids
     * @return
     */
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String ids);


    /**
     * 微信订单
     * @param type
     * @return
     */
    @POST("wxorder")
    @FormUrlEncoded
    Flowable<WXOrderBean> wxOrder(@Field("paytype") int type);

    /**
     * 获取alipay的订单
     * @param type
     * @return
     */
    @POST("alipayorder")
    @FormUrlEncoded
    Flowable<AlipayBean> alipayOrder(@Field("paytype") int type);

    /**
     * 更新用户信息
     */
    @POST("api/user/updateUserInfo")
    @FormUrlEncoded
    Flowable<UpdateUserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);

    /**
     * 获取微信的access_token
     */
    @GET("sns/oauth2/access_token")
    Flowable<WxTokenBean> getWxAccessToken(@QueryMap Map<String,String> map);

    /**
     * 微信token刷新
     * @param map
     * @return
     */
    @GET("sns/oauth2/refresh_token")
    Flowable<WxTokenRefreshBean> getWxRefreshToken(@QueryMap Map<String,String> map);

    /**
     * 获取微信的用户数据
     * @param map
     * @return
     */
    @GET("sns/userinfo")
    Flowable<WxUserInfoBean> getWxUserInfo(@QueryMap Map<String,String> map);


    /**
     * 获取最新的版本信息
     * @return
     */
    @GET("tongpao/apk/version.json")
    Flowable<VersionBean> getVersion();

}
