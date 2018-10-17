package SakibBotDir;

public class Teacher extends Person {

    private String firstName;
    private String lastName;
    private String relationshipType = "teacher";
    private String gender;

    public Teacher(String firstName, String lastName)
    {
        super(firstName,lastName,"teacher");
    }

}
