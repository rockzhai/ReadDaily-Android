package com.rockzhai.readdaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.ui.fragment.DailyFragment;
import com.rockzhai.readdaily.ui.fragment.GankFragment;
import com.rockzhai.readdaily.ui.fragment.RecommendFragment;


public class MainActivity extends MVPBaseActivity implements NavigationView.OnNavigationItemSelectedListener{


    private TextView textView;
    private FrameLayout contentFrame;
    private NavigationView leftDraw;
    private DrawerLayout mainDrawLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        mainDrawLayout = (DrawerLayout) findViewById(R.id.main_draw_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        leftDraw = (NavigationView) findViewById(R.id.left_draw);
        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
        initDrawerView();
        GankFragment fragment = new GankFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new RecommendFragment()).commit();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    private void initDrawerView() {

        toggle = new ActionBarDrawerToggle(this,mainDrawLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        mainDrawLayout.addDrawerListener(toggle);

        leftDraw.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_daily_read :
                setTitle(R.string.daily_one);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new DailyFragment()).commit();
                mainDrawLayout.closeDrawers();
                break;
            case R.id.nav_gank:
                setTitle(R.string.daily_nav_gank);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new GankFragment()).commit();
                mainDrawLayout.closeDrawers();
                break;
            case R.id.nav_home_recommend:
                setTitle(R.string.app_name);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new RecommendFragment()).commit();
                mainDrawLayout.closeDrawers();
                break;
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                break;
        }
        return true;
    }

    //  返回按键
    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (mainDrawLayout.isDrawerOpen(GravityCompat.START)) {
            mainDrawLayout.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), R.string.exit_text, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }
}
