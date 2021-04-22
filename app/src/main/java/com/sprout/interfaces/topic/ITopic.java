package com.sprout.interfaces.topic;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.TopicBean;

import java.util.Map;

public interface ITopic {
    interface View extends IBaseView {
        //定义一个接口给Presenter调用
        void getTopicReturn(TopicBean result);

    }


    interface Presenter extends IBasePresenter<View> {
        //定义一个给View层调用的接口
        void getTopic(Map<String,String> map);
    }


    interface Model extends IModel {
        //定义一个给Presenter调用的接口，用来加载数据
        void getTopic(Map<String,String> map , Callback<TopicBean> callback);
    }
}
