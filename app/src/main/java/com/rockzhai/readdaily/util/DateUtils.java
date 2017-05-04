package com.rockzhai.readdaily.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rockzhai on 2017/4/25.
 *
 * 工具类：日期
 *
 */

public class DateUtils {
    // 判断两日期是否为同一天
    public static boolean isSameDate(Date date_1,Date date_2) {
        Calendar cal_1 = Calendar.getInstance();
        cal_1.setTime(date_1);
        Calendar cal_2 = Calendar.getInstance();
        cal_2.setTime(date_2);

        return cal_1.get(Calendar.DAY_OF_YEAR) == cal_2.get(Calendar.DAY_OF_YEAR);
    }
}
