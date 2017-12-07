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
import com.rockzhai.readdaily.bean.Essay.EssayForDB;
import com.rockzhai.readdaily.ui.activity.MainActivity;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.view.IDailyFgView;
import com.rockzhai.readdaily.ui.view.IReDailyFgView;
import com.rockzhai.readdaily.util.DBUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rockzhai on 2017/5/2.
 * Contact Me : zhaidyan@gmail.com
 */

public class ReDailyFgPresenter extends BasePresenter<IReDailyFgView> {
    private Context context;
    private EssayForDB essay;
    private IReDailyFgView iReDailyFgView;
    private TextView title, author, content;
    private FloatingActionButton saveEssay;

    public ReDailyFgPresenter(Context context) {
        this.context = context;
    }

    public void getReDailyData() {
        iReDailyFgView = getView();
        if (iReDailyFgView != null) {

            title = iReDailyFgView.getTitleView();
            author = iReDailyFgView.getAuthorView();
            content = iReDailyFgView.getContentView();
            saveEssay = iReDailyFgView.getSaveEssaybtn();

            essay = new EssayForDB();

            Observable.create((Observable.OnSubscribe<Document>) subscriber -> {
                try {
                    Document document = Jsoup.connect("https://meiriyiwen.com/random").get();
                        subscriber.onNext(document);

                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onNext(null);
                    loadError(e);
                    iReDailyFgView.setDataRefresh(false);
                }
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).doOnNext(document -> {
                Element docContent = document.getElementById("article_show");

                essay.setTitle(document.select("h1").text());
                essay.setAuthor(document.getElementsByClass("article_author").text());
                essay.setContent(document.getElementsByClass("article_text").toString());
                essay.setDigest(document.getElementsByClass("article_text").text().substring(0, 48));

                // str = docContent.text();
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(document -> {
                displayEssay(essay, iReDailyFgView, title, author, content);
                iReDailyFgView.setDataRefresh(false);
            });
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.loadError, Toast.LENGTH_SHORT).show();
        iReDailyFgView.setDataRefresh(false);
    }

    private void displayEssay(EssayForDB essay, IReDailyFgView dailyFgView, TextView title, TextView author, TextView content) {
        title.setText(essay.getTitle());
        author.setText("作者：" + essay.getAuthor());
        content.setText(Html.fromHtml(essay.getContent()));
        dailyFgView.setDataRefresh(false);
        MainActivity.updateReadNum(essay.getContent().length());
        saveEssay.setOnClickListener(v -> {
            boolean b = DBUtils.insert(MyApp.dbOpenHelper, essay.getTitle(), essay.getAuthor(), essay.getDigest(), essay.getContent());

            if (b) {
                Toast.makeText(context, R.string.fav_successed, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, R.string.fav_failed_alreadyfav, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
