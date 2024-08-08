package com.synergybank.accounts.service.impl;

import com.synergybank.accounts.dto.AccountsDto;
import com.synergybank.accounts.dto.CustomerDto;
import com.synergybank.accounts.entity.Accounts;
import com.synergybank.accounts.entity.Customer;
import com.synergybank.accounts.exceptions.CustomerAlreadyExistsException;
import com.synergybank.accounts.exceptions.ResourceNotFoundException;
import com.synergybank.accounts.mapper.AccountsMapper;
import com.synergybank.accounts.mapper.CustomerMapper;
import com.synergybank.accounts.repository.AccountsRepository;
import com.synergybank.accounts.repository.CustomerRepository;
import com.synergybank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        String mobileNumber = customerDto.getMobileNumber();

        Optional<Customer> foundCustomer = customerRepository.findByMobileNumber(mobileNumber);

        if(foundCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists for given mobile number " + mobileNumber);
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customerRepository.save(customer);

        Accounts accounts =  createNewAccount(customer.getCustomerId());
        accountsRepository.save(accounts);
    }


    private Accounts createNewAccount(Long customerId) {
        Accounts accounts = new Accounts();
        accounts.setAccountType("SAVINGS");
        Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setBranchAddress("201, Main Road, Noida, INDIA");
        accounts.setCustomerId(customerId);
        return accounts;

    }

    @Override
    public CustomerDto fetchDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "Customer Id", customer.getCustomerId().toString())
        );

        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        customerDto.setAccountsDto(accountsDto);

        return customerDto;

    }

}
