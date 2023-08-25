package DataObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private static int uniqueId = 0;
    private final String userId;
    private String name;
    private String email;
    private String mobile;
    private Map<String, Double> usersBalance;
    private List<Expense> expenseTransactions;

    public User() {
        uniqueId++;
        this.userId = "U"+uniqueId;
    }


    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Map<String, Double> getUsersBalance() {
        return usersBalance;
    }

    public void setUsersBalance(Map<String, Double> usersBalance) {
        this.usersBalance = usersBalance;
    }

    public List<Expense> getExpenseTransactions() {
        if (expenseTransactions == null) {
            expenseTransactions = new ArrayList<>();
        }
        return expenseTransactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
