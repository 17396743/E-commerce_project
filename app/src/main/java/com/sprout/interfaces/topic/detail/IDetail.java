package com.sprout.interfaces.topic.detail;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.DetailBean;

import java.util.Map;

/**
 * @创建时间 2021/4/10 16:41
 */
public interface IDetail {
    interface View extends IBaseView {
        //定义一个接口给Presenter调用
        void getDetailReturn(DetailBean result);

    }


    interface Presenter extends IBasePresenter<View> {
        //定义一个给View层调用的接口
        void getDetail(Map<String,String> map);
    }


    interface Model extends IModel {
        //定义一个给Presenter调用的接口，用来加载数据
        void getDetail(Map<String,String> map , Callback<DetailBean> callback);
    }
}
