package com.nmp.account.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "account")
public class AccountContactInfoDto {

    private String message;
    private Map<String,String > contactDetails;
    private List<String> onCallSupport;

}
