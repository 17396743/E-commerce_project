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
import com.sprout.mode.data.GoodDetailBean;

import java.util.ArrayList;

/**
 * @创建时间 2021/4/13 20:04
 */
public class AttributeAdapter extends DelegateAdapter.Adapter {
    private ArrayList<GoodDetailBean.DataBeanX.AttributeBean> attributeBeans;
    private Context context;

    public AttributeAdapter(ArrayList<GoodDetailBean.DataBeanX.AttributeBean> attributeBeans, Context context) {
        this.attributeBeans = attributeBeans;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_attribute, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  HomeHolder){
            if (attributeBeans!=null){
                ((HomeHolder) holder).tvAttributeName.setText(attributeBeans.get(position).getName());
                ((HomeHolder) holder).tvAttributeValue.setText(attributeBeans.get(position).getValue());
            }
        }
    }

    @Override
    public int getItemCount() {
        return attributeBeans.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvAttributeName;
        TextView tvAttributeValue;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvAttributeName = (TextView) itemView.findViewById(R.id.tv_attribute_name);
            tvAttributeValue = (TextView) itemView.findViewById(R.id.tv_attribute_value);


        }
    }
}
