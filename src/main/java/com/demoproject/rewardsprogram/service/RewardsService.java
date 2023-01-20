package com.demoproject.rewardsprogram.service;

import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.repository.TransactionRepository;
import com.demoproject.rewardsprogram.model.Transaction;
import com.demoproject.rewardsprogram.util.RewardsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RewardsService {
    @Autowired
    private TransactionRepository transactionRepository;
    private Function<Double, Integer> calculatePoints = RewardsUtil::getRewardPoints;

    public Rewards calculateRewardsMonthly(String customerId) {
        // Retrieve the transactions for the customer from the repository
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        // Group the transactions by month and year
        Map<String, List<Transaction>> transactionsByMonth = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getDate().getMonth().name() + " " + t.getDate().getYear()));

        // Calculate the rewards for each month
        Map<String, Integer> monthlyRewards = transactionsByMonth.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().mapToInt(t -> calculatePoints.apply(t.getAmount())).sum()));

        // Calculate the total rewards
        Integer totalRewards = transactions.stream().mapToInt(t -> calculatePoints.apply(t.getAmount())).sum();

        // Create and return the Rewards object
        return new Rewards(customerId, monthlyRewards, totalRewards);
    }


    public Map<Month, Integer> calculateRewardsMonthly(String customerId, LocalDate start, LocalDate end) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, start, end);
        return transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getDate().getMonth(),
                        Collectors.summingInt(t -> calculatePoints.apply(t.getAmount()))));

    }

}
