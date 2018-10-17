package SakibBotDir;

public class Sibling extends Person {

     String firstName;
     String lastName;
     String relationshipType = "sibling";
     String gender;

    public Sibling(String firstName, String lastName)
    {
        super(firstName, lastName,"sibling");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Sibling(String firstName, String lastName, String gender)
    {
        super(firstName, lastName,"sibling");
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }



}
