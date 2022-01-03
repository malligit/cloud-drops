package com.malli.labs.crdb.repositories;

import com.malli.labs.crdb.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository  extends  CrudRepository<Account, Long> {
}


