package com.malli.labs.crdb.services;

import com.malli.labs.crdb.entities.Account;
import com.malli.labs.crdb.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired(required = true)
    AccountRepository accRepo;

    public List<Account> getAccounts() {
        List accounts =  new ArrayList<Account>();
        accRepo.findAll().forEach(a -> accounts.add(a));
        return accounts;
    }

    public Account getAccount(long id) {
        return accRepo.findById(id).get();
    }

    public String updateAccount(Account a) {
        accRepo.save(a);
        return "Account Updated Successfully";
    }

    public String addAccount(Account a) {
        accRepo.save(a);
        return "Account Added Successfully";
    }
}
