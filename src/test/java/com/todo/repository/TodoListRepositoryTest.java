package com.todo.repository;

import com.todo.repository.entity.TodoList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TodoListRepositoryTest {

    @Autowired
    private TodoListRepository todoListRepository;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Test
    void testCreateListInDB() {
        TodoList todoList = new TodoList();
        todoList.setId(UUID.randomUUID());
        todoList.setName("Test List");
        todoList.setCreatedAt(LocalDateTime.parse("2011-12-03T10:15:30" , dateTimeFormatter));

        todoListRepository.save(todoList);

        TodoList todoListSavedInRepository = todoListRepository.findById(todoList.getId()).orElse(null);

        assertThat(todoListSavedInRepository).isNotNull();
        assertThat(todoListSavedInRepository.getId()).isEqualTo(todoList.getId());
    }
}