package Se.lexion;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;



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
    public AppUser getCredentials() {
        return credentials;
    }

    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email);
        // Note: credentials is excluded from equals
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
        // Note: credentials is excluded from hashCode
    }

    @Override
    public String toString() {
        return String.format("{id: %d, name: %s %s, email: %s}", id, firstName, lastName, email);
        // Note: credentials is excluded from toString
    }


    /*public String getSummary(){
        StringBuilder sum = new StringBuilder();
        sum.append("id: " ).append(id).append(", name: " )
                .append(firstName).append(" ").append(lastName)
                .append(", email: ").append(email);

        return sum.toString();

    }*/



}
