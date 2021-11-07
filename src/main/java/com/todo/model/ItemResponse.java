package com.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {
    private UUID id;
    private String itemValue;
    private ItemStatus itemStatus;
    private LocalDateTime createdAt;
}
