package com.synergybank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;
    private Boolean communicationSw;
}
