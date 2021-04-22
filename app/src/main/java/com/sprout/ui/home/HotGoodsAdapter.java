package com.sprout.ui.home;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.HomeBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public abstract class HotGoodsAdapter extends BaseDelegateAdapter<HomeBean.DataBean.HotGoodsListBean> {


    public HotGoodsAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> list) {
        super(context, list, Delegate.LIST);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hot_goods;
    }

//    @Override
    protected void bindData(BaseViewHolder holder, HomeBean.DataBean.HotGoodsListBean data) {
        ImageView imgHot = (ImageView) holder.getViewById(R.id.img_hot);
        TextView txtName = (TextView) holder.getViewById(R.id.txt_name_hot);
        TextView txtBrief = (TextView) holder.getViewById(R.id.txt_brief_hot);
        TextView txtPrice = (TextView) holder.getViewById(R.id.txt_price_hot);

        ImageLoader.imageLoad(data.getList_pic_url(),imgHot);
        TextViewUtils.setTextView(data.getName(),txtName);
        TextViewUtils.setTextView(data.getGoods_brief(),txtBrief);
        TextViewUtils.setTextView(data.getRetail_price(),txtPrice);
    }
}
