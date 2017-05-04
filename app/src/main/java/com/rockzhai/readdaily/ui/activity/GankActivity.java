package com.rockzhai.readdaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.api.GankApi;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.ui.presenter.GankPresenter;
import com.rockzhai.readdaily.ui.view.IGankView;

import java.util.Calendar;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankActivity extends MVPBaseActivity<IGankView,GankPresenter> implements IGankView{
    private RecyclerView gank_list;
    private static final String DATE = "date";

    private int year;
    private int month;
    private int day;

    @Override
    protected GankPresenter createPresenter() {
        return new GankPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gank_list = (RecyclerView) findViewById(R.id.gank_list_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        gank_list.setLayoutManager(linearLayoutManager);

        setTitle("Gank's 今日特供");
        parseIntent();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }
    public static Intent newIntent(Context context,long date) {
        Intent intent = new Intent(context, GankActivity.class);
        intent.putExtra(GankActivity.DATE,date);
        return intent;
    }
    private void  parseIntent() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getIntent().getLongExtra(DATE,0));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Boolean isSetRefresh() {
        return true;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return gank_list;
    }

    @Override
    public void setDataRefresh(boolean refresh) {
        setRefresh(refresh);
    }
}
