package com.todo.controller;

import com.todo.model.TodoListResponse;
import com.todo.services.TodoListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TodoListService todoListService;

    @Test
    void testCreateList() {
        when(todoListService.createTodoList(null))
                .thenReturn(new TodoListResponse(UUID.randomUUID(), null, null, LocalDateTime.now()));

        TodoListResponse response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/todolists", null, TodoListResponse.class);

        assertThat(response).isNotNull();
    }

    @Test
    void testGetList() {
        TodoListResponse todoListResponse = new TodoListResponse(UUID.randomUUID(), null, null, LocalDateTime.now());
        when(todoListService.getTodoList(any(UUID.class)))
                .thenReturn(todoListResponse);

        TodoListResponse response = this.restTemplate.getForObject(
                "http://localhost:" + port + "/todolists/" + todoListResponse.getId().toString(), TodoListResponse.class);

        assertThat(response.getId()).isEqualTo(todoListResponse.getId());
    }
}