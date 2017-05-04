package com.rockzhai.readdaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.MVPBaseFragment;
import com.rockzhai.readdaily.ui.presenter.ReDailyFgPresenter;
import com.rockzhai.readdaily.ui.view.IReDailyFgView;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class RecommendFragment extends MVPBaseFragment<IReDailyFgView, ReDailyFgPresenter> implements IReDailyFgView{
    private View rootView;
    private TextView title, author, content;

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
    protected ReDailyFgPresenter createPresenter() {
        return new ReDailyFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_redaily_essay;
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
