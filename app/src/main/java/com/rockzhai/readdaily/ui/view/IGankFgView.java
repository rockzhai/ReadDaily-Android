package com.rockzhai.readdaily.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by rockzhai on 2017/4/27.
 * Contact Me : zhaidyan@gmail.com
 */

public interface IGankFgView {
    void setDataRefresh(Boolean refresh);
    GridLayoutManager getLayoutManager();
    RecyclerView getRecyclerView();
}
