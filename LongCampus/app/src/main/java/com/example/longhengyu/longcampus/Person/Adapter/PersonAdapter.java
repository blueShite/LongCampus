package com.example.longhengyu.longcampus.Person.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.Person.Bean.PersonBean;
import com.example.longhengyu.longcampus.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by longhengyu on 2017/4/22.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {


    private List<PersonBean> mList;

    private View headerView;
    private View footerView;
    private Context mContext;

    public PersonAdapter(List<PersonBean> list, Context context){

        mList = list;
        mContext = context;

    }

    public void reloadItem(List<PersonBean> list){

        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        if(position==mList.size()-1){
            return 2;
        }
        return 1;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0){
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }
        if(viewType==2){

            footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_footer,parent,false);
            ViewHolder viewHolder = new ViewHolder(footerView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {

        if(position==0){

            for (int i=0;i<holder.mRelativeLayouts.size();i++){
                RelativeLayout relativeLayout = holder.mRelativeLayouts.get(i);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
            holder.titleNameText.setText("昵称:"+LoginManage.getInstance().getLoginBean().getNickname());
            holder.titlejifenText.setText("积分:"+LoginManage.getInstance().getLoginBean().getBalance());
            return;
        }
        if(position==mList.size()-1){
            holder.footerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return;
        }

        holder.nameText.setText(mList.get(position).getTitle());
        holder.mImageView.setImageResource(mList.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //item的视图
        private TextView nameText;
        private ImageView mImageView;

        //headerView的视图
        private List<RelativeLayout> mRelativeLayouts = new ArrayList<>();
        private TextView titleNameText;
        private TextView titlejifenText;

        //footerView的视图
        private Button footerBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            if(headerView!=null&&headerView==itemView){

                RelativeLayout shouRelat = (RelativeLayout)itemView.findViewById(R.id.relative_person_shouchang);
                RelativeLayout jifenRelat = (RelativeLayout)itemView.findViewById(R.id.relative_person_jifen);
                RelativeLayout dingdanRelat = (RelativeLayout)itemView.findViewById(R.id.relative_person_dingdan);
                mRelativeLayouts.add(shouRelat);
                mRelativeLayouts.add(jifenRelat);
                mRelativeLayouts.add(dingdanRelat);
                titleNameText = (TextView)itemView.findViewById(R.id.text_person_headerName);
                titlejifenText = (TextView)itemView.findViewById(R.id.text_person_titlejifen);

                return;
            }

            if(footerView!=null&&footerView==itemView){

                footerBtn = (Button)itemView.findViewById(R.id.button_person_logout);

                return;
            }
            nameText = (TextView)itemView.findViewById(R.id.text_person_itemName);
            mImageView = (ImageView)itemView.findViewById(R.id.image_person_item);
        }
    }
}
