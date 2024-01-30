package com.nmp.account.repository;

import com.nmp.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);

    Optional<Customer> findByAccountAccountNumber(Long accountNumber);
}
