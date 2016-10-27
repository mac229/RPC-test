/**
 * Created by maciej on 26.10.16.
 */
public class UserServiceImpl implements UserService {

    public User createUser(String userName, String firstName, String password) {
        System.out.println("createUSer");
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setPassword(password);
        return user;
    }

    public User createUser(String userName, String password) {
        System.out.println("createUSer");
        return this.createUser(userName, null, password);
    }

    public User findUserByUserName(String userName) {
        System.out.println("find");
        //return database.findUserByUserName(userName);
        return null;
    }

    public int getUserCount() {
        System.out.println("getUserCount");
        //return database.getUserCount();
        return 0;
    }

}