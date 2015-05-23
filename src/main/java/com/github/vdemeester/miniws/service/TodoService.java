package com.github.vdemeester.miniws.service;

import com.github.vdemeester.miniws.model.Todo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A dumb simple fake implementation of a TodoService :D
 */
@Service
public class TodoService {

    private static final Todo TODO1 = new Todo(1, "todo1", true, null);
    private static final Todo TODO2 = new Todo(2, "todo2", false, LocalDateTime.now());
    private static final Todo TODO3 = new Todo(3, "todo3", false, LocalDateTime.of(2015, 8, 1, 0, 0));

    public List<Todo> findAll() {
        return Lists.newArrayList(TODO1, TODO2, TODO3);
    }

    public Todo findOne(Integer id) {
        switch (id) {
            case 1:
                return TODO1;
            case 2:
                return TODO2;
            case 3:
                return TODO3;
            default:
                throw new IllegalArgumentException("No Todo for the given id : " + id);
        }
    }
}
