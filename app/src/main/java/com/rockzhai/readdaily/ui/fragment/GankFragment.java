package com.rockzhai.readdaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.MVPBaseFragment;
import com.rockzhai.readdaily.ui.presenter.GankFgPresenter;
import com.rockzhai.readdaily.ui.view.IGankFgView;



/**
 * Created by rockzhai on 2017/4/27.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankFragment extends MVPBaseFragment<IGankFgView,GankFgPresenter> implements IGankFgView {

    private  GridLayoutManager gridLayoutManager;
    private RecyclerView content_list;

    @Override
    protected void initView(View rootView) {
        content_list = (RecyclerView) rootView.findViewById(R.id.content_list);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        content_list.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return content_list;
    }

    @Override
    protected GankFgPresenter createPresenter() {
        return new GankFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_gank;
    }
    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getGankData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefresh(true);
        mPresenter.getGankData();
        mPresenter.scrollRecycleView();
    }
}
