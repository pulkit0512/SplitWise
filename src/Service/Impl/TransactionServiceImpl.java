package Service.Impl;

import DataObjects.Expense;
import DataObjects.User;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.UserDataStore;
import Service.TransactionService;

import java.util.List;
import java.util.Map;

public class TransactionServiceImpl implements TransactionService {
    private static final UserDataStore userDataStore = new UserDataStoreImpl();

    @Override
    public void showBalance(String user) {
        List<User> users = userDataStore.showUsersBalance(user);
        users.forEach(u -> {
            Map<String, Double> userBalance = u.getUsersBalance();
            userBalance.forEach((k, v) -> {
                if (v < 0.0) {
                    System.out.println(u.getUserId() + " owes " + k + ": " + -v);
                } else if (v > 0.0) {
                    System.out.println(k + " owes " + u.getUserId() + ": " + v);
                }
            });
        });
    }

    @Override
    public void getPassbook(String userId) {
        User user = userDataStore.getUser(userId);

        List<Expense> expenses = user.getExpenseTransactions();
        System.out.println("User Name: " + user.getName());
        expenses.forEach(expense -> System.out.println(expense.getAmountPaid() + " " + expense.getUserPaid() + " " + expense.getSplitType()));
    }
}
