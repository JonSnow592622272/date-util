package shotgun.my.dateutil.test;

import shotgun.my.dateutil.util.DateUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtilsTest {

    public static void main(String[] args) {
        LocalDateTime test1 = LocalDateTime.now();
        Instant test2 = DateUtils.fromLDT2Instant(test1);
        Date test3 = DateUtils.fromLDT2Date(test1);

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

        System.out.println(DateUtils.LOCAL_DATE_TIME.format(test1));
        System.out.println(DateUtils.INSTANT.format(test2));
        System.out.println(DateUtils.DATE.format(test3));

        System.out.println(DateUtils.LOCAL_DATE_TIME.getDayStartTime(test1));
        System.out.println(DateUtils.INSTANT.getDayStartTime(test2));
        System.out.println(DateUtils.DATE.getDayStartTime(test3));

        System.out.println(DateUtils.LOCAL_DATE_TIME.getDayEndTime(test1));
        System.out.println(DateUtils.INSTANT.getDayEndTime(test2));
        System.out.println(DateUtils.DATE.getDayEndTime(test3));


    }

}
