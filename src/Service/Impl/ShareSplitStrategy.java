package Service.Impl;

import DataObjects.Expense;
import Service.SplitStrategy;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShareSplitStrategy implements SplitStrategy {
    @Override
    public void splitExpense(Expense expense) {
        double totalAmount = Double.parseDouble(df_obj.format(expense.getAmountPaid()));

        List<String> userPaidFor = expense.getPaidForUsers();
        String userPaid = expense.getUserPaid();
        List<Double> shares = expense.getShares();
        double totalShares = shares.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        List<String> response = new ArrayList<>();

        for(int i=0;i<userPaidFor.size();i++) {
            if (userPaidFor.get(i).equalsIgnoreCase(userPaid)) {
                continue;
            }
            if (i==0) {
                df_obj.setRoundingMode(RoundingMode.CEILING);
            } else {
                df_obj.setRoundingMode(RoundingMode.FLOOR);
            }
            double individualAmount = Double.parseDouble(df_obj.format((totalAmount*shares.get(i))/totalShares));
            String output = userPaidFor.get(i) + " owes " + userPaid + ": " + individualAmount;

            updateUser(individualAmount, userPaidFor.get(i), userPaid, expense);
            response.add(output);
        }

        response.forEach(System.out::println);
    }
}
