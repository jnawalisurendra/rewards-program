package com.demoproject.rewardsprogram.model;

import java.util.Map;

public class Rewards {
    private String customerId;
    private Map<String, Integer> monthlyRewards;
    private Integer totalRewards;

    public Rewards() {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public Integer getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(Integer totalRewards) {
        this.totalRewards = totalRewards;
    }

    public Rewards(String customerId, Map<String, Integer> monthlyRewards, Integer totalRewards) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }
}
