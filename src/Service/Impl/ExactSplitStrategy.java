package Service.Impl;

import DataObjects.Expense;
import Service.SplitStrategy;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public void splitExpense(Expense expense) {
        double totalAmount = Double.parseDouble(df_obj.format(expense.getAmountPaid()));
        List<Double> shares = expense.getShares();
        double totalShares = shares.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        totalShares = Double.parseDouble(df_obj.format(totalShares));
        if (totalAmount != totalShares) {
            System.out.println("Amount and total share does not match.");
            return;
        }

        List<String> userPaidFor = expense.getPaidForUsers();
        String userPaid = expense.getUserPaid();

        List<String> response = new ArrayList<>();

        for(int i=0;i<userPaidFor.size();i++) {
            if (userPaidFor.get(i).equalsIgnoreCase(userPaid)) {
                continue;
            }

            double individualAmount = shares.get(i);
            String output = userPaidFor.get(i) + " owes " + userPaid + ": " + individualAmount;

            updateUser(individualAmount, userPaidFor.get(i), userPaid, expense);
            response.add(output);
        }

        response.forEach(System.out::println);
    }
}
