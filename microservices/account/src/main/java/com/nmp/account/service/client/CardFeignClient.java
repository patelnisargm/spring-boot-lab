package com.nmp.account.service.client;

import com.nmp.account.dto.CardDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card")
public interface CardFeignClient {

    @GetMapping(value = "/fetch", consumes = "application/json")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);

}
