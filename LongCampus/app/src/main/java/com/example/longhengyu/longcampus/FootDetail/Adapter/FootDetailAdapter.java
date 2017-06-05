package com.example.longhengyu.longcampus.FootDetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailItemBean;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/27.
 */

public class FootDetailAdapter extends RecyclerView.Adapter<FootDetailAdapter.ViewHolder> {

    private View headerView;
    private Context mContext;

    private List<FootDetailItemBean> mList;
    private ShopCartBean mShopCartBean;

    public FootDetailAdapter(List<FootDetailItemBean> list,ShopCartBean shopCartBean, Context context){

        mList = list;
        mShopCartBean = shopCartBean;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        return 1;
    }

    @Override
    public FootDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_footdetail_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footdetail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FootDetailAdapter.ViewHolder holder, int position) {

        if(position==0){

            Picasso.with(mContext).load(RequestTools.BaseUrl+mShopCartBean.getLitpic()).fit().centerCrop().into(holder.headerImage);
            holder.nameText.setText(mShopCartBean.getDish());
            holder.numText.setText("月售"+mShopCartBean.getSalnum()+"份");
            holder.priceText.setText("¥"+mShopCartBean.getPrice());
            holder.oldPriceText.setText("原价"+mShopCartBean.getPrice()+"元");
            holder.addNumText.setText(mShopCartBean.getAddNum());
            return;
        }

        FootDetailItemBean bean = mList.get(position);
        holder.itemNameText.setText(bean.getName());
        holder.itemSubText.setText(bean.getSub());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //item视图
        private TextView itemNameText;
        private TextView itemSubText;

        //header视图
        private ImageView headerImage;
        private ImageView addImage;
        private ImageView jianImage;
        private TextView nameText;
        private TextView numText;
        private TextView priceText;
        private TextView oldPriceText;
        private TextView addNumText;


        public ViewHolder(View itemView) {
            super(itemView);

            if(headerView!=null&&headerView==itemView){

                headerImage = (ImageView)itemView.findViewById(R.id.image_footDetail_header);
                addImage = (ImageView)itemView.findViewById(R.id.image_footDetail_add);
                jianImage = (ImageView)itemView.findViewById(R.id.image_footDetail_jian);
                nameText = (TextView)itemView.findViewById(R.id.text_footDetail_name);
                numText = (TextView)itemView.findViewById(R.id.text_footDetail_num);
                priceText = (TextView)itemView.findViewById(R.id.text_footDetail_price);
                oldPriceText = (TextView)itemView.findViewById(R.id.text_footDetail_oldPrice);
                addNumText = (TextView)itemView.findViewById(R.id.text_footDetail_addNum);

                return;
            }

            itemNameText = (TextView)itemView.findViewById(R.id.text_footDetailItem_name);
            itemSubText = (TextView)itemView.findViewById(R.id.text_footDetailItem_sub);
        }
    }
}
