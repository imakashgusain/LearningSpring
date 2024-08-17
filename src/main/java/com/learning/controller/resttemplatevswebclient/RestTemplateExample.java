package com.learning.controller.resttemplatevswebclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class RestTemplateExample {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    String url = "https://catfact.ninja/fact";

    @GetMapping("/test-rest")
    public ResponseEntity<Object> testRestTemplate(){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        for(int i=0; i<10; i++){
            restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Object>() {
            });
        }
        return  restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Object>() {
        });

    }

        @GetMapping("/test-web-client")
    public Mono<ResponseEntity<Object>> testWebClient() {
        for(int i=0; i<10; i++){
            webClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(Object.class);
        }
        return webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(Object.class);
    }


}
