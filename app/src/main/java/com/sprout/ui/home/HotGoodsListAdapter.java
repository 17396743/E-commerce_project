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

import java.util.ArrayList;

/**
 * @创建时间 2021/4/10 18:16
 */
public class HotGoodsListAdapter extends DelegateAdapter.Adapter {
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private Context mContext;
    public HotGoodsListAdapter(Context mContext,ArrayList<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans) {

        this.hotGoodsListBeans = hotGoodsListBeans;
        this.mContext = mContext;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_hot_goods, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  HomeHolder){
            Glide.with(mContext).load(hotGoodsListBeans.get(position).getList_pic_url()).into(((HomeHolder) holder).imgHot);
            ((HomeHolder) holder).txtNameHot.setText(hotGoodsListBeans.get(position).getName());
            ((HomeHolder) holder).txtBriefHot.setText(hotGoodsListBeans.get(position).getGoods_brief());
            ((HomeHolder) holder).txtPriceHot.setText("￥"+hotGoodsListBeans.get(position).getRetail_price());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v);
                }
            });
        }
    }
    private CategoryAdapter.OnItemClickListener listener;

    public void setListener(CategoryAdapter.OnItemClickListener goodid) {
        this.listener = goodid;
    }


    public interface OnItemClickListener{
        void onClick(View v);
    }

    @Override
    public int getItemCount() {
        return hotGoodsListBeans.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder{
         ImageView imgHot;
         TextView txtNameHot;
         TextView txtBriefHot;
         TextView txtPriceHot;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            imgHot = (ImageView) itemView.findViewById(R.id.img_hot);
            txtNameHot = (TextView) itemView.findViewById(R.id.txt_name_hot);
            txtBriefHot = (TextView) itemView.findViewById(R.id.txt_brief_hot);
            txtPriceHot = (TextView) itemView.findViewById(R.id.txt_price_hot);

        }
    }


}
