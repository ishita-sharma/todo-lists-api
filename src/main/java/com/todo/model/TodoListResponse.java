package com.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class TodoListResponse {
    private UUID id;
    private String name;
    private List<ItemResponse> items;
    private LocalDateTime createdAt;

    public static TodoListResponse fromIdAndName(Object[] idAndName) {
        return new TodoListResponse(UUID.nameUUIDFromBytes((byte[]) idAndName[0]),
                String.valueOf(idAndName[1]),
                null,
                null);
    }
}
