package shotgun.my.dateutil.inface.impl;

import shotgun.my.dateutil.inface.TimeUtil;
import shotgun.my.dateutil.util.DateUtils;

import java.time.Duration;
import java.util.Date;

public class DateTimeUtilImpl implements TimeUtil<Date> {

    @Override
    public String format(Date time) {
        return DateUtils.LOCAL_DATE_TIME.format(DateUtils.fromDate2LDT(time));
    }

    @Override
    public String format(Date time, String pattern) {
        return DateUtils.LOCAL_DATE_TIME.format(DateUtils.fromDate2LDT(time), pattern);
    }

    @Override
    public long getTimeMilli(Date time) {
        return time.getTime();
    }

    @Override
    public long getTimeSeconds(Date time) {
        return time.toInstant().getEpochSecond();
    }

    @Override
    public Date getDayStartTime(Date time) {
        return DateUtils.fromLDT2Date(DateUtils.LOCAL_DATE_TIME.getDayStartTime(DateUtils.fromDate2LDT(time)));
    }

    @Override
    public long getDayStartTimeMilli(Date time) {
        return DateUtils.LOCAL_DATE_TIME.getDayStartTimeMilli(DateUtils.fromDate2LDT(time));
    }

    @Override
    public long getDayStartTimeSeconds(Date time) {
        return DateUtils.LOCAL_DATE_TIME.getDayStartTimeSeconds(DateUtils.fromDate2LDT(time));
    }

    @Override
    public Date getDayEndTime(Date time) {
        return DateUtils.fromLDT2Date(DateUtils.LOCAL_DATE_TIME.getDayEndTime(DateUtils.fromDate2LDT(time)));
    }

    @Override
    public long getDayEndTimeMilli(Date time) {
        return DateUtils.LOCAL_DATE_TIME.getDayEndTimeMilli(DateUtils.fromDate2LDT(time));
    }

    @Override
    public long getDayEndTimeSeconds(Date time) {
        return DateUtils.LOCAL_DATE_TIME.getDayEndTimeSeconds(DateUtils.fromDate2LDT(time));
    }

    @Override
    public Duration diff(Date time, Date time2) {
        return Duration.between(time.toInstant(), time2.toInstant());
    }

    @Override
    public Date now() {
        return new Date();
    }


}