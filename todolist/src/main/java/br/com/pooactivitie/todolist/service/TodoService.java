package br.com.pooactivitie.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        return todoRepository.findAll();
    }

    public List<Todo> listById(long id) {
        return todoRepository.findAllById(List.of(id));
    }

    public List<Todo> listByStatus(String status) {
        return todoRepository.findAll().stream().filter(todo -> todo.getStatus().equals(status)).toList();
    }

    public List<Todo> listByImportancia(boolean importancia) {
        return todoRepository.findAll().stream().filter(todo -> todo.isImportancia() == importancia).toList();
    }

    public List<Todo> parcialUpdate(long id) {

        var todo = todoRepository.findById(id);
        
        if (todo.isPresent()) {
            var todoAtualizado = todo.get();
            if (todoAtualizado.getStatus().equals("FAZENDO")) {
                todoAtualizado.setStatus("FEITO");
            } else {
                todoAtualizado.setStatus("FAZENDO");
            }
            todoRepository.save(todoAtualizado);
        }

        return list();
    }

    public List<Todo> delete(long id) {
    
        todoRepository.deleteById(id);
        return list();
    }

}
