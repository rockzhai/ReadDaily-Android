package com.rockzhai.readdaily.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.gank.Gank;
import com.rockzhai.readdaily.ui.adapter.GankActivityAdapter;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.view.IGankView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankPresenter extends BasePresenter<IGankView>{
    public GankPresenter(Context context) {
        this.mContext = context;
    }
    public void getGankList(int year ,int month,int day) {
        gankView = getView();
        if (gankView!=null) {
            recyclerView = gankView.getRecyclerView();

            gankApi.getGankData(year,month,day)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(gankData -> {
                        displayGankList(mContext,gankData.results.getAllResults(),gankView,recyclerView);
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, R.string.loadError, Toast.LENGTH_SHORT).show();
    }

    private void displayGankList(Context context, List<Gank> gankList, IGankView gankView, RecyclerView recyclerView) {
        GankActivityAdapter adapter = new GankActivityAdapter(context, gankList);
        recyclerView.setAdapter(adapter);
        gankView.setDataRefresh(false);
    }
    private Context mContext;
    private IGankView gankView;
    private RecyclerView recyclerView;
}
