package com.nmp.account.controller;

import com.nmp.account.dto.CustomerDetailsDto;
import com.nmp.account.dto.CustomerDto;
import com.nmp.account.service.ICustomerDetailsService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController @AllArgsConstructor
@RequestMapping("")
public class CustomerDetailsController {

    ICustomerDetailsService customerDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsController.class);

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("bank-correlation-id") String correlationID,
                                                                   @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        logger.debug("fetchCustomerDetails method start");
        logger.debug("fetchCustomerDetails method end");
        return new ResponseEntity<>(customerDetailsService.fetchCustomerDetails(correlationID, mobileNumber), HttpStatus.OK);
    }
}
