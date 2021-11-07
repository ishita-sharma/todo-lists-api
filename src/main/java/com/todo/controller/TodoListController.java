package com.todo.controller;

import com.todo.model.ItemRequest;
import com.todo.model.ItemResponse;
import com.todo.model.TodoListRequest;
import com.todo.model.TodoListResponse;
import com.todo.services.TodoListService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/todolists")
    public TodoListResponse createList(@RequestBody(required = false) TodoListRequest todoListRequest) {
        final String listName = todoListRequest != null ? todoListRequest.getName() : null;
        return todoListService.createTodoList(listName);
    }

    @GetMapping("/todolists/{id}")
    public TodoListResponse getList(@PathVariable(name = "id") String idOfList) {
        return todoListService.getTodoList(UUID.fromString(idOfList));
    }

    @GetMapping("/todolists")
    public List<TodoListResponse> getAllLists() {
        return todoListService.getAllLists();
    }

    @PutMapping("/todolists/{id}/items")
    public List<ItemResponse> createItems(@RequestBody List<ItemRequest> itemRequests, @PathVariable(name = "id") String todoListId) {
        return todoListService.createItems(itemRequests, UUID.fromString(todoListId));
    }

    @DeleteMapping("/todolists/{id}")
    public void deleteList(@PathVariable(name = "id") String idOfList) {
         todoListService.deleteList(UUID.fromString(idOfList));
    }
}
