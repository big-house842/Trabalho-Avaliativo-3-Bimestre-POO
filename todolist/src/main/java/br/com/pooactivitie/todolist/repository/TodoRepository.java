package br.com.pooactivitie.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pooactivitie.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
 
}
