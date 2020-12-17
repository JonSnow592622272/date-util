package shotgun.my.sweetutil.test.http;

/**
 * 契税奖励请求
 **/
public class JiangLiTest {

    public static void main(String[] args) throws InterruptedException {

        alwaysRun(() -> {
            System.out.println("娃哈哈");
        }, 1000);
    }

    public static void alwaysRun(Runnable runnable, long intervalMillis) throws InterruptedException {
        while (true) {
            runnable.run();
            Thread.sleep(intervalMillis);
        }
    }

}
