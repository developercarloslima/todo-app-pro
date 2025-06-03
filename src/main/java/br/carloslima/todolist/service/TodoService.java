package br.carloslima.todolist.service;

import br.carloslima.todolist.entity.Todo;
import br.carloslima.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    public List<Todo> create( Todo todo) {
        todoRepository.save(todo);
        return list();

    }
    public List<Todo> list() {
        Sort sort = Sort.by("priority").descending().and(
                Sort.by("startTodo"));
        return todoRepository.findAll(sort);
    }
    public List<Todo> update(Todo todo) {
        todoRepository.save(todo);
        return list();
    }
    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }
}
