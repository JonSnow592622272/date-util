package shotgun.my.sweetutil.time;

import java.time.Duration;

/**
 * 时间工具接口
 *
 * @author wulm
 * @date 2020/11/2 15:28
 **/
public interface TimeUtil<E> {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    //默认时间格式
    public static final String DEFAULT_DATE = YYYY_MM_DD;
    public static final String DEFAULT_DATE_TIME = YYYY_MM_DD_HH_MM_SS;

    String format(E time);

    String format(E time, String pattern);

    /**
     * 获取时间戳-毫秒数
     */
    long getTimeMilli(E time);

    /**
     * 获取时间戳-秒数
     */
    long getTimeSeconds(E time);


    /**
     * 获取一天的开始时间(0点时间)
     */
    E getDayStartTime(E time);

    /**
     * 获取一天的开始时间(0点时间) 时间戳-毫秒
     */
    long getDayStartTimeMilli(E time);


    /**
     * 获取一天的开始时间(0点时间) 时间戳-秒
     */
    long getDayStartTimeSeconds(E time);


    /**
     * 获取一天的结束时间('23:59:59.999999999')
     */
    E getDayEndTime(E time);

    /**
     * 获取一天的结束时间('23:59:59.999999999') 的时间戳-毫秒
     */
    long getDayEndTimeMilli(E time);


    /**
     * 获取一天的结束时间('23:59:59.999999999') 的时间戳-秒
     */
    long getDayEndTimeSeconds(E time);

    /**
     * 计算两个时间差(小的时间作为第1个参数，大的时间作为第二个参数，否则会返回负数)
     *
     * @param time  时间1（小）
     * @param time2 时间2（大）
     * @return 返回时间差对象
     * @author wulm
     **/
    Duration diff(E time, E time2);

    /**
     * 计算两个时间差(小的时间作为第1个参数，大的时间作为第二个参数，否则会返回负数)
     *
     * @param time  时间1（小）
     * @param time2 时间2（大）
     * @return 返回毫秒数
     * @author wulm
     **/
    default long diffToMilli(E time, E time2) {
        return diff(time, time2).toMillis();
    }

    /**
     * 计算与当前的时间差（当前时间之前结果为“正数”，当前时间之后结果为“负数”）
     * @author wulm
     * @date 2020/11/4 14:12
     * @param time 要计算的时间
     * @return 时间差对象
     **/
    default Duration diffNow(E time){
        return diff(time, now());
    }

    /**
     * 计算与当前时间的毫秒时间差（返回正数）
     *
     * @author wulm
     **/
    default long diffNowToMilliAbs(E time) {
        return Math.abs(diffNow(time).toMillis());
    }

    E now();


}
