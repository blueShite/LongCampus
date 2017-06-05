package com.example.longhengyu.longcampus.Circle.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Circle.Bean.CircleHeaderBean;
import com.example.longhengyu.longcampus.Circle.Bean.CircleItemBean;
import com.example.longhengyu.longcampus.Circle.Interface.CircleInterface;
import com.example.longhengyu.longcampus.Information.Adapter.PicassoImageLoader;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by longhengyu on 2017/4/22.
 */

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {

    private View mheaderView;
    private Context mContext;

    private List<CircleHeaderBean> mBannerList;
    private List<CircleItemBean> mItemList;

    private CircleInterface mInterface;

    public CircleAdapter(List<CircleHeaderBean> bannerList,List<CircleItemBean> itemList,Context context,CircleInterface circleInterface){

        mBannerList = bannerList;
        mItemList = itemList;
        mContext = context;
        mInterface = circleInterface;

    }

    public void reloadItem(List<CircleHeaderBean> bannerList,List<CircleItemBean> itemList){

        mBannerList = bannerList;
        mItemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        return 1;
    }

    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            mheaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_circle_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(mheaderView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CircleAdapter.ViewHolder holder, final int position) {

        if(position==0){
            Banner banner = holder.mBanner;
            List<String> list = new ArrayList<>();
            for (int i=0;i<mBannerList.size();i++){
                String imageUrl = RequestTools.BaseUrl+mBannerList.get(i).getGroup_litpic();
                list.add(imageUrl);
            }
            banner.setImages(list);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int posit) {
                    mInterface.onClickHeader(posit);
                }
            });
            banner.start();
            return;
        }

        CircleItemBean bean = mItemList.get(position);
        holder.nameText.setText(bean.getGroup_title());
        String[] all=bean.getGroup_time().split("[ ]");
        holder.timeText.setText(all[0]);
        holder.zanText.setText("点赞"+"("+bean.getNum()+")");
        holder.pinglunText.setText("评论"+"("+bean.getReply_num()+")");
        String imageUrl = RequestTools.BaseUrl+bean.getGroup_litpic().get(0);
        Picasso.with(mContext).load(imageUrl).resize(70,70).into(holder.mImageView);
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Banner mBanner;

        ImageView mImageView;
        TextView nameText;
        TextView zanText;
        TextView pinglunText;
        TextView timeText;
        View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            if(mheaderView!=null&&mheaderView==itemView){

                mBanner = (Banner)itemView.findViewById(R.id.banner_circle);
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                mBanner.setImageLoader(new PicassoImageLoader());

                return;
            }

            mImageView = (ImageView) itemView.findViewById(R.id.circleImage_circle_item);
            nameText = (TextView)itemView.findViewById(R.id.text_circle_name);
            zanText = (TextView)itemView.findViewById(R.id.text_circle_zan);
            pinglunText = (TextView)itemView.findViewById(R.id.text_circle_pinglun);
            timeText = (TextView)itemView.findViewById(R.id.text_circle_time);
            selfView = itemView;
        }
    }
}
