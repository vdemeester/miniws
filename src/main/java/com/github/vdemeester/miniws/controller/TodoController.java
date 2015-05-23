package com.github.vdemeester.miniws.controller;

import com.github.vdemeester.miniws.model.Todo;
import com.github.vdemeester.miniws.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Scope("request")
@RequestMapping("/todos")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Todo> getTodoList() {
        LOGGER.debug("Get list of all todos");
        return todoService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Todo getTodo(@PathVariable Integer id) {
        LOGGER.debug("Get the todo with id : {}", id);
        try {
            return todoService.findOne(id);
        } catch (IllegalArgumentException e) {
            throw  new ResourceNotFoundException();
        }
    }
}
