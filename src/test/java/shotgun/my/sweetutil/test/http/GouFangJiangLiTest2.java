package shotgun.my.sweetutil.test.http;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.StringEscapeUtils;
import shotgun.my.sweetutil.http.HttpClientUtil;
import shotgun.my.sweetutil.http.HttpClientUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 契税奖励请求
 **/
public class GouFangJiangLiTest2 {

    public static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53" + ".0" +
                        ".2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI " + "MiniProgramEnv/Windows " +
                        "WindowsWechat");
        headerMap.put("client", "XCX");

        Map<String, String> baseParamMap = new LinkedHashMap<>();
        baseParamMap.put("laifang", "343106200800049");//343106201000029
        baseParamMap.put("name", "李超");
        baseParamMap.put("idcard", "432826198910233010");

        //公共配置
        baseParamMap.put("department", "1");
        baseParamMap.put("events", "1");
        baseParamMap.put("utoken", "oodke5K_jCvi93ecEFuhmNiV_N9s");
        baseParamMap.put("uniacid", "263");

        List<DateSelect> reqs = new ArrayList<>();
//        reqs.add(new DateSelect("99", "460"));
//        reqs.add(new DateSelect("100", "460"));
//        reqs.add(new DateSelect("101", "460"));
        //抢0106第6组
        reqs.add(new DateSelect("101", "474"));

        reqs.add(new DateSelect("89", "399"));
        reqs.add(new DateSelect("89", "402"));


        reqs.add(new DateSelect("102","475"));
        reqs.add(new DateSelect("102","477"));
        reqs.add(new DateSelect("102","479"));
        reqs.add(new DateSelect("103","481"));
        reqs.add(new DateSelect("103","483"));
        reqs.add(new DateSelect("103","485"));



        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        asaynRun(reqs.parallelStream().map(dateSelect -> () -> {
            Map<String, String> paramMap = new LinkedHashMap<>(baseParamMap);
            paramMap.put("date", dateSelect.getDate());
            paramMap.put("formId", dateSelect.getFormId());
            paramMap.put("timerange", dateSelect.getTimerange());
            try {
                String body = HttpClientUtil.buildFormString(paramMap);
//                System.out.println(body);
                String resultSec = HttpClientUtils.HTTP_CLIENT_OK
                        .execute("post", "https://yuyue.csdfa.cn//addons/yb_yuyue/index.php?s=api/user/subscribe",
                                "POST /addons/yb_yuyue/index.php?s=api/user/subscribe HTTP/1.1\n" + "Host: yuyue.csdfa.cn\n" + "Connection: keep-alive\n" + "Content-Length: 214\n" + "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.4.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat\n" + "client: XCX\n" + "content-type: application/x-www-form-urlencoded\n" + "Referer: https://servicewechat.com/wx1d783eaa59cfe328/7/page-frame.html\n" + "Accept-Encoding: gzip, deflate, br\n",
                                body);
                String result = StringEscapeUtils.unescapeJava(resultSec);

                if (!concurrentHashMap.containsKey(DigestUtils.md5Hex(result))) {
                    System.out.println("请求结果:" + result);
                    concurrentHashMap.put(DigestUtils.md5Hex(result), "");
                } else {
                    System.out.println("重复请求结果:" + result);
                }
            } catch (Exception e) {
                System.out.println("请求异常,msg=" + e.getMessage());
//                e.printStackTrace();
            }
        }), 500);

    }

    public static void asaynRun(Stream<Runnable> runnables, long intervalMillis) {
        if (runnables == null) {
            return;
        }
        List<Runnable> collect = runnables.collect(Collectors.toList());
        pool.execute(() -> {
            while (true) {
                for (Runnable runnable : collect) {
                    pool.execute(runnable);
                    try {
                        Thread.sleep(intervalMillis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


    public static class DateSelect {
        private String formId;
        private String date;
        private String timerange;

        public DateSelect() {
        }

        public DateSelect(String date, String timerange) {
            this.formId = UUID.randomUUID().toString().replace("-", "");
            this.date = date;
            this.timerange = timerange;
        }

        public String getFormId() {
            return formId;
        }

        public void setFormId(String formId) {
            this.formId = formId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTimerange() {
            return timerange;
        }

        public void setTimerange(String timerange) {
            this.timerange = timerange;
        }
    }

}
