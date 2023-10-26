package com.example.zadaniedomowe2resttemplate.service;

import com.example.zadaniedomowe2resttemplate.proxy.QuoteResponse;
import com.example.zadaniedomowe2resttemplate.proxy.QuoteServerProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class QuoteService {

    private final QuoteServerProxy quoteServerClient;
    private final QuoteMapper quoteMapper;

    public QuoteService(QuoteServerProxy quoteServerClient, QuoteMapper quoteMapper) {
        this.quoteServerClient = quoteServerClient;
        this.quoteMapper = quoteMapper;
    }

    public List<QuoteResponse> fetchAllQuotes() {
        String jsonQuote = quoteServerClient.makeGetAllRequest();
        if (jsonQuote == null) {
            log.error("jsonQuote was null");
        }
        List<QuoteResponse> list = quoteMapper.mapJsonToQuoteListResponse(jsonQuote);
        log.info("QuoteService fetched: " + list);
        return list;
    }
    public QuoteResponse fetchQuoteByPathVariable(int id){
        String jsonQuote = quoteServerClient.makeGetByPathVariableRequest(id);
        if(jsonQuote == null){
            log.error("jsonQuote was null");
        }
        QuoteResponse response = quoteMapper.mapJsontoQuoteResponse(jsonQuote);
        log.info("QuoteService fetched: " + response);
        return response;
    }
    public QuoteResponse fetchRandomQuote(){
        String jsonQuote = quoteServerClient.makeGetRandomRequest();
        if(jsonQuote == null){
            log.error("jsonQuote was null");
        }
        QuoteResponse response = quoteMapper.mapJsontoQuoteResponse(jsonQuote);
        log.info("QuoteService fetched: " + response);
        return response;
    }

    public QuoteResponse fetchQuoteByQueryParam(int id){
        String jsonQuote = quoteServerClient.makeGetByQueryParamRequest(id);
        if(jsonQuote == null){
            log.error("jsonQuote was null");
        }
        QuoteResponse response = quoteMapper.mapJsontoQuoteResponse(jsonQuote);
        log.info("QuoteService fetched: " + response);
        return response;
    }
    public List<QuoteResponse> fetchAllQuotesWithHeaders(){
        String jsonQuote = quoteServerClient.makeGetAllWithHeadersRequest();
        if (jsonQuote == null) {
            log.error("jsonQuote was null");
        }
        List<QuoteResponse> list = quoteMapper.mapJsonToQuoteListResponse(jsonQuote);
        log.info("QuoteService fetched: " + list);
        return list;
    }

    public void postQuote(){
        String jsonQuote = quoteServerClient.makePostRequest();
        if (jsonQuote == null) {
            log.error("jsonQuote was null");
        }
        log.info("QuoteService posted: " + jsonQuote);
    }
    public void deleteQuote(int id){
        String jsonQuote = quoteServerClient.makeDeleteRequestByPathVariable(id);
        if (jsonQuote == null){
            log.error("jsonQuote was null");
        }
        log.info("QuoteService deleted: " + jsonQuote);
    }

}
