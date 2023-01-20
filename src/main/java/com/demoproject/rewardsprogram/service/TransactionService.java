package com.demoproject.rewardsprogram.service;

import com.demoproject.rewardsprogram.model.Transaction;
import com.demoproject.rewardsprogram.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return transactions;
    }
}
