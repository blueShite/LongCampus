package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.PackpageClassesAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.PackpageCommAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageClassesBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageCommodityBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.MyPackpageInterface;
import com.example.longhengyu.longcampus.FootList.SubFootList.Presenter.MyPackpagePresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPackageFragment extends SupportFragment implements MyPackpageInterface {

    @BindView(R.id.packpage_classes_recycle)
    RecyclerView mPackpageClassesRecycle;
    @BindView(R.id.packpage_comm_recycle)
    RecyclerView mPackpageCommRecycle;
    @BindView(R.id.packpage_comm_refresh)
    TwinklingRefreshLayout mPackpageCommRefresh;

    private View mView;
    private String page;
    private PackpageClassesAdapter mClassesAdapter;
    private PackpageCommAdapter mCommAdapter;
    private List<PackpageClassesBean> mClassesBeenList = new ArrayList<>();
    private List<PackpageCommodityBean> mCommodityBeenList = new ArrayList<>();
    private MyPackpagePresenter mPresenter = new MyPackpagePresenter(this);
    private CanteenBean mCanteenBean;
    private PackpageClassesBean selectClassesBean;

    public MyPackageFragment(CanteenBean canteenBean) {
        // Required empty public constructor
        mCanteenBean = canteenBean;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_package, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page="1";
        mPresenter.requestClassesList(mCanteenBean.getRes_id());
        return mView;
    }

    private void customView(){

        mPresenter.setContext(getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mPackpageClassesRecycle.setLayoutManager(manager);
        LinearLayoutManager commManager = new LinearLayoutManager(getContext());
        mPackpageCommRecycle.setLayoutManager(commManager);

        mClassesAdapter = new PackpageClassesAdapter(mClassesBeenList,getContext(),this);
        mPackpageClassesRecycle.setAdapter(mClassesAdapter);

        mCommAdapter = new PackpageCommAdapter(mCommodityBeenList,getContext(),this);
        mPackpageCommRecycle.setAdapter(mCommAdapter);
        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mPackpageCommRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mPackpageCommRefresh.setBottomView(loadingView);
        mPackpageCommRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {

                page = "1";
                mPresenter.requestCommodityList(LoginManage.getInstance().getLoginBean().getHate(),
                        LoginManage.getInstance().getLoginBean().getLike_id(),page,selectClassesBean.getRes_id(),
                        LoginManage.getInstance().getLoginBean().getId(),LoginManage.getInstance().getLoginBean().getTaboos(),false);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {

                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestCommodityList(LoginManage.getInstance().getLoginBean().getHate(),
                        LoginManage.getInstance().getLoginBean().getLike_id(),page,selectClassesBean.getRes_id(),
                        LoginManage.getInstance().getLoginBean().getId(),LoginManage.getInstance().getLoginBean().getTaboos(),false);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void requestClassesSuccess(List<PackpageClassesBean> list, List<PackpageCommodityBean> commodityList) {

        mClassesBeenList.clear();
        mClassesBeenList.addAll(list);
        mClassesAdapter.notifyDataSetChanged();
        selectClassesBean = mClassesBeenList.get(0);
        if(commodityList==null){
            return;
        }
        mCommodityBeenList.clear();
        mCommodityBeenList.addAll(commodityList);
        mCommAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestCommoditySuccess(List<PackpageCommodityBean> list) {

        mPackpageCommRefresh.finishLoadmore();
        mPackpageCommRefresh.finishRefreshing();
        if(page.equals("1")){
            mCommodityBeenList.clear();
        }
        mCommodityBeenList.addAll(list);
        mCommAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestCommodityError(String error) {
        mPackpageCommRefresh.finishLoadmore();
        mPackpageCommRefresh.finishRefreshing();
    }

    @Override
    public void onClickClasses(int poist) {

        for (int i=0;i<mClassesBeenList.size();i++){
            if(i==poist){
                mClassesBeenList.get(i).setSelect(true);
            }else {
                mClassesBeenList.get(i).setSelect(false);
            }
        }
        mClassesAdapter.notifyDataSetChanged();
        selectClassesBean = mClassesBeenList.get(poist);
        page = "1";
        mPresenter.requestCommodityList(LoginManage.getInstance().getLoginBean().getHate(),
                LoginManage.getInstance().getLoginBean().getLike_id(),page,selectClassesBean.getRes_id(),
                LoginManage.getInstance().getLoginBean().getId(),LoginManage.getInstance().getLoginBean().getTaboos(),false);

    }

    @Override
    public void requestCollectionSucess(int poist) {

    }
}
