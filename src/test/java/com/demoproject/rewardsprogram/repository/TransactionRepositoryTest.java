package com.demoproject.rewardsprogram.repository;

import com.demoproject.rewardsprogram.model.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindByCustomerId() {
        // Save some transactions to the repository
        Transaction transaction1 = new Transaction(1, LocalDate.of(2020, 1, 10), 200.0);
        Transaction transaction2 = new Transaction(1, LocalDate.of(2020, 2, 15), 150.0);
        Transaction transaction3 = new Transaction(2, LocalDate.of(2021, 1, 20), 100.0);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

        // Retrieve the transactions for customer 1
        List<Transaction> transactions = transactionRepository.findByCustomerId("C001");

        // Assert that the correct transactions were retrieved
        Assert.assertEquals(1L, transactions.get(0).getCustomerId());
        Assert.assertEquals(1L, transactions.get(1).getCustomerId());
    }

}