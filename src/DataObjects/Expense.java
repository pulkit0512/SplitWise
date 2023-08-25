package DataObjects;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String userPaid;
    private double amountPaid;
    private List<String> paidForUsers;
    private Split splitType;
    private List<Double> shares;

    public String getUserPaid() {
        return userPaid;
    }

    public void setUserPaid(String userPaid) {
        this.userPaid = userPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public List<String> getPaidForUsers() {
        if (paidForUsers == null) {
            paidForUsers = new ArrayList<>();
        }
        return paidForUsers;
    }

    public void setPaidForUsers(List<String> paidForUsers) {
        this.paidForUsers = paidForUsers;
    }

    public Split getSplitType() {
        return splitType;
    }

    public void setSplitType(Split splitType) {
        this.splitType = splitType;
    }

    public List<Double> getShares() {
        if (shares == null) {
            shares = new ArrayList<>();
        }
        return shares;
    }

    public void setShares(List<Double> shares) {
        this.shares = shares;
    }
}
