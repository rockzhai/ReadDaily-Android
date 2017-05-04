package com.rockzhai.readdaily.ui.presenter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rockzhai.readdaily.MyApp;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.Essay.Essay;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.view.IDailyFgView;
import com.rockzhai.readdaily.util.DBUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.rockzhai.readdaily.MyApp.mContext;

/**
 * Created by rockzhai on 2017/5/2.
 * Contact Me : zhaidyan@gmail.com
 */

public class DailyFgPresenter extends BasePresenter<IDailyFgView> {
    private Context context;
    private Essay essay;
    private IDailyFgView dailyFgView;
    private TextView title, author, content;
    private FloatingActionButton saveEssay;

    public DailyFgPresenter(Context context) {
        this.context = context;
    }

    public void getDailyData() {
        dailyFgView = getView();
        if (dailyFgView != null) {

            title = dailyFgView.getTitleView();
            author = dailyFgView.getAuthorView();
            content = dailyFgView.getContentView();
            saveEssay = dailyFgView.getSaveEssaybtn();
            dailyApi.getDailyEssay()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(essay1 -> displayEssay(essay1, dailyFgView, title, author, content), this::loadError);
        }

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.loadError, Toast.LENGTH_SHORT).show();
    }

    private void displayEssay(Essay essay, IDailyFgView dailyFgView, TextView title, TextView author, TextView content) {
        title.setText(essay.getData().getTitle());
        author.setText("作者：" + essay.getData().getAuthor());
        content.setText(Html.fromHtml(essay.getData().getContent()));
        dailyFgView.setDataRefresh(false);
        saveEssay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b =DBUtils.insert(MyApp.dbOpenHelper, essay.getData().getTitle(), essay.getData().getAuthor(),essay.getData().getDigest(), essay.getData().getContent());

                if (b) {
                    Toast.makeText(context, R.string.fav_successed, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, R.string.fav_failed_alreadyfav, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
