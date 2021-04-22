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
 * @创建时间 2021/4/13 20:41
 */
public class IssueAdapter extends DelegateAdapter.Adapter {
    private ArrayList<GoodDetailBean.DataBeanX.IssueBean> issueBeans;
    private Context context;

    public IssueAdapter(ArrayList<GoodDetailBean.DataBeanX.IssueBean> issueBeans, Context context) {
        this.issueBeans = issueBeans;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_issue, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder) {
            if (issueBeans != null) {
                ((HomeHolder) holder).tvQuestion.setText(issueBeans.get(position).getQuestion());
                ((HomeHolder) holder).tvAnswer.setText(issueBeans.get(position).getAnswer());
            }
        }
    }

    @Override
    public int getItemCount() {
        return issueBeans.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        TextView tvAnswer;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestion = (TextView) itemView.findViewById(R.id.tv_question);
            tvAnswer = (TextView) itemView.findViewById(R.id.tv_answer);

        }
    }
}
