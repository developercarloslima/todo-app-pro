package br.carloslima.todolist.dto;

public class TodoRequest {
    private Long id;
    private String name;
    private String description;
    private boolean taskDone;
    private int priority;
    private String startTodo;
    private String finishTodo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isTaskDone() { return taskDone; }
    public void setTaskDone(boolean taskDone) { this.taskDone = taskDone; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public String getStartTodo() { return startTodo; }
    public void setStartTodo(String startTodo) { this.startTodo = startTodo; }

    public String getFinishTodo() { return finishTodo; }
    public void setFinishTodo(String finishTodo) { this.finishTodo = finishTodo; }
}
