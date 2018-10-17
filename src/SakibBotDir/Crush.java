package SakibBotDir;
import edu.stanford.nlp.simple.*;

import java.util.List;

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




    Sentence woo = new Sentence("hi");
    List<String> nerTags =  woo.nerTags();






}
