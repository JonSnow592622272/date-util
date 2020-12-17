package shotgun.my.sweetutil.test.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 契税奖励请求
 **/
public class JiangLiTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(16);

        alwaysRun(() -> {
            //异步执行
            pool.execute(() -> {


                System.out.println("娃哈哈");


            });

        }, 1000);
    }

    public static void alwaysRun(Runnable runnable, long intervalMillis) throws InterruptedException {
        while (true) {
            runnable.run();
            Thread.sleep(intervalMillis);
        }
    }

}
