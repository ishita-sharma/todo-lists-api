package com.todo.services.impl;

import com.todo.model.ItemRequest;
import com.todo.model.ItemStatus;
import com.todo.model.TodoListResponse;
import com.todo.repository.ItemRepository;
import com.todo.repository.TodoListRepository;
import com.todo.repository.entity.Item;
import com.todo.repository.entity.TodoList;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoListServiceImplTest {

    @Mock
    private TodoListRepository todoListRepository;

    @Mock
    private ItemRepository itemRepository;

    private TodoListServiceImpl classUnderTest;

    @BeforeEach
    void initUseCase() {
        classUnderTest = new TodoListServiceImpl(todoListRepository, itemRepository);
    }

    @Test
    void createTodoList() {
        when(todoListRepository.save(any(TodoList.class))).thenReturn(new TodoList());
        TodoListResponse createdListId = classUnderTest.createTodoList(Strings.EMPTY);

        assertNotNull(createdListId);
    }

    @Test
    void getTodoList() {
        TodoList todoList = new TodoList();
        when(todoListRepository.getById(any(UUID.class))).thenReturn(todoList);
        TodoListResponse getList = classUnderTest.getTodoList(UUID.randomUUID());

        assertNotNull(getList);
    }

    @Test
    void createItems() {
        Optional<TodoList> todoList = Optional.of(new TodoList());

        ItemRequest itemRequest = new ItemRequest("milk", ItemStatus.OPEN);
        ItemRequest itemRequest1 = new ItemRequest("groceries", ItemStatus.OPEN);
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(itemRequest);
        itemRequests.add(itemRequest1);

        when(todoListRepository.getById(any(UUID.class))).thenReturn(todoList.orElse(null));

        classUnderTest.createItems(itemRequests, UUID.randomUUID());

        verify(itemRepository, times(2)).save(any(Item.class));
    }
}