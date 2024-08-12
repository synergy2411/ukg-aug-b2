package com.synergybank.accounts.mapper;

import com.synergybank.accounts.dto.AccountsDto;
import com.synergybank.accounts.dto.CustomerDto;
import com.synergybank.accounts.entity.Accounts;
import com.synergybank.accounts.entity.Customer;

public class AccountsMapper {

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){

        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setCustomerId(accountsDto.getCustomerId());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        return accounts;
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){

        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setCustomerId(accounts.getCustomerId());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        return accountsDto;
    }



}
