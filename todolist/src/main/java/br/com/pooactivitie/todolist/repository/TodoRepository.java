package br.com.pooactivitie.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pooactivitie.todolist.entity.Todo;
import br.com.pooactivitie.todolist.entity.Status;

//Biblioteca que faz a comunicação com o banco de dados
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByStatus(Status status); // Método para buscar tarefas por status
    List<Todo> findByImportancia(boolean importancia); // Método para buscar tarefas por importância
}