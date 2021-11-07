package com.todo.repository;

import com.todo.repository.entity.TodoList;
import com.todo.repository.entity.TodoListIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TodoListRepository extends JpaRepository<TodoList, UUID> {
    public static final String FIND_ALL_TODO_LISTS = "SELECT id, name FROM TODO_LIST";

    @Query(value = FIND_ALL_TODO_LISTS, nativeQuery = true)
    List<Object[]> findAllTodoLists();
}
