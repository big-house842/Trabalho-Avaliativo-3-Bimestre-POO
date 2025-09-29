package br.com.pooactivitie.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.service.TodoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController

@RequestMapping("/api/tarefas")

public class TodoController {
    
    private TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @PostMapping
    List<Todo> create(Todo todo){
        return todoService.create(todo);
    }

    @GetMapping
    List<Todo> list(){
        return todoService.list();
    }

    @GetMapping("{id}")
    List<Todo> listById(@PathVariable("id") Long id){
        return todoService.listById(id);
    }

    @GetMapping("importancia")
    List<Todo> listByImportancia(@PathVariable("importancia") boolean importancia){
        return todoService.listByImportancia(importancia);
    }

    @GetMapping("status")
    List<Todo> listByStatus(@PathVariable("status") String status){
        return todoService.listByStatus(status);
    }

    @PatchMapping
    List<Todo> parcialUpdate(@RequestBody Long id){
        return todoService.parcialUpdate(id);
    }//???????????????????????????????

    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") Long id){
        return todoService.delete(id);
    }
}
