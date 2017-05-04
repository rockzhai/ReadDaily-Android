package com.rockzhai.readdaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.MVPBaseFragment;
import com.rockzhai.readdaily.ui.presenter.DailyFgPresenter;
import com.rockzhai.readdaily.ui.view.IDailyFgView;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class DailyFragment extends MVPBaseFragment<IDailyFgView,DailyFgPresenter> implements IDailyFgView{
    private TextView title,author,content;

    @Override
    protected void initView(View rootView) {
        title = (TextView) rootView.findViewById(R.id.essay_title);
        author = (TextView) rootView.findViewById(R.id.essay_author);
        content = (TextView) rootView.findViewById(R.id.essay_content);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getDailyData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefresh(true);
        mPresenter.getDailyData();
    }

    @Override
    protected DailyFgPresenter createPresenter() {
        return new DailyFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_daily_essay;
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public TextView getTitleView() {
        return title;
    }

    @Override
    public TextView getAuthorView() {
        return author;
    }

    @Override
    public TextView getContentView() {
        return content;
    }
}
