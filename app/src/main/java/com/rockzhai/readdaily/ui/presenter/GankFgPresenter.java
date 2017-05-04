package com.rockzhai.readdaily.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.gank.Gank;
import com.rockzhai.readdaily.bean.gank.Meizhi;
import com.rockzhai.readdaily.bean.gank.Video;
import com.rockzhai.readdaily.ui.adapter.GankListAdapter;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.view.IGankFgView;
import com.rockzhai.readdaily.util.DateUtils;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rockzhai on 2017/4/27.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankFgPresenter extends BasePresenter<IGankFgView> {
    private Context context;
    private IGankFgView gankFgView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager layoutManager;
    private GankListAdapter adapter;
    private List<Gank> list;
    private  int page = 1;
    private  int lastVisibleItem;
    private boolean isLoadMore = false;

    public GankFgPresenter (Context context) {
        this.context = context;
    }

    public void getGankData() {
        gankFgView = getView();
        if (gankFgView!=null) {
            mRecyclerView = gankFgView.getRecyclerView();
            layoutManager = gankFgView.getLayoutManager();

            if (isLoadMore) {
                page = page+1;
            }
            Observable.zip(gankApi.getMeizhiData(page),gankApi.getVideoData(page),this::createDesc)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(meizhi1 -> {
                        displayMeizhi(context,meizhi1.getResults(),gankFgView,mRecyclerView);
                    },this::loadError);



        }
    }

    private  void displayMeizhi (Context context,List<Gank> mzList,IGankFgView gankFgView,RecyclerView recyclerView) {
        if (isLoadMore) {
            if (mzList ==null) {
                gankFgView.setDataRefresh(false);
            } else  {
                list.addAll(mzList);
            }
            adapter.notifyDataSetChanged();
        } else {
            list = mzList;
            adapter = new GankListAdapter(context,list);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        gankFgView.setDataRefresh(false);
    }

    public void scrollRecycleView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        gankFgView.setDataRefresh(true);
                        isLoadMore = true;
                        new Handler().postDelayed(() -> getGankData(), 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        gankFgView.setDataRefresh(false);
        Toast.makeText(context, R.string.loadError , Toast.LENGTH_SHORT).show();
    }
    private Meizhi createDesc(Meizhi meizhi, Video video) {
        for (Gank gankmeizhi : meizhi.getResults()) {
            gankmeizhi.desc = gankmeizhi.desc + " " +
                    getVideoDesc(gankmeizhi.getPublishedAt(), video.getResults());
        }
        return meizhi;
    }

    //匹配同一天的福利描述和视频描述
    private String getVideoDesc(Date publishedAt, List<Gank> results) {
        String videoDesc = "";
        for (int i = 0; i < results.size(); i++) {
            Gank video = results.get(i);
            if (video.getPublishedAt() == null) video.setPublishedAt(video.getCreatedAt());
            if (DateUtils.isSameDate(publishedAt, video.getPublishedAt())) {
                videoDesc = video.getDesc();
                break;
            }
        }
        return videoDesc;
    }
}
