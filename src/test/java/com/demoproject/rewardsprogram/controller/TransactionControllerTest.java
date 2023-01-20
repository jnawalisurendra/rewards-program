package com.demoproject.rewardsprogram.controller;

import com.demoproject.rewardsprogram.model.Rewards;
import com.demoproject.rewardsprogram.model.Transaction;
import com.demoproject.rewardsprogram.repository.TransactionRepository;
import com.demoproject.rewardsprogram.service.RewardsService;
import com.demoproject.rewardsprogram.service.TransactionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void testTotalTransaction() {
        Transaction transaction1 = new Transaction(1, "C001", 200.0, LocalDate.of(2020, 1, 10));
        Transaction transaction2 = new Transaction(3, "C001", 100.0, LocalDate.of(2021, 1, 20));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionService.getTransactions("C001")).thenReturn(transactions);

        ResponseEntity response = transactionController.getTransaction("C001");

        List<?> list = new ArrayList<>();
        if (response.getBody().getClass().isArray()) {
            list = Arrays.asList((Object[])response.getBody());
        } else if (response.getBody() instanceof Collection) {
            list = new ArrayList<>((Collection<?>)response.getBody());
        }

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(2, list.size());
    }
}