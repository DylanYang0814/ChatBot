package SakibBotDir;

public class Crush extends Person {

    private String firstName;
    private String lastName;
    private String relationshipType = "crush";
    private String gender;


    public  Crush (String firstName, String lastName)
    {
        super(firstName, lastName, "crush");
        this.firstName = firstName;
        this.lastName = lastName;

    }


}
