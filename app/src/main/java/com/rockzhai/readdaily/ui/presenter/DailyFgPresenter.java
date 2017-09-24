package com.rockzhai.readdaily.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.util.Log;
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
import com.rockzhai.readdaily.util.DBUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.rockzhai.readdaily.MyApp.mContext;

/**
 * Created by rockzhai on 2017/5/2.
 * Contact Me : zhaidyan@gmail.com
 */

public class DailyFgPresenter extends BasePresenter<IDailyFgView> {
    private Context context;
    private EssayForDB essay;
    private IDailyFgView dailyFgView;
    private TextView title, author, content;
    private FloatingActionButton saveEssay;
    String str = null;

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
//            dailyApi.getDailyEssay()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                   .subscribe(essay -> displayEssay(essay, dailyFgView, title, author, content), this::loadError);
            essay = new EssayForDB();

            Observable.create((Observable.OnSubscribe<Document>) subscriber -> {
                try {
                    Document document = Jsoup.connect("https://meiriyiwen.com/").get();
                    subscriber.onNext(document);
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onNext(null);
                    dailyFgView.setDataRefresh(false);
                    loadError(e);
                }
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).doOnNext(document -> {
                essay.setTitle(document.select("h1").text());
                essay.setAuthor(document.getElementsByClass("article_author").text());
                essay.setContent(document.getElementsByClass("article_text").toString());
                Log.i("this",document.getElementsByClass("article_text").text());
                essay.setDigest(document.getElementsByClass("article_text").text().substring(0,48));

               // str = docContent.text();
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(document -> {
                displayEssay(essay,dailyFgView,title,author,content);
                dailyFgView.setDataRefresh(false);
            });


        }

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.loadError, Toast.LENGTH_SHORT).show();
        dailyFgView.setDataRefresh(false);
    }

    private void displayEssay(EssayForDB essay, IDailyFgView dailyFgView, TextView title, TextView author, TextView content) {
//        title.setText(essay.getData().getTitle());
//        author.setText("作者：" + essay.getData().getAuthor());
//        content.setText(Html.fromHtml(essay.getData().getContent()));
//        dailyFgView.setDataRefresh(false);
//        MainActivity.updateReadNum(essay.getData().getWc());
//        saveEssay.setOnClickListener(v -> {
//            boolean b =DBUtils.insert(MyApp.dbOpenHelper, essay.getData().getTitle(), essay.getData().getAuthor(),essay.getData().getDigest(), essay.getData().getContent());
//
//            if (b) {
//                Toast.makeText(context, R.string.fav_successed, Toast.LENGTH_SHORT).show();
//
//            } else {
//                Toast.makeText(context, R.string.fav_failed_alreadyfav, Toast.LENGTH_SHORT).show();
//
//            }
//        });

        title.setText(essay.getTitle());
        author.setText("作者："+essay.getAuthor());
        content.setText(Html.fromHtml(essay.getContent()));
        dailyFgView.setDataRefresh(false);
        MainActivity.updateReadNum(essay.getContent().length());

        saveEssay.setOnClickListener(v -> {
            boolean b =DBUtils.insert(MyApp.dbOpenHelper, essay.getTitle(), essay.getAuthor(),essay.getDigest(), essay.getContent());

        if (b) {
            Toast.makeText(context, R.string.fav_successed, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, R.string.fav_failed_alreadyfav, Toast.LENGTH_SHORT).show();

        }
    });

    }
}
