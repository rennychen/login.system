import java.util.Map;
import java.util.HashMap;

public class InMemoryUserRepository implements UserRepository{

    Map<String,User> data = new HashMap<>();

    @Override
    public void save(User user){
        data.put(user.getUserAccount(),user);
    }

}
