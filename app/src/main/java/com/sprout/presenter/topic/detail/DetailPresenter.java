package com.sprout.presenter.topic.detail;

import android.widget.Toast;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.topic.detail.DetailModel;
import com.sprout.interfaces.topic.detail.IDetail;
import com.sprout.mode.data.DetailBean;

import java.util.Map;

/**
 * @创建时间 2021/4/10 16:47
 */
public class DetailPresenter extends BasePresenter<IDetail.View> implements IDetail.Presenter {

    IDetail.Model model;

    public DetailPresenter() {
        model = new DetailModel();
    }

    @Override
    public void getDetail(Map<String, String> map) {
        model.getDetail(map, new Callback<DetailBean>() {
            @Override
            public void fail(String msg) {
                if (mView != null) {
                    mView.showToast(msg, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void success(DetailBean detailBean) {
                if (mView != null) {
                    mView.getDetailReturn(detailBean);
                }
            }
        });
    }
}
