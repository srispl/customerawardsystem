package com.spl.customerRewards.domain;

import java.util.Map;
import java.util.Objects;

public class CustomerInfo {

    String name;

    Map<String, Integer> transactions;

    int totalRewardPoints;

    Map<Integer, Integer> monthlyRewardPoints;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, Integer> transactions) {
        this.transactions = transactions;
    }


    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(int totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    public Map<Integer, Integer> getMonthlyRewardPoints() {
        return monthlyRewardPoints;
    }

    public void setMonthlyRewardPoints(Map<Integer, Integer> monthlyRewardPoints) {
        this.monthlyRewardPoints = monthlyRewardPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerInfo)) return false;
        CustomerInfo that = (CustomerInfo) o;
        return getTotalRewardPoints() == that.getTotalRewardPoints() && Objects.equals(getName(), that.getName()) && Objects.equals(getTransactions(), that.getTransactions()) && Objects.equals(getMonthlyRewardPoints(), that.getMonthlyRewardPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTransactions(), getTotalRewardPoints(), getMonthlyRewardPoints());
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "name='" + name + '\'' +
                ", transactions=" + transactions +
                ", totalRewardPoints=" + totalRewardPoints +
                ", monthlyRewardPoints=" + monthlyRewardPoints +
                '}';
    }
}
