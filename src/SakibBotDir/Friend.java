package SakibBotDir;

public class Friend extends Person {


    private String firstName;
    private String lastName;
    private String relationshipType = "crush";
    private String gender;



    public Friend(String firstName, String lastName)
    {
        super(firstName,lastName,"friend");
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
