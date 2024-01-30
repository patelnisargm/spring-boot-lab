package com.nmp.account.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String mobileNumber) {
        super("Customer already registered with given mobileNumber:" + mobileNumber);
    }
}
