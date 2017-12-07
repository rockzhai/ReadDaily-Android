package com.rockzhai.readdaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.MVPBaseFragment;
import com.rockzhai.readdaily.ui.presenter.DailyFgPresenter;
import com.rockzhai.readdaily.ui.view.IDailyFgView;
import com.rockzhai.readdaily.util.StateUtils;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class DailyFragment extends MVPBaseFragment<IDailyFgView,DailyFgPresenter> implements IDailyFgView{
    private TextView title,author,content;
    private FloatingActionButton saveEssay;
    @Override
    protected void initView(View rootView) {
        title = (TextView) rootView.findViewById(R.id.essay_title);
        author = (TextView) rootView.findViewById(R.id.essay_author);
        content = (TextView) rootView.findViewById(R.id.essay_content);
        saveEssay = (FloatingActionButton) rootView.findViewById(R.id.save_essay);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);

        if (StateUtils.isNetworkAvailable(getContext())) {
            mPresenter.getDailyData();
        }else {
            setDataRefresh(false);
            if (content.getText().toString()=="") {
                content.setText("网络无连接，请检查网络后重试");
            }
            Toast.makeText(getContext(), "网络无连接，请检查网络后重试", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefresh(true);
        if (StateUtils.isNetworkAvailable(getContext())) {
            mPresenter.getDailyData();
        }else {
            setDataRefresh(false);
            if (content.getText().toString()=="") {
                content.setText("网络无连接，请检查网络后重试");
            }
            Toast.makeText(getContext(), "网络无连接，请检查网络后重试", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public FloatingActionButton getSaveEssaybtn() {
        return saveEssay;
    }
}
