package com.todo.controller;

import com.todo.model.ItemRequest;
import com.todo.model.ItemResponse;
import com.todo.model.ItemStatus;
import com.todo.model.TodoListRequest;
import com.todo.model.TodoListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoListControllerE2E {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateList() {
        TodoListRequest request = new TodoListRequest();
        request.setName("testList");

        TodoListResponse response = testRestTemplate.postForObject(
                "http://localhost:" + port + "/todolists",
                request,
                TodoListResponse.class
        );

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("testList");
    }

    @Test
    void testGetList() {
        TodoListRequest request = new TodoListRequest();
        request.setName("testListFood");

        TodoListResponse createdList = testRestTemplate.postForObject(
                "http://localhost:" + port + "/todolists",
                request,
                TodoListResponse.class
        );

        TodoListResponse response = testRestTemplate.getForObject(
                "http://localhost:" + port + "/todolists/" + createdList.getId(),
                TodoListResponse.class
        );

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(createdList.getId());
        assertThat(response.getName()).isEqualTo("testListFood");
    }

    @Test
    void testCreateItem() {
        ItemRequest request = new ItemRequest("Milk", ItemStatus.OPEN);

        ItemResponse createdItem = testRestTemplate.postForObject(
                "http://localhost:" + port + "/items",
                request,
                ItemResponse.class
        );

        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getItemValue()).isEqualTo("Milk");
    }

}
