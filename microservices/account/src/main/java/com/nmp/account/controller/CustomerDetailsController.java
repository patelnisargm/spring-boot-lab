package com.nmp.account.controller;

import com.nmp.account.dto.CustomerDetailsDto;
import com.nmp.account.dto.CustomerDto;
import com.nmp.account.service.ICustomerDetailsService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
@RequestMapping("/customer")
public class CustomerDetailsController {

    ICustomerDetailsService customerDetailsService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        return new ResponseEntity<>(customerDetailsService.fetchCustomerDetails(mobileNumber), HttpStatus.OK);
    }
}
