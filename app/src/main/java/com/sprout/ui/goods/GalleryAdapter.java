package com.sprout.ui.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.GoodDetailBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * @创建时间 2021/4/13 19:49
 */
public class GalleryAdapter extends DelegateAdapter.Adapter {
    private ArrayList<GoodDetailBean.DataBeanX.GalleryBean> galleryBeans;
    private Context context;
    public GalleryAdapter(ArrayList<GoodDetailBean.DataBeanX.GalleryBean> galleryBeans, Context context) {
        this.galleryBeans = galleryBeans;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  HomeHolder){
            if (galleryBeans!=null){
                ((HomeHolder) holder).banner.setImages(galleryBeans);
                ((HomeHolder) holder).banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        GoodDetailBean.DataBeanX.GalleryBean galleryBean = (GoodDetailBean.DataBeanX.GalleryBean) path;
                        Glide.with(context).load(galleryBean.getImg_url()).into(imageView);
                    }
                }).start();



            }
        }
    }



    @Override
    public int getItemCount() {
        return 1;
    }
    class HomeHolder extends RecyclerView.ViewHolder{
        Banner banner;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }
}
