package com.arabot.store.productsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    @JsonProperty
    private HttpStatus status;

    @JsonProperty
    @Builder.Default
    private final Instant timestamp = Instant.now();

    @JsonProperty
    private String message;

}