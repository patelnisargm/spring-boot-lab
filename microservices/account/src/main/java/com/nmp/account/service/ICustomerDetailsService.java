package com.nmp.account.service;

import com.nmp.account.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {

    CustomerDetailsDto fetchCustomerDetails(String correlationID, String mobileNumber);

}
