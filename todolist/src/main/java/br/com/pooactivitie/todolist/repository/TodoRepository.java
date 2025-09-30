package br.com.pooactivitie.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.entity.Status;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByStatus(Status status);
    List<Todo> findByImportancia(boolean importancia);
}