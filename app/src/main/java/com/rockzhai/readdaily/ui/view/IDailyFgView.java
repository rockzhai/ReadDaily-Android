package com.rockzhai.readdaily.ui.view;

import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

/**
 * Created by rockzhai on 2017/5/2.
 * Contact Me : zhaidyan@gmail.com
 */

public interface IDailyFgView {
    void setDataRefresh(Boolean refresh);
    TextView getTitleView();
    TextView getAuthorView();
    TextView getContentView();
    FloatingActionButton getSaveEssaybtn();
}
