package shotgun.my.sweetutil.test.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 契税奖励请求
 **/
public class GouFangJiangLiTest {

    public static ExecutorService pool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) throws InterruptedException {


        asaynRun(1000, () -> {
                    System.out.println("娃哈哈");
                }, () -> {
                    System.out.println("娃哈哈222");
                }
        );

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
