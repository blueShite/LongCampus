package com.example.longhengyu.longcampus.FootList.SubFootList.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.SaleInterface;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/6/26.
 */

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {

    private List<FeatureBean> mList;
    private Context mContext;
    private SaleInterface mInterface;

    public SaleAdapter(List<FeatureBean> list,Context context,SaleInterface anInterface){
        mList = list;
        mContext = context;
        mInterface = anInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        FeatureBean bean = mList.get(position);
        String imageStr = RequestTools.BaseUrl+bean.getMeal_litpic();
        Picasso.with(mContext).load(imageStr).resize(50,50).into(holder.mImageSaleComm);
        holder.mTextSalePrice.setText("现价:"+bean.getPrice()+"元");
        holder.mTextSaleOldprice.setText("原价:"+bean.getPrice()+"元");
        holder.mTextSaleName.setText(bean.getDish());
        holder.mImageSaleAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickAddShopCart(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_sale_comm)
        ImageView mImageSaleComm;
        @BindView(R.id.text_sale_oldprice)
        TextView mTextSaleOldprice;
        @BindView(R.id.text_sale_price)
        TextView mTextSalePrice;
        @BindView(R.id.image_sale_addBtn)
        ImageView mImageSaleAddBtn;
        @BindView(R.id.text_sale_name)
        TextView mTextSaleName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
