package com.demoproject.rewardsprogram.util;

public class RewardsUtil {
    public static int getRewardPoints(double amount){
        int points = 0;
        if (amount > 50) {
            points += 1 * (Math.min(amount, 100) - 50);
        }
        if (amount > 100) {
            points += 2 * (amount - 100);
        }
        return points;
    }
}
