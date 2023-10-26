package com.example.zadaniedomowe2resttemplate.service;

import com.example.zadaniedomowe2resttemplate.proxy.QuoteResponse;
import com.example.zadaniedomowe2resttemplate.proxy.ValueResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class QuoteMapper {

    private final ObjectMapper objectMapper;

    public QuoteMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    List<QuoteResponse> mapJsonToQuoteListResponse(String jsonQuote){
        try {
            return Arrays.asList(objectMapper.readValue(jsonQuote,QuoteResponse[].class));
        } catch (JsonProcessingException e) {
            log.error("QuoteListResponseMapper could not map json");
            return Collections.emptyList();
        }
    }
    QuoteResponse mapJsontoQuoteResponse(String jsonQuote){
        try {
            return objectMapper.readValue(jsonQuote,QuoteResponse.class);
        } catch (JsonProcessingException e) {
            log.error("QuoteResponseMapper could not map json");
            return new QuoteResponse("",new ValueResponse(0,""));
        }
    }
}
