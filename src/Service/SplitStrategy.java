package Service;

import DataObjects.Expense;
import DataObjects.User;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.UserDataStore;

import java.text.DecimalFormat;
import java.util.Map;

public interface SplitStrategy {
    UserDataStore userDataStore = new UserDataStoreImpl();
    DecimalFormat df_obj = new DecimalFormat("#.##");
    void splitExpense(Expense expense);

    default void updateUser(double amount, String x, String y, Expense expense) {
        User userX = userDataStore.getUser(x);
        User userY = userDataStore.getUser(y);

        Map<String, Double> userXBalance = userX.getUsersBalance();
        double existingBalanceXtoY = userXBalance.getOrDefault(y, 0.0);
        userXBalance.put(y, existingBalanceXtoY-amount);
        userX.getExpenseTransactions().add(expense);

        Map<String, Double> userYBalance = userY.getUsersBalance();
        double existingBalanceYtoX = userYBalance.getOrDefault(x, 0.0);
        userYBalance.put(x, existingBalanceYtoX+amount);
        userY.getExpenseTransactions().add(expense);

        userDataStore.updateUser(userX);
        userDataStore.updateUser(userY);
    }
}
