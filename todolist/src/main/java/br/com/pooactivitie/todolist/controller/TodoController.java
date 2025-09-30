package br.com.pooactivitie.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pooactivitie.todolist.entity.Status;
import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.service.TodoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tarefas")
public class TodoController {
    
    private TodoService todoService;
    
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @PostMapping
    public List<Todo> create(@RequestBody @Valid Todo todo) {
        // Remove apenas o ID se for enviado (gerado automaticamente)
        // NÃO remove dataCriacao e status - eles serão gerados automaticamente
        todo.setId(null);
        return todoService.create(todo);
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    @GetMapping("/{id}")
    public Todo listById(@PathVariable("id") Long id) {
        return todoService.listById(id);
    }

    @GetMapping("/importancia")
    public List<Todo> listByImportancia(@RequestParam("importancia") boolean importancia) {
        return todoService.listByImportancia(importancia);
    }

    @GetMapping("/status")
    public List<Todo> listByStatus(@RequestParam("status") Status status) {
        return todoService.listByStatus(status);
    }

    @PatchMapping("/{id}")
    public List<Todo> parcialUpdate(@PathVariable("id") Long id, @RequestBody Todo todoAtualizado) {
        return todoService.parcialUpdate(id, todoAtualizado);
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}