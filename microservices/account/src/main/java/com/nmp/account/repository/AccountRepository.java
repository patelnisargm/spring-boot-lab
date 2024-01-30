package com.nmp.account.repository;

import com.nmp.account.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerCustomerId(Long customerId);

    Optional<Account> findByCustomerMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByCustomerCustomerId(Long customerId);
}
