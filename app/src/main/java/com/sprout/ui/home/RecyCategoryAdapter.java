package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;

public class RecyCategoryAdapter extends DelegateAdapter.Adapter {
    Context context;
    HomeBean.DataBean.CategoryListBean bean;

    public RecyCategoryAdapter(Context context, HomeBean.DataBean.CategoryListBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recy_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        HomeBean.DataBean.CategoryListBean.GoodsListBean listBean = bean.getGoodsList().get(position);
        Glide.with(context).load(listBean.getList_pic_url()).into(viewHolder.imgRecy);
        if (!listBean.getName().isEmpty()){
            viewHolder.txtTitle.setText(listBean.getName());
        }
        if (!listBean.getRetail_price().isEmpty()){
            viewHolder.txtPrice.setText("￥"+listBean.getRetail_price());
        }
    }

    @Override
    public int getItemCount() {
        return bean.getGoodsList().size();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecy;
        TextView txtTitle;
        TextView txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecy = itemView.findViewById(R.id.img_recy);
            txtTitle = itemView.findViewById(R.id.txt_title_categort_recy);
            txtPrice = itemView.findViewById(R.id.txt_price_categort_recy);
        }
    }
}
