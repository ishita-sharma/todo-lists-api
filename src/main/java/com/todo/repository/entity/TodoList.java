package com.todo.repository.entity;

import com.todo.model.TodoListResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class TodoList {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy="todoList", cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();

    private LocalDateTime createdAt;

    public TodoListResponse toTodoListDto() {
        return new TodoListResponse(
                id,
                name,
                items.stream()
                        .map(Item::toItemDto)
                        .collect(Collectors.toList()),
                createdAt
        );
    }
}
