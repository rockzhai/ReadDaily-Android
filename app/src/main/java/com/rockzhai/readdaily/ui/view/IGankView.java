package com.rockzhai.readdaily.ui.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public interface IGankView {
    RecyclerView getRecyclerView();
    void setDataRefresh(boolean refresh);
}
