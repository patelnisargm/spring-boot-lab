package com.nmp.loan.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(value = "loan")
public class LoanContactInfoDto {

    private String message;
    private Map<String,String > contactDetails;
    private List<String> onCallSupport;

}
