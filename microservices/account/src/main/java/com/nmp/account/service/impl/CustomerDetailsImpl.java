package com.nmp.account.service.impl;

import com.nmp.account.dto.CustomerDetailsDto;
import com.nmp.account.dto.CustomerDto;
import com.nmp.account.service.IAccountService;
import com.nmp.account.service.ICustomerDetailsService;
import com.nmp.account.service.ICustomerService;
import com.nmp.account.service.client.CardFeignClient;
import com.nmp.account.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CustomerDetailsImpl implements ICustomerDetailsService {

    private IAccountService accountService;
    private LoanFeignClient loanFeignClient;
    private CardFeignClient cardFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationID, String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setCustomerDto(accountService.fetchAccount(mobileNumber));
        customerDetailsDto.setLoanDto(loanFeignClient.fetchLoanDetails(correlationID, mobileNumber).getBody());
        customerDetailsDto.setCardDto(cardFeignClient.fetchCardDetails(correlationID, mobileNumber).getBody());
        return customerDetailsDto;
    }
}
