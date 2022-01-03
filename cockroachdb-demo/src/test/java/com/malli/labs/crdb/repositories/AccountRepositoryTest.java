package com.malli.labs.crdb.repositories;

import com.malli.labs.crdb.entities.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAddAccount(){
        testEntityManager.persist(new Account(9,"Malli21","Asset", BigDecimal.valueOf(1000), Timestamp.from(Instant.now())));
        Account account = accountRepository.findById(Long.valueOf(9)).get();
        assertEquals(account.getName(),"Malli21");
    }
}