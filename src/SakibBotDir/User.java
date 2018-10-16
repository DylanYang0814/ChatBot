package SakibBotDir;

public class User  {

    private Person[] userAssociations;
    private String userName = "";



    public  User(Person[] userAssociations)
    {
        this.userAssociations = userAssociations;

    }

    public  void setUserAssociations(Person newPerson)
    {
        int count = 0;
        for (int i=0; !(userAssociations[i].equals(newPerson));i++)
        {
            count++;
        }
        userAssociations[count] = newPerson;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
