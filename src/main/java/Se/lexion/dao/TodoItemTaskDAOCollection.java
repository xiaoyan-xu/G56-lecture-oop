package Se.lexion.dao;

import Se.lexion.TodoItemTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{
    private List<TodoItemTask> todoItemTasks;

    public TodoItemTaskDAOCollection() {
        this.todoItemTasks = new ArrayList<>();
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) {
            throw new IllegalArgumentException("TodoItemTask cannot be null");
        }
        // Check if id already exists
        if (findById(todoItemTask.getId()) != null) {
            throw new IllegalArgumentException("TodoItemTask with this ID already exists");
        }
        todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        return todoItemTasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return new ArrayList<>(todoItemTasks);
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        return todoItemTasks.stream()
                .filter(task -> task.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return todoItemTasks.stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        todoItemTasks.removeIf(task -> task.getId() == id);
    }
}
