package com.demoproject.rewardsprogram.controller;

import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.model.Transaction;
import com.demoproject.rewardsprogram.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable String customerId) {
        List<Transaction> transactions= transactionService.getTransactions(customerId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

