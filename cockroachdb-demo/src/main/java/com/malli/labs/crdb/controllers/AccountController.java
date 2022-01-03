package com.malli.labs.crdb.controllers;

import com.malli.labs.crdb.entities.Account;
import com.malli.labs.crdb.repositories.AccountRepository;
import com.malli.labs.crdb.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/greet")
    public String greet(){
        return "Hello World!";
    }

    @GetMapping("/accounts")
    public List<Account> listAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/account/{id}")
    public Account accountById(@PathVariable("id") long accountId) {
        return accountService.getAccount(accountId);
    }

    @PostMapping("/update-account")
    public String update(@RequestBody Account a) {

        return accountService.updateAccount(a);
    }

    @PostMapping("/add-account")
    public String add(@RequestBody Account a) {

        return accountService.addAccount(a);
    }




}
