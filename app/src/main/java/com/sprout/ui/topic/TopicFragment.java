package com.sprout.ui.topic;

import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.topic.ITopic;
import com.sprout.mode.data.TopicBean;
import com.sprout.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TopicFragment extends BaseFragment<ITopic.Presenter> implements ITopic.View {
    @BindView(R.id.recyclerview_one)
    RecyclerView recyclerview_one;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;
    private ArrayList<TopicBean.Data.Datas> databaseDTOS;
    private ListAdapter listAdapter;
    private ButtonAdapter buttonAdapter;
    private Map<String, String> map;
    private int poston;

    public static TopicFragment getInstance() {
        return new TopicFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initView() {
        virtualLayoutManager = new VirtualLayoutManager(mContext);
        recyclerview_one.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyclerview_one.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerview_one.setAdapter(delegateAdapter);

        databaseDTOS = new ArrayList<>();
        listAdapter = new ListAdapter(mContext, databaseDTOS);
        listAdapter.OnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (databaseDTOS != null) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("getid", databaseDTOS.get(position).getId()+"");
                    startActivity(intent);
                }
            }
        });
        delegateAdapter.addAdapter(listAdapter);

        buttonAdapter = new ButtonAdapter(mContext);
        buttonAdapter.OnItemOneClickListener(new ButtonAdapter.OnItemOneClickListener() {
            @Override
            public void onClick(int position) {
                if (poston > 1) {
                    poston -= 1;
                    map = new HashMap<>();
                    map.put("page", poston + "");
                    map.put("size", "10");
                    presenter.getTopic(map);
                } else {
                    Toast.makeText(mContext, "没有上一页了。", Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttonAdapter.OnItemTwoClickListener(new ButtonAdapter.OnItemTwoClickListener() {
            @Override
            public void onClick(int position) {
                if (poston < databaseDTOS.size()) {
                    poston += 1;
                    map = new HashMap<>();
                    map.put("page", poston + "");
                    map.put("size", "10");
                    presenter.getTopic(map);
                } else {
                    Toast.makeText(mContext, "到头了。", Toast.LENGTH_SHORT).show();
                }

            }
        });
        delegateAdapter.addAdapter(buttonAdapter);
        buttonAdapter.notifyDataSetChanged();


    }


    @Override
    public ITopic.Presenter createPersenter() {
        return new TopicPresenter();
    }

    @Override
    public void initData() {
        map = new HashMap<>();
        poston = 1;
        map.put("page", "1");
        map.put("size", "10");
        presenter.getTopic(map);
    }

    @Override
    public void getTopicReturn(TopicBean result) {
        if (result.getData() != null) {

            databaseDTOS.clear();
            databaseDTOS.addAll(result.getData().getData());
            listAdapter.notifyDataSetChanged();


        }
    }
}
