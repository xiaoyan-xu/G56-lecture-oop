package Se.lexion;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;


    public Person(int id, String firstName,String lastName, String email){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);

    }

    public void setId(int id){
        this.id = id;
    }
    public void setFirstName(String firstName)  {
        if (firstName ==null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("first name cannot be null or empty");
        }
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()){
            throw new IllegalArgumentException("last name cannot be null or empty");
        }
        this.lastName = lastName;
    }
    public  void setEmail(String email){
        if (email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        this.email = email;
    }

    public int getId (){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }


    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }
    public String getSummary(){
        StringBuilder sum = new StringBuilder();
        sum.append("id: " ).append(id).append(", name: " )
                .append(firstName).append(lastName)
                .append(", email: ").append(email);

        return sum.toString();

    }



}
