package com.demoproject.rewardsprogram.controller;
import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.service.RewardsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardsControllerTest {

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    @Test
    public void testGetRewards() {
        // Create a rewards object to return
        Rewards rewards = new Rewards();
        rewards.setCustomerId("C001");
        rewards.setTotalRewards(450);
        Map<String, Integer> monthlyRewards = new HashMap<>();
        monthlyRewards.put("JANUARY 2020", 200);
        monthlyRewards.put("FEBRUARY 2020", 150);
        monthlyRewards.put("JANUARY 2021", 100);
        rewards.setMonthlyRewards(monthlyRewards);

        // Configure the rewards service to return the rewards
        when(rewardsService.calculateRewardsMonthly("C001")).thenReturn(rewards);

        // Call the getRewards method
        ResponseEntity<Rewards> response = rewardsController.getRewardsMonthly("C001");

        // Assert that the response is as expected
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(rewards, response.getBody());
    }
    @Test
    public void testCalculateRewards() {
        // Create a map of rewards to return
        Map<Month, Integer> rewards = new HashMap<>();
        rewards.put(Month.JANUARY, 100);
        rewards.put(Month.FEBRUARY, 50);

        // Configure the rewards service to return the rewards
        when(rewardsService.calculateRewardsMonthly("C001", LocalDate.of(2020,1,1), LocalDate.of(2020,2,28))).thenReturn(rewards);

        // Call the calculateRewards method
        Map<Month, Integer> response = rewardsController.getTotalRewardsMonthly("C001", LocalDate.of(2020,1,1), LocalDate.of(2020,2,28));

        // Assert that the response is as expected
        Assert.assertEquals(rewards, response);
    }
}