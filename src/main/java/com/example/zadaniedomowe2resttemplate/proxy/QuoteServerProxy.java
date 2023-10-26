package com.example.zadaniedomowe2resttemplate.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Log4j2
public class QuoteServerProxy {
    private final RestTemplate restTemplate;

    @Value("${quote.server.service.url}")
    String url;

    @Value("${quote.server.service.port}")
    int port;

    public QuoteServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeGetAllRequest(){
        URI uri = getUriComponentsBuilder().path("/api").build().toUri();
        return  getRequest(uri);
    }
    public String makeGetByPathVariableRequest(int id){
     URI uri = getUriComponentsBuilder().path("/api").path("/"+id).build().toUri();
        return  getRequest(uri);
    }

    public String makeGetRandomRequest(){
        URI uri = getUriComponentsBuilder().path("/api").path("/random").build().toUri();
        return  getRequest(uri);
    }

    public String makeGetByQueryParamRequest(@RequestParam int id) {
        URI uri = getUriComponentsBuilder().path("/apiWithRequestParam").queryParam("id",id).build().toUri();
        return  getRequest(uri);
    }
    public String makeGetAllWithHeadersRequest(){
        URI uri = getUriComponentsBuilder().path("/apiWithHeader").build().toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId","hello World");
        HttpEntity<Object> quoteResponseHttpEntity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    quoteResponseHttpEntity,
                    String.class
            );
            return response.getBody();
        }catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return null;
    }

    public String makePostRequest(){
        URI uri = getUriComponentsBuilder().path("/api").path("/quote").build().toUri();
        ValueResponse requestBody =
                new ValueResponse(420,"never give up on your dreams");
        HttpEntity<ValueResponse> httpEntity = new HttpEntity<>(requestBody);
        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return null;
    }
    public String makeDeleteRequestByPathVariable(int id){
        URI uri = getUriComponentsBuilder().path("/api").path("/quote").path("/"+id).build().toUri();
        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            return response.getBody();
        }catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return null;

    }

    private String getRequest(URI uri){
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return response.getBody();
        }catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return null;
    }

    private UriComponentsBuilder getUriComponentsBuilder(){
        return UriComponentsBuilder.newInstance()
                .scheme("http").host(url).port(port);
    }

}
