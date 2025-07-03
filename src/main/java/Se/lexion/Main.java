package Se.lexion;

import java.rmi.AlreadyBoundException;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Person Alex = new Person(1,"Alex", "Nbplus", "alex@gmail.com");

        System.out.println(Alex.getSummary());

        TodoItem todo = new TodoItem(1,"title 1","complete Java assignment", LocalDate.of(2027,7,3));

        todo.setCreator(Alex);

        System.out.println(todo.getSummary());

        TodoItemTask todoItemTask = new TodoItemTask();

        todoItemTask.setAssignee(Alex);
        todoItemTask.getTodoItem(todo);

        System.out.println(todoItemTask.getSummary());
    }
}