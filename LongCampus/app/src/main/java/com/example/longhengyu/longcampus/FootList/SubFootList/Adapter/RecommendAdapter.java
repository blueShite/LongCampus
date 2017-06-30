package com.example.longhengyu.longcampus.FootList.SubFootList.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.RecommendInterface;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by longhengyu on 2017/6/26.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {


    private List<FeatureBean> mList;
    private Context mContext;
    private RecommendInterface mInterface;

    public RecommendAdapter(List<FeatureBean> list, Context context,RecommendInterface anInterface) {
        mList = list;
        mContext = context;
        mInterface = anInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcart, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FeatureBean bean = mList.get(position);
        String imageStr = RequestTools.BaseUrl+bean.getMeal_litpic();
        Picasso.with(mContext).load(imageStr).resize(100,100).into(holder.mImageShopCartItem);
        holder.mTextShopCartItemName.setText(bean.getDish());
        holder.mTextShopCartItemSub.setText(bean.getMealinfo());
        holder.mTextShopCartItemPrice.setText("¥"+bean.getPrice()+"元");
        holder.mTextShopCartOldPrice.setText("原价:"+bean.getPrice()+"元");
        holder.mTextShopCartOldNum.setText("已售"+bean.getNum()+"份");
        holder.mTextShopCartItemNum.setText(bean.getAddNum());
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickItem(position);
            }
        });
        holder.mImageShopCartItemJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickItemAdd(position,holder.mTextShopCartItemNum);
            }
        });
        holder.mImageShopCartItemJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickItemReduce(position,holder.mTextShopCartItemNum);
            }
        });
        if(bean.getIfkeep()==0){
            holder.mButtonItemShopcartCollection.setSelected(false);
        }else {
            holder.mButtonItemShopcartCollection.setSelected(true);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_shopCartItem)
        ImageView mImageShopCartItem;
        @BindView(R.id.text_shopCartItem_name)
        TextView mTextShopCartItemName;
        @BindView(R.id.text_shopCartItem_sub)
        TextView mTextShopCartItemSub;
        @BindView(R.id.text_shopCartItem_price)
        TextView mTextShopCartItemPrice;
        @BindView(R.id.text_shopCart_oldPrice)
        TextView mTextShopCartOldPrice;
        @BindView(R.id.text_shopCart_oldNum)
        TextView mTextShopCartOldNum;
        @BindView(R.id.image_shopCartItem_jia)
        ImageView mImageShopCartItemJia;
        @BindView(R.id.text_shopCartItem_num)
        TextView mTextShopCartItemNum;
        @BindView(R.id.image_shopCartItem_jian)
        ImageView mImageShopCartItemJian;
        @BindView(R.id.button_item_shopcart_collection)
        Button mButtonItemShopcartCollection;

        private View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            selfView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
