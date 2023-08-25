package DataStore;

import DataObjects.User;

import java.util.List;

public interface UserDataStore {
    void addUser(User user);
    User getUser(String userId);
    void updateUser(User user);
    List<User> showUsersBalance(String userId);
}
