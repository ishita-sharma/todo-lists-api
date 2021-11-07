package com.todo.services.impl;

import com.todo.model.ItemRequest;
import com.todo.model.ItemResponse;
import com.todo.model.TodoListResponse;
import com.todo.repository.ItemRepository;
import com.todo.repository.TodoListRepository;
import com.todo.repository.entity.Item;
import com.todo.repository.entity.TodoList;
import com.todo.services.TodoListService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final ItemRepository itemRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository, ItemRepository itemRepository) {
        this.todoListRepository = todoListRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public TodoListResponse createTodoList(String nameOfList) {
        TodoList todoList = new TodoList();
        todoList.setId(UUID.randomUUID());
        todoList.setName(nameOfList);
        todoList.setCreatedAt(LocalDateTime.now());

        todoListRepository.save(todoList);

        return todoList.toTodoListDto();
    }

    @Override
    public TodoListResponse getTodoList(UUID idOfList) {
        return todoListRepository.getById(idOfList).toTodoListDto();
    }

    @Override
    public List<ItemResponse> createItems(List<ItemRequest> itemRequests, UUID todoListId) {
        return itemRequests.stream()
                .map(itemRequest -> createItem(itemRequest, todoListId))
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoListResponse> getAllLists() {
        List<Object[]> todoLists = todoListRepository.findAllTodoLists();

        return todoLists.stream().map(TodoListResponse::fromIdAndName).collect(Collectors.toList());
    }

    @Override
    public void deleteList(UUID idOfList) {
         todoListRepository.deleteById(idOfList);
    }

    private ItemResponse createItem(ItemRequest itemRequest, UUID todoListId) {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setItemValue(itemRequest.getItemValue());
        item.setItemStatus(itemRequest.getItemStatus());
        item.setCreatedAt(LocalDateTime.now());

        TodoList todoListTobeUpdated = todoListRepository.getById(todoListId);
        item.setTodoList(todoListTobeUpdated);
        todoListTobeUpdated.getItems().add(item);

        itemRepository.save(item);

        return item.toItemDto();
    }
}
