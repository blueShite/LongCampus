package com.example.longhengyu.longcampus.FootList.SubFootList.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.Event.FootListShopEvent;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageClassesBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageCommodityBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.MyPackpageInterface;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by longhengyu on 2017/6/29.
 */

public class PackpageCommAdapter extends RecyclerView.Adapter<PackpageCommAdapter.ViewHolder> {

    private List<PackpageCommodityBean> mList;
    private Context mContext;
    private MyPackpageInterface mInterface;

    public PackpageCommAdapter (List<PackpageCommodityBean> list,Context context,MyPackpageInterface anInterface){
        mList = list;
        mContext = context;
        mInterface = anInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_packpage_commodity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        PackpageCommodityBean commodityBean = mList.get(position);
        holder.mTextPackCommName.setText(commodityBean.getDish());
        holder.mTextPackCommSub.setText(commodityBean.getIntro());
        holder.mTextPackCommNum.setText(commodityBean.getNums());
        Picasso.with(mContext).load(RequestTools.BaseUrl+commodityBean.getLitpic()).resize(120,120).into(holder.mImagePackCommHeader);
        holder.mButtonPackCommCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickCollection(position);
            }
        });
        if(commodityBean.getIfkeep()==0){
            holder.mButtonPackCommCollection.setSelected(false);
        }else {
            holder.mButtonPackCommCollection.setSelected(true);
        }
        holder.mImagePackCommAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickAddShopCart(position,holder.mTextPackCommNum);
            }
        });
        holder.mImagePackCommRedux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickReduxShopCart(position,holder.mTextPackCommNum);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_pack_comm_header)
        CircleImageView mImagePackCommHeader;
        @BindView(R.id.text_pack_comm_name)
        TextView mTextPackCommName;
        @BindView(R.id.button_pack_comm_collection)
        Button mButtonPackCommCollection;
        @BindView(R.id.text_pack_comm_sub)
        TextView mTextPackCommSub;
        @BindView(R.id.image_pack_comm_add)
        ImageView mImagePackCommAdd;
        @BindView(R.id.text_pack_comm_num)
        TextView mTextPackCommNum;
        @BindView(R.id.image_pack_comm_redux)
        ImageView mImagePackCommRedux;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}