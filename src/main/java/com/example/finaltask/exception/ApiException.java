package com.example.finaltask.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {

    private HttpStatus status;

    private String message;

    private LocalDateTime timestamp;

    private String path;

    private Map<String, String> validationErrors;

    public ApiException(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ApiException(HttpStatus status, String path) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
}