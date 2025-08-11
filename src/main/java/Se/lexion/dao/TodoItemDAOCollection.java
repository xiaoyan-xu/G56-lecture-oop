package Se.lexion.dao;

import Se.lexion.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemDAOCollection implements TodoItemDAO{
    private List<TodoItem> todoItems;

    public TodoItemDAOCollection() {
        this.todoItems = new ArrayList<>();
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        // Check if id already exists
        if (findById(todoItem.getId()) != null) {
            throw new IllegalArgumentException("TodoItem with this ID already exists");
        }
        todoItems.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        return todoItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<TodoItem> findAll() {
        return new ArrayList<>(todoItems);
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return todoItems.stream()
                .filter(item -> item.isDone() == done)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        if (title == null) {
            return new ArrayList<>();
        }
        return todoItems.stream()
                .filter(item -> item.getTitle() != null && item.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return todoItems.stream()
                .filter(item -> item.getCreator() != null && item.getCreator().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        if (date == null) {
            return new ArrayList<>();
        }
        return todoItems.stream()
                .filter(item -> item.getDeadLine().isBefore(date))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        if (date == null) {
            return new ArrayList<>();
        }
        return todoItems.stream()
                .filter(item -> item.getDeadLine().isAfter(date))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        todoItems.removeIf(item -> item.getId() == id);
    }
}
