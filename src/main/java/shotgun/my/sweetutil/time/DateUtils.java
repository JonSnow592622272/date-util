package shotgun.my.sweetutil.time;

import shotgun.my.sweetutil.time.impl.DateTimeUtilImpl;
import shotgun.my.sweetutil.time.impl.InstantTimeUtilImpl;
import shotgun.my.sweetutil.time.impl.LocalDateTimeTimeUtilImpl;
import shotgun.my.sweetutil.time.impl.LongTimeUtilImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具，这里是入口类
 * <p>
 * 时间类型之间传唤过程为：
 * 1：LocalDateTime ==> Instant ==> Date
 * 2：Date ==> Instant ==> LocalDateTime
 * 解释：
 * 1：LocalDateTime要转换为Date，则需要先转换为Instant
 * 2：Date要转换为LocalDateTime，则需要先转换为Instant
 * 注意：
 * <p>
 * 涉及到时间的计算都应转换到LocalDateTime处理。
 * 如果有多次计算时间（比如日期时间多次操作增减），应当使用LocalDateTime或者Instant提供的api
 *
 * @author wulm
 **/
public class DateUtils {

    /**
     * LocalDateTime工具
     */
    public static final LocalDateTimeTimeUtilImpl LOCAL_DATE_TIME = new LocalDateTimeTimeUtilImpl();

    /**
     * Instant工具
     */
    public static final InstantTimeUtilImpl INSTANT = new InstantTimeUtilImpl();

    /**
     * Date工具
     */
    public static final DateTimeUtilImpl DATE = new DateTimeUtilImpl();

    /**
     * 毫秒时间戳工具
     */
    public static final LongTimeUtilImpl LONG_MILLI = new LongTimeUtilImpl();


    public static LocalDateTime fromInstant2LDT(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date fromInstant2Date(Instant instant) {
        return Date.from(instant);
    }

    public static Instant fromDate2Instant(Date date) {
        return date.toInstant();
    }

    public static LocalDateTime fromDate2LDT(Date date) {
        return fromInstant2LDT(fromDate2Instant(date));
    }

    public static Instant fromLDT2Instant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static Date fromLDT2Date(LocalDateTime time) {
        return fromInstant2Date(fromLDT2Instant(time));
    }


    //以上方法是LocalDateTime、Instant、Date之间互相转换

}
