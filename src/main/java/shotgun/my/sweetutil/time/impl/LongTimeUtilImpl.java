package shotgun.my.sweetutil.time.impl;

import shotgun.my.sweetutil.time.DateUtils;
import shotgun.my.sweetutil.time.TimeUtil;

import java.time.Duration;
import java.time.Instant;

/**
 * 毫秒时间戳工具
 * @author wulm
 **/
public class LongTimeUtilImpl implements TimeUtil<Long> {

    @Override
    public String format(Long time) {
        return DateUtils.INSTANT.format(Instant.ofEpochMilli(time));
    }

    @Override
    public String format(Long time, String pattern) {
        return DateUtils.INSTANT.format(Instant.ofEpochMilli(time), pattern);
    }

    @Override
    public long getTimeMilli(Long time) {
        return time;
    }

    @Override
    public long getTimeSeconds(Long time) {
        return time / 1000;
    }

    @Override
    public Long getDayStartTime(Long time) {
        return getDayStartTimeMilli(time);
    }

    @Override
    public long getDayStartTimeMilli(Long time) {
        return DateUtils.INSTANT.getDayStartTimeMilli(Instant.ofEpochMilli(time));
    }

    @Override
    public long getDayStartTimeSeconds(Long time) {
        return DateUtils.INSTANT.getDayStartTimeSeconds(Instant.ofEpochMilli(time));
    }

    @Override
    public Long getDayEndTime(Long time) {
        return getDayEndTimeMilli(time);
    }

    @Override
    public long getDayEndTimeMilli(Long time) {
        return DateUtils.INSTANT.getDayEndTimeMilli(Instant.ofEpochMilli(time));
    }

    @Override
    public long getDayEndTimeSeconds(Long time) {
        return DateUtils.INSTANT.getDayEndTimeSeconds(Instant.ofEpochMilli(time));
    }

    @Override
    public Duration diff(Long time, Long time2) {
        return DateUtils.INSTANT.diff(Instant.ofEpochMilli(time), Instant.ofEpochMilli(time2));
    }

    @Override
    public Long now() {
        return System.currentTimeMillis();
    }
}