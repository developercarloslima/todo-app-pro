package br.carloslima.todolist.repository;

import br.carloslima.todolist.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
