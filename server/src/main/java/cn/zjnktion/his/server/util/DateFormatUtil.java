package cn.zjnktion.his.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zjnktion
 */
public class DateFormatUtil {

    private static final DateFormat DF = new SimpleDateFormat("yyyyMMddhhmmss:SSS");
    private static final DateFormat DF_D = new SimpleDateFormat("yyyyMMdd");

    public static final String NULL = "19000101000000:000";
    public static final String NULL_D = "19000101";

    public static String format(Date date) {
        if (date == null) {
            return NULL;
        }
        return DF.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return NULL_D;
        }
        return DF_D.format(date);
    }
}
