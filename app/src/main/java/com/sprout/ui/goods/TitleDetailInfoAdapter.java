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

import java.util.ArrayList;

/**
 * @创建时间 2021/4/13 20:56
 */
public class TitleDetailInfoAdapter extends DelegateAdapter.Adapter {

    private ArrayList<String> detailInfoTitles;
    private Context context;

    public TitleDetailInfoAdapter(ArrayList<String> detailInfoTitles, Context context) {
        this.detailInfoTitles = detailInfoTitles;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_titledetail, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder) {
            ((HomeHolder) holder).tvTitledetailOne.setText(detailInfoTitles.get(0));
            ((HomeHolder) holder).tvTitledetailTwo.setText(detailInfoTitles.get(1));
            ((HomeHolder) holder).tvTitledetailThree.setText(detailInfoTitles.get(2));
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvTitledetailOne;
        TextView tvTitledetailTwo;
        TextView tvTitledetailThree;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);


            tvTitledetailOne = (TextView) itemView.findViewById(R.id.tv_titledetail_one);
            tvTitledetailTwo = (TextView) itemView.findViewById(R.id.tv_titledetail_two);
            tvTitledetailThree = (TextView) itemView.findViewById(R.id.tv_titledetail_three);

        }
    }
}
