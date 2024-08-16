package com.synergybank.accounts.service;

import com.synergybank.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchDetails(String mobileNumber);

    boolean updateDetails(CustomerDto customerDto);

    boolean deleteAccounts(String mobileNumber);

    void updateCommunication(Long accountNumber);
}
