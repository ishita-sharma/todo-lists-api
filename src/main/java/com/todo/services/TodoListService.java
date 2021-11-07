package com.todo.services;

import com.todo.model.ItemRequest;
import com.todo.model.ItemResponse;
import com.todo.model.TodoListResponse;

import java.util.List;
import java.util.UUID;

public interface TodoListService {

    TodoListResponse createTodoList(String nameOfList);

    TodoListResponse getTodoList(UUID idOfList);

    List<ItemResponse> createItems(List<ItemRequest> itemRequests, UUID todoListId);

    List<TodoListResponse> getAllLists();

    void deleteList(UUID idOfList);
}
