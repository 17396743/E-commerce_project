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
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;

import java.util.ArrayList;

/**
 * @创建时间 2021/4/8 19:54
 */
public class CategoryAdapter extends DelegateAdapter.Adapter {
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> categoryLists;
    private Context mContext;

    public CategoryAdapter(Context mContext, ArrayList<HomeBean.DataBean.NewGoodsListBean> categoryLists) {
        this.categoryLists = categoryLists;
        this.mContext = mContext;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(2);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_category, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder) {
            Glide.with(mContext).load(categoryLists.get(position).getList_pic_url()).into(((HomeHolder) holder).ivCategory);
            ((HomeHolder) holder).tvCategoryOne.setText(categoryLists.get(position).getName());
            ((HomeHolder) holder).tvCategoryTwo.setText("￥" + categoryLists.get(position).getRetail_price());
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v);
                }
            });
        }
    }



    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(View v);
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        ImageView ivCategory;
        TextView tvCategoryOne;
        TextView tvCategoryTwo;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            ivCategory = (ImageView) itemView.findViewById(R.id.iv_category);
            tvCategoryOne = (TextView) itemView.findViewById(R.id.tv_category_one);
            tvCategoryTwo = (TextView) itemView.findViewById(R.id.tv_category_two);
        }
    }
}
