package cn.itcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static String getLocalDateTimeStr() {
        return format.format(new Date());
    }
}
