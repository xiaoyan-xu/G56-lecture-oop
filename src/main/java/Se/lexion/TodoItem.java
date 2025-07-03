package Se.lexion;

import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem (int id, String title, String taskDescription, LocalDate deadLine){
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
    }

    public void setId(int id){
        this.id = id ;
    }

    public void setTitle(String title){
        if(title== null || title.trim().isEmpty()){
            throw new IllegalArgumentException("title is not allowed to be null or empty.");
        }
        this.title = title;
    }

    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }

    public void setDeadLine(LocalDate deadLine){
        if (deadLine == null){
            throw new IllegalArgumentException("deadline is not allowed to be null");
        } else if (deadLine.isAfter(LocalDate.now())) {
            System.out.println(" over due");
        }
        this.deadLine = deadLine;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public void setCreator( Person creator){
        this.creator = creator;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

    public LocalDate getDeadLine(){
        return deadLine;
    }

    public String getSummary(){
        StringBuilder sum = new StringBuilder();

        sum.append("id " ).append(id).append(", title: ").append(title)
                .append(", taskDescription:").append(taskDescription)
                .append(", deadline: ").append(deadLine).append(", creator: ")
                .append(creator.getSummary());

        return sum.toString();
    }


}
