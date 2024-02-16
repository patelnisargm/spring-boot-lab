package com.nmp.account.service.impl;

import com.nmp.account.constants.AccountConstants;
import com.nmp.account.dto.AccountDto;
import com.nmp.account.dto.AccountMessageDto;
import com.nmp.account.dto.CustomerDto;
import com.nmp.account.entity.Account;
import com.nmp.account.entity.Customer;
import com.nmp.account.exception.CustomerAlreadyExistsException;
import com.nmp.account.exception.ResourceNotFoundException;
import com.nmp.account.mapper.AccountMapper;
import com.nmp.account.mapper.CustomerMapper;
import com.nmp.account.repository.AccountRepository;
import com.nmp.account.repository.CustomerRepository;
import com.nmp.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private StreamBridge streamBridge;
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent())
            throw new CustomerAlreadyExistsException(customerDto.getMobileNumber());
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(createAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication(Account savedAccount, Customer savedCustomer) {
        var accountMsgDto = new AccountMessageDto(savedAccount.getAccountNumber(),
                savedCustomer.getName(), savedCustomer.getEmail(), savedCustomer.getMobileNumber());
        logger.info("Sending Communication request for the details: {}", accountMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", accountMsgDto);
        logger.info("Is the Communication request successfully triggered ? : {}", result);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = accountRepository.findByCustomerCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.maptoCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            //Long customerId = account.getCustomer();
            Customer customer = customerRepository.findByAccountAccountNumber(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", accountDto.getAccountNumber().toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        //accountRepository.deleteByCustomerCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if (accountNumber != null) {
            Account account =  accountRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
            );
            account.setNotified(true);
            accountRepository.save(account);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param customer
     * @return
     */
    private Account createAccount(Customer customer) {
        Account newAccount = new Account();
        //newAccount.setCustomerId(customer.getCustomerId());
        Long accountNumber = 10000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCustomer(customer);
        accountRepository.save(newAccount);
        return newAccount;
    }

}
