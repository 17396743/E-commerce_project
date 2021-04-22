package com.sprout.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.google.zxing.activity.CaptureActivity;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.home.HomePresenter;
import com.sprout.ui.goods.GoodDetailActivity;
import com.sprout.ui.goods.NewGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View, View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.img_scan)
    ImageView imgScan;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    List<HomeBean.DataBean.BannerBean> banners;
    BannerAdapter bannerAdapter;

    List<HomeBean.DataBean.ChannelBean> channels;
    ChannelAdapter channelAdapter;

    //品牌标题
    String brandTitle = "";
    String brandTitle_one = "";
    String brandTitle_two = "";
    String brandTitle_three = "";
    String brandTitle_four = "";
    String brandTitle_five = "";
    String brandTitle_six = "";
    String brandTitle_seven = "";
    String brandTitle_eight = "";
    String brandTitle_nine = "";
    String brandTitle_ten = "";
    String brandTitle_eleven = "";
    String brandTitle_twelve = "";

    //上下轮番广告ViewFlipper
    List<String> flipperList;
    FlipperAdapter flipperAdapter;

    List<HomeBean.DataBean.BrandListBean> brands;
    BrandAdapter brandAdapter;
    ArrayList<HomeBean.DataBean.NewGoodsListBean> categoryLists;
    CategoryAdapter categoryAdapter;
    Button btnSetting;
    ArrayList<HomeBean.DataBean.CategoryListBean> categoryListBeans;
    ArrayList<HomeBean.DataBean.TopicListBean> topicListBeans;
    ArrayList<HomeBean.DataBean.TopicListBean> topicListBeans1;
    RecyCategoryAdapter recyCategoryAdapter;
    RecyTopicAdapter recyTopicAdapter;
    ArrayList<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    HotGoodsListAdapter hotGoodsListAdapter;


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        imgScan.setOnClickListener(this);

        virtualLayoutManager = new VirtualLayoutManager(mContext);
        recyclerView.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        //banner
        banners = new ArrayList<>();
        bannerAdapter = new BannerAdapter(banners);
        delegateAdapter.addAdapter(bannerAdapter);

        channels = new ArrayList<>();
        channelAdapter = new ChannelAdapter(mContext, channels);
        delegateAdapter.addAdapter(channelAdapter);

        flipperList = new ArrayList<>();
        flipperList.add("这是头条新闻");
        flipperList.add("这是普通新闻");
        flipperList.add("这是腾讯新闻");
        flipperAdapter = new FlipperAdapter(mContext,flipperList);
        delegateAdapter.addAdapter(flipperAdapter);

        brandTitle = "品牌制造商直供";
        TitleAdapter titleAdapter_one = new TitleAdapter(mContext, brandTitle);
        delegateAdapter.addAdapter(titleAdapter_one);
        titleAdapter_one.notifyDataSetChanged();

        brands = new ArrayList<>();
        brandAdapter = new BrandAdapter(mContext, brands);
        delegateAdapter.addAdapter(brandAdapter);


        brandTitle_one = "周一周四·新品首发";
        TitleAdapter titleAdapter_two = new TitleAdapter(mContext, brandTitle_one);
        titleAdapter_two.setListener(new TitleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(getContext(), NewGoodsActivity.class));
            }
        });
        delegateAdapter.addAdapter(titleAdapter_two);
        titleAdapter_two.notifyDataSetChanged();

        categoryLists = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(mContext, categoryLists);
        delegateAdapter.addAdapter(categoryAdapter);
        categoryAdapter.setListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                HomeBean.DataBean.NewGoodsListBean bean = categoryLists.get(pos);
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("goodid", bean.getId());
                startActivityForResult(intent, Constants.PAGE_REQEST_CODE_GOODDETAIL);
            }
        });


        brandTitle_two = "人气推荐";
        TitleAdapter titleAdapter_three = new TitleAdapter(mContext, brandTitle_two);
        delegateAdapter.addAdapter(titleAdapter_three);
        titleAdapter_three.notifyDataSetChanged();

        hotGoodsListBeans = new ArrayList<>();
        hotGoodsListAdapter = new HotGoodsListAdapter(mContext, hotGoodsListBeans);
        delegateAdapter.addAdapter(hotGoodsListAdapter);
        hotGoodsListAdapter.setListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                HomeBean.DataBean.HotGoodsListBean bean = hotGoodsListBeans.get(pos);
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("goodid", bean.getId());
                startActivityForResult(intent, Constants.PAGE_REQEST_CODE_GOODDETAIL);
            }
        });


        brandTitle_three = "专题精选";
        TitleAdapter titleAdapter_four = new TitleAdapter(mContext, brandTitle_three);
        delegateAdapter.addAdapter(titleAdapter_four);
        titleAdapter_four.notifyDataSetChanged();

        topicListBeans = new ArrayList<>();
        recyTopicAdapter = new RecyTopicAdapter(mContext, topicListBeans);
        delegateAdapter.addAdapter(recyTopicAdapter);

        brandTitle_four = "居家";
        TitleAdapter titleAdapter_five = new TitleAdapter(mContext, brandTitle_four);
        delegateAdapter.addAdapter(titleAdapter_five);
        titleAdapter_five.notifyDataSetChanged();


        brandTitle_five = "餐厨";
        TitleAdapter titleAdapter_6 = new TitleAdapter(mContext, brandTitle_five);
        delegateAdapter.addAdapter(titleAdapter_6);
        titleAdapter_6.notifyDataSetChanged();


        brandTitle_six = "饮食";
        TitleAdapter titleAdapter_7 = new TitleAdapter(mContext, brandTitle_six);
        delegateAdapter.addAdapter(titleAdapter_7);
        titleAdapter_7.notifyDataSetChanged();


        brandTitle_seven = "配件";
        TitleAdapter titleAdapter_8 = new TitleAdapter(mContext, brandTitle_seven);
        delegateAdapter.addAdapter(titleAdapter_8);
        titleAdapter_8.notifyDataSetChanged();


        brandTitle_eight = "服装";
        TitleAdapter titleAdapter_9 = new TitleAdapter(mContext, brandTitle_eight);
        delegateAdapter.addAdapter(titleAdapter_9);
        titleAdapter_9.notifyDataSetChanged();


        brandTitle_nine = "婴童";
        TitleAdapter titleAdapter_10 = new TitleAdapter(mContext, brandTitle_nine);
        delegateAdapter.addAdapter(titleAdapter_10);
        titleAdapter_10.notifyDataSetChanged();

        brandTitle_ten = "杂货";
        TitleAdapter titleAdapter_11 = new TitleAdapter(mContext, brandTitle_ten);
        delegateAdapter.addAdapter(titleAdapter_11);
        titleAdapter_11.notifyDataSetChanged();

        brandTitle_eleven = "洗护";
        TitleAdapter titleAdapter_12 = new TitleAdapter(mContext, brandTitle_eleven);
        delegateAdapter.addAdapter(titleAdapter_12);
        titleAdapter_12.notifyDataSetChanged();

        brandTitle_twelve = "志趣";
        TitleAdapter titleAdapter_13 = new TitleAdapter(mContext, brandTitle_twelve);
        delegateAdapter.addAdapter(titleAdapter_13);
        titleAdapter_13.notifyDataSetChanged();


    }

    @Override
    public IHome.Presenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    public void initData() {
        presenter.getHome();
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if (result.getData() != null) {
            banners.clear();
            banners.addAll(result.getData().getBanner());
            bannerAdapter.notifyDataSetChanged();

            channels.clear();
            channels.addAll(result.getData().getChannel());
            channelAdapter.notifyDataSetChanged();



            brands.clear();
            brands.addAll(result.getData().getBrandList());
            brandAdapter.notifyDataSetChanged();

            categoryLists.clear();
            categoryLists.addAll(result.getData().getNewGoodsList());
            categoryAdapter.notifyDataSetChanged();


            topicListBeans.clear();
            topicListBeans.addAll(result.getData().getTopicList());
            recyTopicAdapter.notifyDataSetChanged();

            hotGoodsListBeans.clear();
            hotGoodsListBeans.addAll(result.getData().getHotGoodsList());
            hotGoodsListAdapter.notifyDataSetChanged();


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_scan:
                openScan();
                break;
        }
    }

    /**
     * 打开扫描页面
     */
    private void openScan(){
        Intent intent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(intent,Constants.PAGE_SCAN_CODE);
    }
}
