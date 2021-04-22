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

import java.util.List;

public class RecyTopicAdapter extends DelegateAdapter.Adapter {
    Context context;
    List<HomeBean.DataBean.TopicListBean> list;

    public RecyTopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    public interface OnItemClick {
        void onClick(int id);
    }

    public TopicAdapter.OnItemClick onItemClick;

    public void setOnItemClick(TopicAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_topic_recy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        HomeBean.DataBean.TopicListBean bean = list.get(position);
        Glide.with(context).load(bean.getItem_pic_url()).into(viewHolder.imgTopic);
        if (!bean.getTitle().isEmpty()) {
            viewHolder.txtTitle.setText(bean.getTitle());
        }
        if (!bean.getPrice_info().isEmpty()) {
            viewHolder.txtPrice.setText("￥" + bean.getPrice_info() + "元起");
        }
        if (!bean.getSubtitle().isEmpty()) {
            viewHolder.txtSubTitle.setText(bean.getSubtitle());
        }
        viewHolder.imgTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(bean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopic;
        TextView txtTitle;
        TextView txtPrice;
        TextView txtSubTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopic = itemView.findViewById(R.id.img_topic);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtSubTitle = itemView.findViewById(R.id.txt_subtitle);
        }
    }

}
