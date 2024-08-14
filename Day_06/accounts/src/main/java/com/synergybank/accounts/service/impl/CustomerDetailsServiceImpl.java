package com.synergybank.accounts.service.impl;

import com.synergybank.accounts.dto.AccountsDto;
import com.synergybank.accounts.dto.CardsDto;
import com.synergybank.accounts.dto.CustomerDetailsDto;
import com.synergybank.accounts.dto.LoansDto;
import com.synergybank.accounts.entity.Accounts;
import com.synergybank.accounts.entity.Customer;
import com.synergybank.accounts.exceptions.ResourceNotFoundException;
import com.synergybank.accounts.mapper.AccountsMapper;
import com.synergybank.accounts.mapper.CustomerMapper;
import com.synergybank.accounts.repository.AccountsRepository;
import com.synergybank.accounts.repository.CustomerRepository;
import com.synergybank.accounts.service.ICustomerDetailsService;
import com.synergybank.accounts.service.clients.CardFeignClient;
import com.synergybank.accounts.service.clients.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private LoanFeignClient loanFeignClient;
    private CardFeignClient cardFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customer id", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());

        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loanFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
