package DataStore.Impl;

import DataObjects.User;
import DataStore.UserDataStore;

import java.util.*;

public class UserDataStoreImpl implements UserDataStore {
    private static final Map<String, User> userDB = new HashMap<>();
    @Override
    public void addUser(User user) {
        userDB.put(user.getUserId(), user);
    }

    @Override
    public User getUser(String userId) {
        return userDB.get(userId);
    }

    @Override
    public void updateUser(User user) {
        addUser(user);
    }

    @Override
    public List<User> showUsersBalance(String userId) {
        if(userId.equals("ALL")) {
            return new ArrayList<>(userDB.values());
        } else {
            return Collections.singletonList(userDB.get(userId));
        }
    }
}
