package com.sprout.ui.topic;

import android.util.Log;
import android.widget.Toast;

import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.topic.detail.IDetail;
import com.sprout.mode.data.DetailBean;
import com.sprout.presenter.topic.detail.DetailPresenter;

import java.util.HashMap;

public class DetailActivity extends BaseActivity<IDetail.Presenter> implements IDetail.View {


    private HashMap<String, String> map;

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IDetail.Presenter createPersenter() {
        return new DetailPresenter();
    }

    @Override
    protected void initData() {
        String getid = getIntent().getStringExtra("getid");
        map = new HashMap<>();
        map.put("id", getid);
        presenter.getDetail(map);
    }

    @Override
    public void getDetailReturn(DetailBean result) {
        if (result.getData() != null) {
            Log.d("TAG2", "getDetailReturn: " + result.getData().getTitle());
            Toast.makeText(this, "专栏详情页：" + result.getData().getTitle() + "", Toast.LENGTH_SHORT).show();
        }
    }
}