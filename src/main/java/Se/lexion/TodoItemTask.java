package Se.lexion;

import java.util.Objects;

public class TodoItemTask {
    private int id ;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;


    public TodoItemTask(int id, TodoItem todoItem) {
        this.id = id;
        setTodoItem(todoItem);
        this.assignee = null;
        updateAssigned();
    }

    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {

        updateAssigned();
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        updateAssigned();
    }

    private void updateAssigned() {
        this.assigned = (assignee != null);
    }

   /* public String getSummary(){
        StringBuilder sum = new StringBuilder();
        sum.append("id: ").append(id).append(", toDoItem: ")
                .append(todoItem).append(", assigned: ").append(true)
                .append("assignee").append(assignee.getSummary());
        return sum.toString();
    }*/
   @Override
   public boolean equals(Object obj) {
       if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
       TodoItemTask that = (TodoItemTask) obj;
       return id == that.id &&
               assigned == that.assigned &&
               Objects.equals(todoItem, that.todoItem);
       // Note: assignee (Person object) is excluded from equals
   }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
        // Note: assignee (Person object) is excluded from hashCode
    }

    @Override
    public String toString() {
        return String.format("TodoItemTask{id=%d, assigned=%s, todoItem=%s}",
                id, assigned, todoItem != null ? todoItem.getTitle() : "null");
        // Note: assignee (Person object) is excluded from toString
    }
}
