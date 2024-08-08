package com.synergybank.accounts.service;

import com.synergybank.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
