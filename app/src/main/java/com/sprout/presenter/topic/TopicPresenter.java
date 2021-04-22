package com.sprout.presenter.topic;

import android.widget.Toast;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.topic.ITopic;
import com.sprout.interfaces.topic.TopicModel;
import com.sprout.mode.data.TopicBean;

import java.util.Map;

public class TopicPresenter extends BasePresenter<ITopic.View> implements ITopic.Presenter {
    ITopic.Model model;

    public TopicPresenter() {
        model = new TopicModel();
    }

    @Override
    public void getTopic(Map<String, String> map) {
        model.getTopic(map, new Callback<TopicBean>() {
            @Override
            public void fail(String msg) {
                if (mView != null) {
                    mView.showToast(msg, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void success(TopicBean topicBean) {
                if (mView != null) {
                    mView.getTopicReturn(topicBean);
                }
            }
        });
    }
}
