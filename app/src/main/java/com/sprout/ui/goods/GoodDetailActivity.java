package com.sprout.ui.goods;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.goods.IGood;
import com.sprout.mode.data.AddCarBean;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.presenter.goods.GoodDetailPresenter;
import com.sprout.ui.goods.adapters.DetailBuyBarAdapter;
import com.sprout.ui.goods.adapters.DetailInfoAdapter;
import com.sprout.ui.goods.adapters.DetailWebAdapter;
import com.sprout.ui.login.LoginActivity;
import com.sprout.utils.ActivityTask;
import com.sprout.utils.DisplayUtils;
import com.sprout.utils.SpUtils;
import com.sprout.widget.BuySelectorWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodDetailActivity extends BaseActivity<IGood.Presenter> implements IGood.View,View.OnClickListener {

    @BindView(R.id.recy_detail)
    RecyclerView recyDetail;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_buy)
    ConstraintLayout layoutBuy;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.txt_car)
    TextView txtCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;

    GalleryAdapter galleryAdapter;
    ArrayList<GoodDetailBean.DataBeanX.GalleryBean> galleryBeans;
    AttributeAdapter attributeAdapter;
    ArrayList<GoodDetailBean.DataBeanX.AttributeBean> attributeBeans;
    ArrayList<GoodDetailBean.DataBeanX.IssueBean> issueBeans;
    IssueAdapter issueAdapter;
    TitleDetailInfoAdapter titleDetailInfoAdapter;


    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    GoodDetailBean.DataBeanX.InfoBean infoBean;
    DetailInfoAdapter detailInfoAdapter;
    //????????????
    DetailWebAdapter detailWebAdapter;

    CarBean carBean = new CarBean();

    //??????????????????
    DetailBuyBarAdapter detailBuyBarAdapter;

    GoodDetailBean goodDetail;
    BuySelectorWindow buySelectorWindow;


    @Override
    protected int getLayout() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void initView() {
        virtualLayoutManager = new VirtualLayoutManager(this);
        recyDetail.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyDetail.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyDetail.setAdapter(delegateAdapter);

        galleryBeans = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(galleryBeans, this);
        delegateAdapter.addAdapter(galleryAdapter);

        ArrayList<String> detailInfoTitles = new ArrayList<>();
        detailInfoTitles.add("30???????????????");
        detailInfoTitles.add("48??????????????????");
        detailInfoTitles.add("???88????????????");
        titleDetailInfoAdapter = new TitleDetailInfoAdapter(detailInfoTitles, this);
        delegateAdapter.addAdapter(titleDetailInfoAdapter);
        titleDetailInfoAdapter.notifyDataSetChanged();


        detailInfoAdapter = new DetailInfoAdapter(this,infoBean);
        delegateAdapter.addAdapter(detailInfoAdapter);
        detailInfoAdapter.addEventListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                showBuyPopwindow(v);
            }
        });

        String attribute_title = "????????????";
        TitleAttributeAdapter titleAttributeAdapter = new TitleAttributeAdapter(attribute_title, this);
        delegateAdapter.addAdapter(titleAttributeAdapter);
        titleAttributeAdapter.notifyDataSetChanged();

        attributeBeans = new ArrayList<>();
        attributeAdapter = new AttributeAdapter(attributeBeans, this);
        delegateAdapter.addAdapter(attributeAdapter);

        detailWebAdapter = new DetailWebAdapter(this,infoBean);
        delegateAdapter.addAdapter(detailWebAdapter);

        issueBeans = new ArrayList<>();
        issueAdapter = new IssueAdapter(issueBeans, this);
        delegateAdapter.addAdapter(issueAdapter);

        detailBuyBarAdapter = new DetailBuyBarAdapter(this,carBean);
        delegateAdapter.addAdapter(detailBuyBarAdapter);
        initDetailBuyBar();

        initBottom();
    }

    private void initBottom(){
        imgCollect.setOnClickListener(this);
        layoutBuy.setOnClickListener(this);
        txtCar.setOnClickListener(this);
        txtBuy.setOnClickListener(this);

    }


    private void showBuyPopwindow(View v){
        buySelectorWindow = new BuySelectorWindow(GoodDetailActivity.this);
        int d = DisplayUtils.dp2px(this,60);
        buySelectorWindow.showAtLocation(v, Gravity.BOTTOM,0,d);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_collect:
                collect();
                break;
            case R.id.layout_buy:
            case R.id.txt_buy:
                if(buySelectorWindow == null){
                    showBuyPopwindow(v);
                }else{
                    selectBuy();
                }
                break;
            case R.id.txt_car:
                joinCar();
                break;
        }
    }

    /**
     * ??????
     */
    private void collect(){
        if(goodDetail != null){
            int goodId = goodDetail.getData().getInfo().getId();
            boolean bool = SpUtils.getInstance().getBoolean(String.valueOf(goodId));
            int rid;
            if(bool){
                SpUtils.getInstance().remove(String.valueOf(goodId));
                rid = R.mipmap.ic_uncollect;
            }else{
                SpUtils.getInstance().setValue(String.valueOf(goodId),true);
                rid = R.mipmap.ic_collect;
            }
            imgCollect.setImageResource(rid);
        }
    }

    private void selectBuy(){
        if(buySelectorWindow != null && goodDetail != null){
            int number = buySelectorWindow.getNumber();
            if(goodDetail.getData().getProductList().size() > 0){
                int goodsid = goodDetail.getData().getProductList().get(0).getGoods_id();
                int productid = goodDetail.getData().getProductList().get(0).getId();
                presenter.addCar(goodsid,number,productid);
            }

        }
    }

    /**
     * ???????????????
     */
    private void joinCar(){
        String token = SpUtils.getInstance().getString("token");
        if(token != null && token.length() > 0){

        }else{
            //???????????????
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void initDetailBuyBar(){
        detailBuyBarAdapter.addEventListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                switch (tag){
                    case 1:
                        gotoCar();
                        break;
                    case 2:
                        break;
                    case 3:
                        gotoCar();
                        break;
                }
            }
        });
    }

    private void gotoCar(){
        //?????????????????????????????????mainactivity ?????????????????????activity
        /*Intent intent = new Intent(GoodDetailActivity.this, MainActivity.class);
        intent.putExtra("type", Constants.PAGE_REQEST_CODE_GOODDETAIL);
        startActivity(intent);*/
        ActivityTask.gotoMainActivity(Constants.PAGE_REQEST_CODE_GOODDETAIL);
    }

    @Override
    protected IGood.Presenter createPersenter() {
        return new GoodDetailPresenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("goodid",0);
        if(id <= 0){

        }else{
            presenter.getGoodDetail(id);
        }
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        goodDetail = result;
        if(result.getData() != null){

            /**
             * ????????????
             */
            boolean bool = SpUtils.getInstance().getBoolean(String.valueOf(result.getData().getInfo().getId()));
            int rid = bool ? R.mipmap.ic_collect : R.mipmap.ic_uncollect;
            imgCollect.setImageResource(rid);

            infoBean = result.getData().getInfo();
            detailInfoAdapter.refreshData(infoBean);
            detailInfoAdapter.notifyDataSetChanged();
            detailWebAdapter.refreshData(infoBean);
            detailWebAdapter.notifyDataSetChanged();

            List<GoodDetailBean.DataBeanX.GalleryBean> gallery = result.getData().getGallery();
            galleryBeans.addAll(gallery);
            galleryAdapter.notifyDataSetChanged();


            List<GoodDetailBean.DataBeanX.AttributeBean> attribute = result.getData().getAttribute();
            attributeBeans.addAll(attribute);
            attributeAdapter.notifyDataSetChanged();

            List<GoodDetailBean.DataBeanX.IssueBean> issue = result.getData().getIssue();
            issueBeans.addAll(issue);
            issueAdapter.notifyDataSetChanged();

            detailBuyBarAdapter.setCurrentGoodId(result.getData().getInfo().getId());
        }
    }

    /**
     * ?????????????????????
     * @param result
     */
    @Override
    public void getCarReturn(CarBean result) {

    }

    /**
     * ?????????????????????
     * @param result
     */
    @Override
    public void addCarReturn(AddCarBean result) {
        gotoCar();
    }
}
