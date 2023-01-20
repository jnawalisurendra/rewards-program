package com.demoproject.rewardsprogram.controller;

import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<Rewards> getRewardsMonthly(@PathVariable String customerId) {
        Rewards rewards = rewardsService.calculateRewardsMonthly(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    @GetMapping("/total")
    public Map<Month, Integer> getTotalRewardsMonthly(@RequestParam String customerId,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return rewardsService.calculateRewardsMonthly(customerId, start, end);
    }
}
