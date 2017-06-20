package com.example.longhengyu.longcampus.PersonSubs.Collection.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Bean.CollectionBean;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Interface.CollectionInterface;
import com.example.longhengyu.longcampus.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/6/20.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {


    private List<CollectionBean> mList;
    private Context mContext;
    private CollectionInterface mInterface;

    public CollectionAdapter(List<CollectionBean> list, Context context,CollectionInterface anInterface) {

        mList = list;
        mContext = context;
        mInterface = anInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CollectionBean bean = mList.get(position);

        holder.mTextCollectionName.setText(bean.getDish());
        holder.mTextCollectionSub.setText(bean.getIntro());
        holder.mTextCollectionPrice.setText("¥"+bean.getPrice());
        String imageUrl = RequestTools.BaseUrl+bean.getLitpic();
        Picasso.with(mContext).load(imageUrl).resize(70,70).into(holder.mImageCollectionComm);
        holder.mButtonCollectionSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickCollectSelect();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Image_collection_comm)
        ImageView mImageCollectionComm;
        @BindView(R.id.text_collection_name)
        TextView mTextCollectionName;
        @BindView(R.id.text_collection_sub)
        TextView mTextCollectionSub;
        @BindView(R.id.text_collection_price)
        TextView mTextCollectionPrice;
        @BindView(R.id.button_collection_select)
        ImageView mButtonCollectionSelect;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
