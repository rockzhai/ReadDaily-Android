package com.rockzhai.readdaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.TextView;

import com.rockzhai.readdaily.MyApp;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.Essay.EssayForDB;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.util.DBUtils;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class EssayActivity extends MVPBaseActivity {
    public static final String ESSAY = "essay";
    private EssayForDB essay ;
    private TextView title,author,content;
    private FloatingActionButton del_essay;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_essay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parseIntent();

        title = (TextView) findViewById(R.id.essay_title);
        author = (TextView) findViewById(R.id.essay_author);
        content = (TextView) findViewById(R.id.essay_content);
        del_essay = (FloatingActionButton) findViewById(R.id.del_essay);

        title.setText(essay.getTitle());
        author.setText(essay.getAuthor());
        content.setText(Html.fromHtml(essay.getContent()));


        del_essay.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("删除文章");
            dialog.setMessage("您确定删除"+essay.getAuthor()+"的《"+essay.getTitle()+"》这篇文章吗？");
            dialog.setNegativeButton("取消",null);
            dialog.setPositiveButton("确定", (dialog1, which) -> {
                DBUtils.delete(MyApp.dbOpenHelper,essay.getTitle());
                FavListActivity.upDateView(essay.getTitle());
                this.finish();
            });
            dialog.show();

        });

    }

    public static Intent newIntent(Context context, EssayForDB essay) {
        Intent intent = new Intent(context, EssayActivity.class);
        intent.putExtra(ESSAY, essay);
        return intent;
    }

    /**
     * 得到Intent传递的数据
     */
    private void parseIntent() {
        essay = (EssayForDB) getIntent().getSerializableExtra(ESSAY);
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
