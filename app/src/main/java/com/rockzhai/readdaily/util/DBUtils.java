package com.rockzhai.readdaily.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rockzhai.readdaily.MyApp;
import com.rockzhai.readdaily.bean.Essay.EssayForDB;

import java.util.ArrayList;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class DBUtils {
    /**
     * 添加收藏
     *
     * @param title
     * @param author
     * @param content
     * @return
     */
    public static boolean insert(DBOpenHelper helper, String title, String author, String digest,String content) {
        SQLiteDatabase db = helper.getWritableDatabase();  // 打开读写的数据库
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("digest",digest);
        values.put("content", content);
        long i = db.insert("essay", null, values);
        db.close();
        if (i == -1) {
            return false;
        }
        return true;
    }

    /**
     * 取消收藏
     *
     * @param title
     * @return
     */
    public static boolean delete(DBOpenHelper helper, String title) {
        SQLiteDatabase db = helper.getWritableDatabase(); // 打开读写的数据库
        int i = db.delete("essay", "title=?", new String[]{title});
        db.close();
        if (i == 0) {
            return false;
        }
        return true;
    }

    /**
     * 查询全部收藏
     *
     * @param helper
     * @return
     */
    public static ArrayList<EssayForDB> query(DBOpenHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();  // 打开只读的数据库
        // 得到结果集的游标
        Cursor cursor = db.rawQuery("select * from essay", null);
        ArrayList<EssayForDB> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            EssayForDB info = new EssayForDB();
            info.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            info.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            info.setDigest(cursor.getString(cursor.getColumnIndex("digest")));
            info.setContent(cursor.getString(cursor.getColumnIndex("content")));
            list.add(info);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 查询当前内容是否已经收藏
     *
     * @param helper
     * @param title  以 title 为 key
     * @return
     */public
    static boolean query(DBOpenHelper helper, String title) {
        SQLiteDatabase db = helper.getReadableDatabase();  // 打开一个只读的数据库
        Cursor cursor = db.query("essay", null, "title=?", new String[]{title}, null, null, null);
        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
