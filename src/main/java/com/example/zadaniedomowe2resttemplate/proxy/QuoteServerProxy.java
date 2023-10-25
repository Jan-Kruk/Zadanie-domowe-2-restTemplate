package com.example.zadaniedomowe2resttemplate.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class QuoteServerProxy {
    private final RestTemplate restTemplate;

    @Value("${quote.server.service.url}")
    String url;

    @Value("${quote.server.service.port}")
    int port;

    public QuoteServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeGetRequest(){
        URI uri = getUriComponentsBuilder().path("/api").build().toUri();
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        return response.getBody();
    }

    private UriComponentsBuilder getUriComponentsBuilder(){
        return UriComponentsBuilder.newInstance()
                .scheme("http").host(url).port(port);
    }

}
