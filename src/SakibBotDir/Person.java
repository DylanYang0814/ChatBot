package SakibBotDir;

public class Person {

    private String lastName;
    private String firstName;
    private String relationType;

    public  Person(String lastName, String firstName, String relationType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationType = relationType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "Person{}";
    }
}
