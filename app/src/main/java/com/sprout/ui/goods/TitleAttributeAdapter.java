package com.sprout.ui.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;

/**
 * @创建时间 2021/4/13 20:19
 */
public class TitleAttributeAdapter extends DelegateAdapter.Adapter {
    private  String attribute_title;
    private Context context;
    public TitleAttributeAdapter(String attribute_title, Context context) {
        this.attribute_title = attribute_title;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_titleattribute, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder){
            ((HomeHolder) holder).tvAttributeTitle.setText(attribute_title);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class HomeHolder extends RecyclerView.ViewHolder{
        TextView tvAttributeTitle;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvAttributeTitle = (TextView) itemView.findViewById(R.id.tv_attribute_title);

        }
    }
}
