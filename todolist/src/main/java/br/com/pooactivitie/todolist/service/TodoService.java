package br.com.pooactivitie.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.entity.Status;
import br.com.pooactivitie.todolist.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        // Garantir que o status seja A_FAZER, se não for fornecido
        if (todo.getStatus() == null) {
            todo.setStatus(Status.A_FAZER);
        }
        
        // Se dataCriacao não foi definida, definir agora
        if (todo.getDataCriacao() == null) {
            todo.setDataCriacao(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        return todoRepository.findAll();
    }

    public Todo listById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
    }

    public List<Todo> listByStatus(Status status) {
        return todoRepository.findByStatus(status);
    }

    public List<Todo> listByImportancia(boolean importancia) {
        return todoRepository.findByImportancia(importancia);
    }

    public List<Todo> parcialUpdate(Long id, Todo todoAtualizado) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        
        if (todoOptional.isPresent()) {
            Todo todoExistente = todoOptional.get();
            
            // Atualiza apenas descrição e status (se fornecidos)
            if (todoAtualizado.getDescricao() != null) {
                todoExistente.setDescricao(todoAtualizado.getDescricao());
            }
            if (todoAtualizado.getStatus() != null) {
                todoExistente.setStatus(todoAtualizado.getStatus());
            }
            
            todoRepository.save(todoExistente);
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }

        return list();
    }

    public List<Todo> delete(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
        return list();
    }
}