package com.sprout.ui.goods;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.goods.INewGoods;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.presenter.goods.NewGoodsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class NewGoodsActivity extends BaseActivity<INewGoods.Presenter> implements INewGoods.View, NewGoodsFilterAdapter.GoodFilter  {


    @BindView(R.id.recy_newgoods)
    RecyclerView recyNewGoods;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    NewGoodTopBean newGoodTopBean;
    NewGoodsTopAdapter newGoodsTopAdapter;

    NewGoodsBean newGoodsBean;
    NewGoodsFilterAdapter newGoodsFilterAdapter;

    //列表数据
    List<NewGoodsBean.DataBeanX.GoodsListBean> goodList;
    NewGoodsListAdapter newGoodsListAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_goods;
    }

    @Override
    protected void initView() {
        virtualLayoutManager = new VirtualLayoutManager(this);
        recyNewGoods.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyNewGoods.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyNewGoods.setAdapter(delegateAdapter);


        newGoodsTopAdapter = new NewGoodsTopAdapter(this,null);
        delegateAdapter.addAdapter(newGoodsTopAdapter);


        newGoodsFilterAdapter = new NewGoodsFilterAdapter(this,newGoodsBean, Delegate.OBJECT);
        newGoodsFilterAdapter.addGoodFilter(this);
        delegateAdapter.addAdapter(newGoodsFilterAdapter);

        goodList = new ArrayList<>();
        newGoodsListAdapter =  new NewGoodsListAdapter(this,goodList);
        delegateAdapter.addAdapter(newGoodsListAdapter);





    }

    @Override
    protected INewGoods.Presenter createPersenter() {
        return new NewGoodsPresenter();
    }

    @Override
    protected void initData() {
        presenter.getNewGoodsTop();
        Map<String, String> map = new HashMap<>();
        map.put("isNew", "1");
        map.put("page", "1");
        map.put("size", "1000");
        map.put("order", "asc");
        map.put("sort", "default");
        map.put("categoryId", "0");
        presenter.getNewGoods(map);

    }

    @Override
    public void getNewGoodsTopReturn(NewGoodTopBean result) {
        newGoodTopBean = result;
        if (result.getData() != null){
            newGoodsTopAdapter.setData(newGoodTopBean.getData().getBannerInfo());
        }
    }

    @Override
    public void getNewGoodsReturn(NewGoodsBean result) {
        if (result.getData() != null){
            newGoodsBean = result;
            newGoodsFilterAdapter.setData(result);
            newGoodsFilterAdapter.notifyDataSetChanged();
        }
        goodList.clear();
        goodList.addAll(result.getData().getGoodsList());
        newGoodsListAdapter.notifyDataSetChanged();
    }



    @Override
    public void setFilterParam(Map<String, String> map) {
        presenter.getNewGoods(map);
    }
}