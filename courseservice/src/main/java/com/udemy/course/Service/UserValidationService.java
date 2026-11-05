package com.udemy.course.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public UserValidationService(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }

    public boolean validateUser(String userId){
        log.info("Calling user validation api for userId: {}",userId);
        try {
            return Boolean.TRUE.equals(userServiceWebClient.get().uri("/api/users/{userId}/exists",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        } catch(WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new RuntimeException("User Not Found: " + userId);
        }

        return false;
    }

}
