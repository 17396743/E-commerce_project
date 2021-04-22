package com.sprout.interfaces.topic;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.mode.data.TopicBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

/**
 * @创建时间 2021/4/9 21:40
 */
public class TopicModel extends BaseModel implements ITopic.Model {

    @Override
    public void getTopic(Map<String, String> map, Callback<TopicBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getTopic(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callback.success(topicBean);
                    }
                }));
    }
}
