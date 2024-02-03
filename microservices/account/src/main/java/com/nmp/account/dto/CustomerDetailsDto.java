package com.nmp.account.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @NoArgsConstructor
public class CustomerDetailsDto {

    CustomerDto customerDto;
    LoanDto loanDto;
    CardDto cardDto;

}
