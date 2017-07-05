package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Bean.ShopCartOrderFootBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Interface.ShopCartOrderInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/7/4.
 */

public class ShopCartOrderAdapter extends RecyclerView.Adapter<ShopCartOrderAdapter.ViewHolder> {



    private List<ShopCartItemBean> mList;
    private Context mContext;
    private ShopCartOrderInterface mInterface;
    private View groupView;
    private View footView;
    private ShopCartOrderFootBean mFootBean;

    public ShopCartOrderAdapter(List<ShopCartItemBean> list, Context context, ShopCartOrderInterface anInterface) {
        mList = list;
        mContext = context;
        mInterface = anInterface;
    }

    public void reloadFootView(ShopCartOrderFootBean footBean) {
        mFootBean = footBean;
        notifyItemChanged(mList.size());
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mList.size()) {
            return 2;
        }
        ShopCartItemBean bean = mList.get(position);
        if (bean.getItemType().equals("0")) {
            return 0;
        }
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcart_list_group, parent, false);
            ViewHolder viewHolder = new ViewHolder(groupView);
            return viewHolder;
        }
        if (viewType == 2) {
            footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shopcart_order_foot, parent, false);
            ViewHolder viewHolder = new ViewHolder(footView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcart_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == mList.size()) {

            if (mFootBean == null) {
                return;
            }
            holder.mTextShopcartOrderFootCoupontext.setText(mFootBean.getCouponSub());
            holder.mTextShopcartOrderFootTime.setText(mFootBean.getTime());
            holder.mTextShopcartOrderFootTotal.setText("总价:"+mFootBean.getTotalPrice()+"元");
            holder.mTextShopcartOrderFootPackprice.setText("打包费:"+mFootBean.getPackPrice()+"元");
            holder.mTextShopcartOrderFootPayprice.setText("应付:"+mFootBean.getPayPrice()+"元");
            if (mFootBean.getGiveType() == 1) {
                holder.mButtonShopcartOrderFootOthergive.setSelected(true);
                holder.mButtonShopcartOrderFootPackgive.setSelected(false);
                holder.mButtonShopcartOrderFootCanteengive.setSelected(false);
            } else if (mFootBean.getGiveType() == 2) {
                holder.mButtonShopcartOrderFootOthergive.setSelected(false);
                holder.mButtonShopcartOrderFootPackgive.setSelected(true);
                holder.mButtonShopcartOrderFootCanteengive.setSelected(false);
            } else {
                holder.mButtonShopcartOrderFootOthergive.setSelected(false);
                holder.mButtonShopcartOrderFootPackgive.setSelected(false);
                holder.mButtonShopcartOrderFootCanteengive.setSelected(true);
            }

            holder.mLayoutShopcartOrderFootTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterface.onClickTime();
                }
            });
            holder.mLayoutShopcartOrderFootOthergive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterface.onClickGiveType(1);
                }
            });
            holder.mLayoutShopcartOrderFootPackgive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterface.onClickGiveType(2);
                }
            });
            holder.mLayoutShopcartOrderFootCanteengive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterface.onClickGiveType(3);
                }
            });
            return;
        }
        ShopCartItemBean bean = mList.get(position);
        if (bean.getItemType().equals("0")) {
            holder.groupText.setText(bean.getRes_name());
            return;
        }
        holder.mTextShopcartOrderName.setText(bean.getDish()+"x1");
        holder.mTextShopcartOrderPrice.setText("¥" + bean.getTotal());
        holder.mEditShopcartOrderRemark.setText(bean.getRemark());
        holder.mEditShopcartOrderRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mInterface.itemEditText(position,holder.mEditShopcartOrderRemark.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //groupView
        TextView groupText;

        //footView
        @BindView(R.id.text_shopcart_order_foot_total)
        TextView mTextShopcartOrderFootTotal;
        @BindView(R.id.text_shopcart_order_foot_packprice)
        TextView mTextShopcartOrderFootPackprice;
        @BindView(R.id.text_shopcart_order_foot_payprice)
        TextView mTextShopcartOrderFootPayprice;
        @BindView(R.id.text_shopcart_order_foot_coupontext)
        TextView mTextShopcartOrderFootCoupontext;
        @BindView(R.id.layout_shopcart_order_foot_coupon)
        ConstraintLayout mLayoutShopcartOrderFootCoupon;
        @BindView(R.id.layout_shopcart_order_foot_time)
        ConstraintLayout mLayoutShopcartOrderFootTime;
        @BindView(R.id.button_shopcart_order_foot_othergive)
        Button mButtonShopcartOrderFootOthergive;
        @BindView(R.id.button_shopcart_order_foot_packgive)
        Button mButtonShopcartOrderFootPackgive;
        @BindView(R.id.layout_shopcart_order_foot_packgive)
        ConstraintLayout mLayoutShopcartOrderFootPackgive;
        @BindView(R.id.button_shopcart_order_foot_canteengive)
        Button mButtonShopcartOrderFootCanteengive;
        @BindView(R.id.layout_shopcart_order_foot_canteengive)
        ConstraintLayout mLayoutShopcartOrderFootCanteengive;
        @BindView(R.id.text_shopcart_order_foot_time)
        TextView mTextShopcartOrderFootTime;
        @BindView(R.id.layout_shopcart_order_foot_othergive)
        ConstraintLayout mLayoutShopcartOrderFootOthergive;

        //itemView
        TextView mTextShopcartOrderPrice;
        TextView mTextShopcartOrderName;
        EditText mEditShopcartOrderRemark;

        public ViewHolder(View itemView) {
            super(itemView);
            if (groupView != null && groupView == itemView) {
                groupText = (TextView) itemView.findViewById(R.id.text_shopcart_list_group);
                return;
            }
            if (footView != null && footView == itemView) {
                ButterKnife.bind(this, footView);
                return;
            }

            mTextShopcartOrderName = (TextView) itemView.findViewById(R.id.text_shopcart_order_name);
            mTextShopcartOrderPrice = (TextView) itemView.findViewById(R.id.text_shopcart_order_price);
            mEditShopcartOrderRemark = (EditText) itemView.findViewById(R.id.edit_shopcart_order_remark);
        }
    }
}
