package com.rockzhai.readdaily.ui.view;

import android.widget.TextView;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public interface IReDailyFgView {
    void setDataRefresh(Boolean refresh);
    TextView getTitleView();
    TextView getAuthorView();
    TextView getContentView();
}
