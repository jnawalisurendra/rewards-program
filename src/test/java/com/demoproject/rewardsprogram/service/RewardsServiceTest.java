package com.demoproject.rewardsprogram.service;

import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.model.Transaction;
import com.demoproject.rewardsprogram.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardsService rewardsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateRewards() {
        // Create a list of transactions for the customer
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, LocalDate.of(2020, 1, 10), 200.0));
        transactions.add(new Transaction(1, LocalDate.of(2020, 2, 15), 150.0));
        transactions.add(new Transaction(1, LocalDate.of(2021, 1, 20), 100.0));

        // Configure the transaction repository to return the list of transactions
        when(transactionRepository.findByCustomerId("C001")).thenReturn(transactions);

        // Calculate the rewards for the customer
        Rewards rewards = rewardsService.calculateRewardsMonthly("C001");

        // Assert that the rewards are as expected
        Assert.assertEquals(450, rewards.getTotalRewards().intValue());
        Map<String, Integer> monthlyRewards = rewards.getMonthlyRewards();
        Assert.assertEquals(250, monthlyRewards.get("JANUARY 2020").intValue());
        Assert.assertEquals(150, monthlyRewards.get("FEBRUARY 2020").intValue());
        Assert.assertEquals(50, monthlyRewards.get("JANUARY 2021").intValue());
    }

    @Test
    public void testCalculateRewardPoints() {
        // Create a list of transactions for the customer
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, LocalDate.of(2020, 1, 10), 200.0));
        transactions.add(new Transaction(1, LocalDate.of(2020, 2, 15), 150.0));
        transactions.add(new Transaction(1, LocalDate.of(2021, 1, 20), 100.0));
    }
}