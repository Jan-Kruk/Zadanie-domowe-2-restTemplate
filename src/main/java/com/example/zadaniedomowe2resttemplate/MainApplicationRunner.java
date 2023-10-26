package com.example.zadaniedomowe2resttemplate;

import com.example.zadaniedomowe2resttemplate.service.QuoteService;
import org.springframework.stereotype.Component;

@Component
public class MainApplicationRunner {
private final QuoteService quoteService;


    public MainApplicationRunner(QuoteService quoteService) {
        this.quoteService = quoteService;
    }
    public void start(){
        quoteService.fetchAllQuotes();
        quoteService.fetchQuoteByPathVariable(2);
        quoteService.fetchRandomQuote();
        quoteService.fetchQuoteByQueryParam(3);
        quoteService.fetchAllQuotesWithHeaders();
        quoteService.postQuote();
        quoteService.deleteQuote(12);
    }
}
