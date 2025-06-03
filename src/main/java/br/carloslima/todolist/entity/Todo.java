package br.carloslima.todolist.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean taskDone;

    private int priority;

    private LocalTime startTodo;

    private LocalTime finishTodo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalTime getStartTodo() {
        return startTodo;
    }

    public void setStartTodo(LocalTime startTodo) {
        this.startTodo = startTodo;
    }

    public LocalTime getFinishTodo() {
        return finishTodo;
    }

    public void setFinishTodo(LocalTime finishTodo) {
        this.finishTodo = finishTodo;
    }
}
