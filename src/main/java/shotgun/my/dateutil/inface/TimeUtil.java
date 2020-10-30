package shotgun.my.dateutil.inface;

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
    long getTimeMillis(E time);

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
    long getDayStartTimeMillis(E time);


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
    long getDayEndTimeMillis(E time);


    /**
     * 获取一天的结束时间('23:59:59.999999999') 的时间戳-秒
     */
    long getDayEndTimeSeconds(E time);


}
