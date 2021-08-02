import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;


import static java.lang.System.out;

public class RedisStorage {

    private int minKey = 0;
    private int maxKey = 0;

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> siteUsers;

    private final static String KEY = "ONLINE_USERS";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }

        rKeys = redisson.getKeys();
        siteUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);

    }

    public void addStartUsers(int count) {
        for (int i = 0; i < count; i++) {
            addUserNamedById(i);

        }
    }

    public String getTopUserAndMoveToEndOfList()
    {
        String topUser = getUpperUser();
        addUser(minKey-1,topUser);
        return topUser;
    }

    public void moveUserBToTopOfList(String user)
    {
        addUser(maxKey+1,user);
    }

    private void addUserNamedById(int user_id)
    {
        addUser(user_id, getUserNameById(user_id));
    }


    public static String getUserNameById(int user_id)
    {
        return "user" + String.valueOf(user_id);
    }

    private void addUser(int number, String user)
    {
        //ZADD
        siteUsers.add(number, user);
        maxKey = Math.max(maxKey, number);
        minKey = Math.min(minKey,number);
    }


    private String getUpperUser()
    {
        return siteUsers.takeLast();
    }

}
