package com.nmp.account.service.client;

import com.nmp.account.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanFallback implements LoanFeignClient {

    @Override
    public ResponseEntity<LoanDto> fetchLoanDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
