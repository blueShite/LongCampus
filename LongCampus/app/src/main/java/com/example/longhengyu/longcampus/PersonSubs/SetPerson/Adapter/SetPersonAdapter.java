package com.example.longhengyu.longcampus.PersonSubs.SetPerson.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Bean.SetPersonBean;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Interface.SetPersonInterface;
import com.example.longhengyu.longcampus.R;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/23.
 */

public class SetPersonAdapter extends RecyclerView.Adapter<SetPersonAdapter.ViewHolder> {

    private View headerView;
    private List<SetPersonBean> mList;
    private Context mContext;
    private SetPersonInterface mInterface;

    public SetPersonAdapter(List<SetPersonBean> list,Context context,SetPersonInterface anInterface){
        mList = list;
        mContext = context;
        mInterface = anInterface;
    }
    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        return 1;
    }

    @Override
    public SetPersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_setperson_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set_person,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SetPersonAdapter.ViewHolder holder, final int position) {
        if(position==0){
            return;
        }
        SetPersonBean bean = mList.get(position-1);
        holder.nameText.setText(bean.getName());
        holder.subText.setText(bean.getSub());
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickPersonItem(position-1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //itemView
        private TextView nameText;
        private TextView subText;
        private View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            if(headerView!=null&&itemView==headerView){
                return;
            }
            selfView = itemView;
            nameText = (TextView) itemView.findViewById(R.id.text_setPerson_name);
            subText = (TextView) itemView.findViewById(R.id.text_setPerson_sub);
        }
    }
}
