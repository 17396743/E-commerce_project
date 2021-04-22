package com.sprout.interfaces.topic.detail;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.mode.data.DetailBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

/**
 * @创建时间 2021/4/10 16:40
 */
public class DetailModel extends BaseModel implements IDetail.Model {

    @Override
    public void getDetail(Map<String, String> map, Callback<DetailBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getDetail(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DetailBean>(callback) {
                    @Override
                    public void onNext(DetailBean detailBean) {
                        callback.success(detailBean);
                    }
                }));
    }
}
