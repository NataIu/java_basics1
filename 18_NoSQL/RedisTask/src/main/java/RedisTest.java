
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    private static final int userCount = 20;
    private static final int SLEEP = 1000; // миллисекунды
    public static final int frequency = 10;

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();
        redis.addStartUsers(userCount);

        int seconds = 0;
        while (true) {
            seconds++;
            if (seconds % frequency == 0) {
                int paidUserId = new Random().nextInt(userCount);
                String paidUserName = RedisStorage.getUserNameById(paidUserId);
                out.println("User "+ paidUserName + " paid.");
                redis.moveUserBToTopOfList(paidUserName);
            }
            out.println(redis.getTopUserAndMoveToEndOfList());
            Thread.sleep(SLEEP);
        }

    }
}
