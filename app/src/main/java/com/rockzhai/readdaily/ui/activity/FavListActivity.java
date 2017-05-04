package com.rockzhai.readdaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rockzhai.readdaily.MyApp;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.Essay.EssayForDB;
import com.rockzhai.readdaily.ui.adapter.FavListAdapter;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.util.DBUtils;

import java.util.ArrayList;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class FavListActivity extends MVPBaseActivity {
    private static RecyclerView recyclerView;
    private static ArrayList<EssayForDB> mList;
    private static FavListAdapter mAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_fav_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的收藏");
        recyclerView = (RecyclerView) findViewById(R.id.fav_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = DBUtils.query(MyApp.dbOpenHelper);
        if (mList.isEmpty()) {
            Toast.makeText(this, "您还没有收藏过文章哦～～～", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("mList+++++", mList.get(0).getDigest());
            mAdapter = new FavListAdapter(this, mList);

            recyclerView.setAdapter(mAdapter);
        }

    }
    public static void upDateView(String title) {
        for (int i = 0; i < mList.size() ; i++) {
            if (mList.get(i).getTitle().equals(title)) {
                mList.remove(i);
                break;
            }
        }
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
