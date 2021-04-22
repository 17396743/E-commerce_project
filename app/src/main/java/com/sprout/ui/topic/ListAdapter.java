package com.sprout.ui.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.TopicBean;

import java.util.ArrayList;

/**
 * @创建时间 2021/4/10 10:28
 */
public class ListAdapter extends DelegateAdapter.Adapter {
    private ArrayList<TopicBean.Data.Datas> databaseDTOS;
    private Context mContext;

    public ListAdapter(Context mContext, ArrayList<TopicBean.Data.Datas> databaseDTOS) {
        this.databaseDTOS = databaseDTOS;
        this.mContext = mContext;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_item_one, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (databaseDTOS != null) {
            if (holder instanceof HomeHolder) {
                Glide.with(mContext).load(databaseDTOS.get(position).getScene_pic_url()).into(((HomeHolder) holder).itemIvOne);
                ((HomeHolder) holder).itemTvOne.setText(databaseDTOS.get(position).getTitle());
                ((HomeHolder) holder).itemTvTwo.setText(databaseDTOS.get(position).getSubtitle());
                ((HomeHolder) holder).itemTvThree.setText(databaseDTOS.get(position).getPrice_info() + "元起");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(position);
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void OnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return databaseDTOS.size();
    }


    class HomeHolder extends RecyclerView.ViewHolder {
        CardView cvItemOne;
        ImageView itemIvOne;
        TextView itemTvOne;
        TextView itemTvTwo;
        TextView itemTvThree;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            cvItemOne = (CardView) itemView.findViewById(R.id.cv_item_one);
            itemIvOne = (ImageView) itemView.findViewById(R.id.item_iv_one);
            itemTvOne = (TextView) itemView.findViewById(R.id.item_tv_one);
            itemTvTwo = (TextView) itemView.findViewById(R.id.item_tv_two);
            itemTvThree = (TextView) itemView.findViewById(R.id.item_tv_three);

        }
    }
}
