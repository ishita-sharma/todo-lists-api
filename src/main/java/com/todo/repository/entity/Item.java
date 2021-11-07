package com.todo.repository.entity;

import com.todo.model.ItemResponse;
import com.todo.model.ItemStatus;
import com.todo.model.TodoListResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Item {
    @Id
    private UUID id;
    private String itemValue;
    private ItemStatus itemStatus;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "todolist_id", nullable = false)
    private TodoList todoList;

    public ItemResponse toItemDto() {
        return new ItemResponse(id, itemValue, itemStatus, createdAt);
    }
}
