import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciej on 26.10.16.
 */
public class UserServiceImpl implements UserService {

    private List<User> userList = new ArrayList<User>();

    public User createUser(String userName, String firstName, String password) {
        System.out.println("createUSer");
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setPassword(password);

        userList.add(user);
        return user;
    }

    public User findUserByUserName(String userName) {
        System.out.println("find");
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }

        return null;
    }

    public int getUserCount() {
        System.out.println("getUserCount");
        return userList.size();
    }

}