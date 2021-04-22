package com.sprout.ui.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;


/**
 * @创建时间 2021/4/10 15:43
 */
public class ButtonAdapter extends DelegateAdapter.Adapter {
    private Context mContext;

    public ButtonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_item_two, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder) {
            ((HomeHolder) holder).rbItemOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oneClickListener.onClick(position);
                }
            });
            ((HomeHolder) holder).rbItemTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    twoClickListener.onClick(position);
                }
            });
        }
    }

    public interface OnItemOneClickListener {
        void onClick(int position);
    }

    public interface OnItemTwoClickListener {
        void onClick(int position);
    }

    private OnItemOneClickListener oneClickListener;
    private OnItemTwoClickListener twoClickListener;

    public void OnItemOneClickListener(OnItemOneClickListener oneClickListener) {
        this.oneClickListener = oneClickListener;
    }

    public void OnItemTwoClickListener(OnItemTwoClickListener twoClickListener) {
        this.twoClickListener = twoClickListener;
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        RadioButton rbItemOne;
        RadioButton rbItemTwo;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            rbItemOne = (RadioButton) itemView.findViewById(R.id.rb_item_one);
            rbItemTwo = (RadioButton) itemView.findViewById(R.id.rb_item_two);
        }
    }
}
