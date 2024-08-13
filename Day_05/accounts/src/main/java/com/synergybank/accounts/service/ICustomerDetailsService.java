package com.synergybank.accounts.service;

import com.synergybank.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
