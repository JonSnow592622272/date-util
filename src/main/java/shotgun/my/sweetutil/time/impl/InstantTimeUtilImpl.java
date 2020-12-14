package shotgun.my.sweetutil.time.impl;

import shotgun.my.sweetutil.time.TimeUtil;
import shotgun.my.sweetutil.time.DateUtils;

import java.time.Duration;
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
    public long getTimeMilli(Instant time) {
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
    public long getDayStartTimeMilli(Instant time) {
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
    public long getDayEndTimeMilli(Instant time) {
        return getDayEndTime(time).toEpochMilli();
    }

    @Override
    public long getDayEndTimeSeconds(Instant time) {
        return getDayEndTime(time).getEpochSecond();
    }

    @Override
    public Duration diff(Instant time, Instant time2) {
        return Duration.between(time, time2);
    }

    @Override
    public Instant now() {
        return Instant.now();
    }
}