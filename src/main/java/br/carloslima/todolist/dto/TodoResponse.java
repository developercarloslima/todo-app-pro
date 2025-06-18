package br.carloslima.todolist.dto;

import br.carloslima.todolist.entity.Todo;
import java.time.format.DateTimeFormatter;

public class TodoResponse {
    private Long id;
    private String name;
    private String description;
    private boolean taskDone;
    private int priority;
    private String startTodo;
    private String finishTodo;

    public void setName(String name) {
        this.name = name;
    }

    public void setFinishTodo(String finishTodo) {
        this.finishTodo = finishTodo;
    }

    public void setStartTodo(String startTodo) {
        this.startTodo = startTodo;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TodoResponse(Todo todo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.taskDone = todo.isTaskDone();
        this.priority = todo.getPriority();
        this.startTodo = todo.getStartTodo().format(formatter);
        this.finishTodo = todo.getFinishTodo().format(formatter);
    }

    public TodoResponse(){}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isTaskDone() { return taskDone; }
    public int getPriority() { return priority; }
    public String getStartTodo() { return startTodo; }
    public String getFinishTodo() { return finishTodo; }
}