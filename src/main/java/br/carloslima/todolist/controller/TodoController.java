package br.carloslima.todolist.controller;

import br.carloslima.todolist.dto.TodoRequest;
import br.carloslima.todolist.dto.TodoResponse;
import br.carloslima.todolist.entity.Todo;
import br.carloslima.todolist.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoRequest request) {
        try {
            LocalTime start = LocalTime.parse(request.getStartTodo());
            LocalTime finish = LocalTime.parse(request.getFinishTodo());

            if (finish.isBefore(start)) {
                return ResponseEntity.badRequest().body("Horário de término deve ser após o de início.");
            }

            Todo todo = new Todo(
                    request.getName(),
                    request.getDescription(),
                    request.isTaskDone(),
                    request.getPriority(),
                    start,
                    finish
            );

            List<Todo> createdList = todoService.create(todo);
            Todo created = createdList.get(createdList.size() - 1);
            return ResponseEntity.status(201).body(new TodoResponse(created));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de hora inválido. Use HH:mm.");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TodoRequest request) {
        try {
            if (request.getId() == null) {
                return ResponseEntity.badRequest().body("ID obrigatório para atualizar.");
            }

            LocalTime start = LocalTime.parse(request.getStartTodo());
            LocalTime finish = LocalTime.parse(request.getFinishTodo());

            if (finish.isBefore(start)) {
                return ResponseEntity.badRequest().body("Horário de término deve ser após o de início.");
            }

            Todo todo = new Todo(
                    request.getName(),
                    request.getDescription(),
                    request.isTaskDone(),
                    request.getPriority(),
                    start,
                    finish
            );
            todo.setId(request.getId());

            List<Todo> updatedList = todoService.update(todo);
            Todo updated = updatedList.stream()
                    .filter(t -> t.getId().equals(todo.getId()))
                    .findFirst()
                    .orElse(todo);

            return ResponseEntity.ok(new TodoResponse(updated));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar. Verifique os dados.");
        }
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    @DeleteMapping("{id}")
    public List<Todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}
