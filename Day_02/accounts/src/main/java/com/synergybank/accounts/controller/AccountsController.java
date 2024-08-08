package com.synergybank.accounts.controller;

import com.synergybank.accounts.dto.CustomerDto;
import com.synergybank.accounts.dto.ResponseDto;
import com.synergybank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {

    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounts(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Successfully Created", HttpStatus.CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountService.fetchDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }
}
