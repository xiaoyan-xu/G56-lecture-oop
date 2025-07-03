package Se.lexion;

public class TodoItemTask {
    private int id ;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;


    public void setId(int id){
        this.id = id;
    }

    public  void setTodoItem(TodoItem todoItem){
        this.todoItem = todoItem;
    }

    public void setAssigned(boolean assigned){
        this.assigned = assigned;
    }

    public void setAssignee(Person assignee){
        this.assignee = assignee;
    }


    public int getId() {
        return id;
    }

    public TodoItem getTodoItem(TodoItem todo) {
        return todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }
    public boolean getAssigned(){
        return true;
    }


    public String getSummary(){
        StringBuilder sum = new StringBuilder();
        sum.append("id: ").append(id).append(", toDoItem: ")
                .append(todoItem).append(", assigned: ").append(true)
                .append("assignee").append(assignee.getSummary());
        return sum.toString();
    }
}
