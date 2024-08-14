package com.synergybank.accounts.controller;

import com.synergybank.accounts.dto.CustomerDetailsDto;
import com.synergybank.accounts.service.ICustomerDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerDetailsController {

    private ICustomerDetailsService iCustomerDetailsService;


    @GetMapping("/fetch-customer-details")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam String mobileNumber){
        CustomerDetailsDto customerDetailsDto = iCustomerDetailsService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
