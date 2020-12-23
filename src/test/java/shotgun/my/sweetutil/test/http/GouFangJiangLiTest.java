package shotgun.my.sweetutil.test.http;

import shotgun.my.sweetutil.http.HttpClientUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 契税奖励请求
 **/
public class GouFangJiangLiTest {

    public static ExecutorService pool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) throws InterruptedException {

        Map<String, String> baseParamMap = new HashMap<>();
        baseParamMap.put("laifang", "343106201000029");
        baseParamMap.put("idcard", "431021199209277435");
        baseParamMap.put("name", "吴玲明");

        baseParamMap.put("department", "1");
        baseParamMap.put("events", "1");
        baseParamMap.put("uniacid", "263");
        baseParamMap.put("utoken", "oodke5K_jCvi93ecEFuhmNiV_N9s");


        asaynRun(100, () -> {
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53" + ".0" + ".2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI " + "MiniProgramEnv/Windows WindowsWechat");
            headerMap.put("client", "XCX");

            Map<String, String> paramMap = new HashMap<>(baseParamMap);
            baseParamMap.put("formId", UUID.randomUUID().toString().replace("-", ""));
            baseParamMap.put("date", "100");
            baseParamMap.put("timerange", "463");
            try {
                String result = HttpClientUtils.HTTP_CLIENT_OK
                        .httpPostForm("https://yuyue.csdfa.cn/addons/yb_yuyue/index.php?s=api/user/subscribe",
                                headerMap, paramMap);

                System.out.println("请求结果:" + result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }, () -> {
            System.out.println("娃哈哈222");
        });

        System.out.println("end...");
    }

    public static void asaynRun(long intervalMillis, Runnable... runnables) throws InterruptedException {
        if (runnables == null || runnables.length == 0) {
            return;
        }
        pool.execute(() -> {
            while (true) {
                for (Runnable runnable : runnables) {
                    pool.execute(runnable);
                }
                try {
                    Thread.sleep(intervalMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
