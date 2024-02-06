package com.nmp.account.service.client;

import com.nmp.account.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardFallback implements CardFeignClient {
    @Override
    public ResponseEntity<CardDto> fetchCardDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
