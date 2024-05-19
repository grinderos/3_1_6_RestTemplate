package kataacademy.resttemplate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateController {
    public String sessionId;
    public StringBuilder key = new StringBuilder();

    private final String taskURL = "http://94.198.50.185:7081/api/users";

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public HttpHeaders getFirstSessionId(HttpHeaders headers) {
        ResponseEntity<String> response = restTemplate.getForEntity(taskURL, String.class);
        sessionId = response.getHeaders().getFirst("Set-Cookie");
        headers.set("Cookie", sessionId);
        return headers;
    }

    public void getFirstPhrase(HttpEntity<Object> entity) {
        String response = restTemplate
                .postForObject(taskURL, entity, String.class);
        key.append(response);
    }

    public void getSecondPhrase(HttpEntity<Object> entity){
        ResponseEntity<String> response = restTemplate
                .exchange(taskURL, HttpMethod.PUT, entity, String.class);
        key.append(response.getBody());
    }

    public void getThirdPhrase(HttpEntity<Object> entity){
        ResponseEntity<String> response = restTemplate
                .exchange(taskURL+"/3", HttpMethod.DELETE, entity, String.class);
        key.append(response.getBody());
    }
}