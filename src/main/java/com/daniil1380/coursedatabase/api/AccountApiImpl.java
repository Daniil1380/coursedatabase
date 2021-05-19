package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.service.AccountService;
import io.swagger.client.api.AccountApi;
import io.swagger.client.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountApiImpl extends AccountApi {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/accounts/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Account> getAccounts() {
        return accountService.listAll().stream().map(AccountEntity::toAccount).collect(Collectors.toList());
    }


    @RequestMapping(value = "/accounts/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Account postAccount(@RequestBody Account body) {
        accountService.save(new AccountEntity(body));
        return body;
    }

    @RequestMapping(value = "/accounts/{accountId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    @Override
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        if (accountService.get(accountId.intValue()).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found");
        }
        accountService.delete(accountId.intValue());

    }


}
