package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;

import java.util.List;

public class TopicAdapter extends DelegateAdapter.Adapter<TopicAdapter.ViewHolder> {
    Context context;
    List<HomeBean.DataBean.TopicListBean> list;


    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> list) {
        this.context = context;
        this.list = list;
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_topic, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        RecyTopicAdapter recyTopicAdapter = new RecyTopicAdapter(context,list);
        holder.recyclerView.setAdapter(recyTopicAdapter);
        recyTopicAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onClick(int id) {
                if (onItemClick!=null){
                    onItemClick.onClick(id);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerview);
        }
    }

    public interface OnItemClick{
        void onClick(int id);
    }
}
