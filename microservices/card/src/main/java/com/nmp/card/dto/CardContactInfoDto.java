package com.nmp.card.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "card")
public class CardContactInfoDto {

    private String message;
    private Map<String,String > contactDetails;
    private List<String> onCallSupport;

}
