package com.synergybank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountsMsgDto {

    private Long accountNumber;
    private String name;
    private String email;
    private String mobileNumber;
}
