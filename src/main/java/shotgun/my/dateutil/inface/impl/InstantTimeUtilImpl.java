package shotgun.my.dateutil.inface.impl;

import shotgun.my.dateutil.inface.TimeUtil;
import shotgun.my.dateutil.util.DateUtils;

import java.time.Instant;

public class InstantTimeUtilImpl implements TimeUtil<Instant> {
    @Override
    public String format(Instant time) {
        return DateUtils.LOCAL_DATE_TIME.format(DateUtils.fromInstant2LDT(time));
    }

    @Override
    public String format(Instant time, String pattern) {
        return DateUtils.LOCAL_DATE_TIME.format(DateUtils.fromInstant2LDT(time), pattern);
    }

    @Override
    public long getTimeMillis(Instant time) {
        return time.toEpochMilli();
    }

    @Override
    public long getTimeSeconds(Instant time) {
        return time.getEpochSecond();
    }

    @Override
    public Instant getDayStartTime(Instant time) {
        return DateUtils.fromLDT2Instant(DateUtils.LOCAL_DATE_TIME.getDayStartTime(DateUtils.fromInstant2LDT(time)));
    }

    @Override
    public long getDayStartTimeMillis(Instant time) {
        return getDayStartTime(time).toEpochMilli();
    }

    @Override
    public long getDayStartTimeSeconds(Instant time) {
        return getDayStartTime(time).getEpochSecond();
    }

    @Override
    public Instant getDayEndTime(Instant time) {
        return DateUtils.fromLDT2Instant(DateUtils.LOCAL_DATE_TIME.getDayEndTime(DateUtils.fromInstant2LDT(time)));
    }

    @Override
    public long getDayEndTimeMillis(Instant time) {
        return getDayEndTime(time).toEpochMilli();
    }

    @Override
    public long getDayEndTimeSeconds(Instant time) {
        return getDayEndTime(time).getEpochSecond();
    }
}