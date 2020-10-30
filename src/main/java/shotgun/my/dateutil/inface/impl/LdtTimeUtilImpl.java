package shotgun.my.dateutil.inface.impl;

import shotgun.my.dateutil.inface.TimeUtil;
import shotgun.my.dateutil.util.DateUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LdtTimeUtilImpl implements TimeUtil<LocalDateTime> {

    @Override
    public String format(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME));
    }

    @Override
    public String format(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public long getTimeMillis(LocalDateTime time) {
        return DateUtils.fromLDT2Instant(time).toEpochMilli();
    }

    @Override
    public long getTimeSeconds(LocalDateTime time) {
        return DateUtils.fromLDT2Instant(time).getEpochSecond();
    }


    @Override
    public LocalDateTime getDayStartTime(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);
    }

    @Override
    public long getDayStartTimeMillis(LocalDateTime time) {
        return getTimeMillis(getDayStartTime(time));
    }

    @Override
    public long getDayStartTimeSeconds(LocalDateTime time) {
        return getTimeSeconds(getDayStartTime(time));
    }

    @Override
    public LocalDateTime getDayEndTime(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);
    }

    @Override
    public long getDayEndTimeMillis(LocalDateTime time) {
        return getTimeMillis(getDayEndTime(time));
    }

    @Override
    public long getDayEndTimeSeconds(LocalDateTime time) {
        return getTimeSeconds(getDayEndTime(time));
    }
}