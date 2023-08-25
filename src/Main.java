import DataObjects.Expense;
import DataObjects.Split;
import DataObjects.User;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.UserDataStore;
import Service.Impl.*;
import Service.SplitStrategy;
import Service.TransactionService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final File FILE = new File("/Users/pulkitarora/learning/SplitWise/input.txt");
    private static final TransactionService transactionService = new TransactionServiceImpl();
    private static final SplitStrategy equalSplitStrategy = new EqualSplitStrategy();
    private static final SplitStrategy exactSplitStrategy = new ExactSplitStrategy();
    private static final SplitStrategy percentSplitStrategy = new PercentSplitStrategy();
    private static final SplitStrategy shareSplitStrategy = new ShareSplitStrategy();

    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(FILE);
            addMockUserData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to SplitWise");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println("===" + input + "===");
            String[] params = input.split(" ");

            if (params[0].equalsIgnoreCase("EXPENSE")) {
                String userIdPaid = params[1].toUpperCase();
                double amount = Double.parseDouble(params[2]);
                int totalUsersPaid = Integer.parseInt(params[3]);
                List<String> userPaidFor = new ArrayList<>();
                int idx = 4;
                for (int i=0;i<totalUsersPaid;i++, idx++) {
                    userPaidFor.add(params[idx].toUpperCase());
                }
                Split split = Split.valueOf(params[idx++]);
                List<Double> shares = new ArrayList<>();
                if (!split.equals(Split.EQUAL)) {
                    for(int i=0;i<totalUsersPaid;i++, idx++) {
                        shares.add(Double.parseDouble(params[idx]));
                    }
                }

                Expense expense = new Expense();
                expense.setSplitType(split);
                expense.setAmountPaid(amount);
                expense.setUserPaid(userIdPaid);
                expense.setPaidForUsers(userPaidFor);
                expense.setShares(shares);

                if (split.equals(Split.EQUAL)) {
                    equalSplitStrategy.splitExpense(expense);
                } else if (split.equals(Split.SHARE)) {
                    shareSplitStrategy.splitExpense(expense);
                } else if (split.equals(Split.EXACT)) {
                    exactSplitStrategy.splitExpense(expense);
                } else if(split.equals(Split.PERCENT)) {
                    percentSplitStrategy.splitExpense(expense);
                } else {
                    System.out.println("Not a valid split strategy");
                }
            } else if (params[0].equalsIgnoreCase("SHOW")) {
                String userId = "ALL";
                if (params.length == 2) {
                    userId = params[1].toUpperCase();
                }
                transactionService.showBalance(userId);
            } else if (params[0].equalsIgnoreCase("PASSBOOK")) {
                String userId = params[1].toUpperCase();
                transactionService.getPassbook(userId);
            }
        }
    }

    private static void addMockUserData() {
        UserDataStore userDataStore = new UserDataStoreImpl();
        User user1 = new User();
        user1.setUsersBalance(new HashMap<>());
        user1.setName("Pulkit");
        user1.setEmail("Pulkit@gmail.com");
        user1.setMobile("12345");
        userDataStore.addUser(user1);

        User user2 = new User();
        user2.setUsersBalance(new HashMap<>());
        user2.setName("Arpit");
        user2.setEmail("Arpit@gmail.com");
        user2.setMobile("23456");
        userDataStore.addUser(user2);

        User user3 = new User();
        user3.setUsersBalance(new HashMap<>());
        user3.setName("Atul");
        user3.setEmail("Atul@gmail.com");
        user3.setMobile("34567");
        userDataStore.addUser(user3);

        User user4 = new User();
        user4.setUsersBalance(new HashMap<>());
        user4.setName("Sanyam");
        user4.setEmail("Sanyam@gmail.com");
        user4.setMobile("45678");
        userDataStore.addUser(user4);
    }
}