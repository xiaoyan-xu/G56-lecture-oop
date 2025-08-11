package Se.lexion;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem(int id, String title, String taskDescription, LocalDate deadLine, Person creator) {
        this.id = id;
        setTitle(title);
        this.taskDescription = taskDescription;
        setDeadLine(deadLine);
        this.creator = creator;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadLine);
    }

    /* public String getSummary(){
        StringBuilder sum = new StringBuilder();

        sum.append("id: " ).append(id).append("title: ").append(title)
                .append("taskDescription: ").append(taskDescription)
                .append("deadline: ").append(deadLine).append(", creator: ")
                .append(creator.getSummary());

        return sum.toString();
    }*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TodoItem todoItem = (TodoItem) obj;
        return id == todoItem.id &&
                done == todoItem.done &&
                Objects.equals(title, todoItem.title) &&
                Objects.equals(taskDescription, todoItem.taskDescription) &&
                Objects.equals(deadLine, todoItem.deadLine);
        // Note: creator (Person object) is excluded from equals
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, deadLine, done);
        // Note: creator (Person object) is excluded from hashCode
    }

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, title='%s', taskDescription='%s', deadLine=%s, done=%s}",
                id, title, taskDescription, deadLine, done);
        // Note: creator (Person object) is excluded from toString
    }


}
