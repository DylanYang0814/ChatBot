// Nazmus Sakib Period 2 & 3
import java.util.Random;
import java.util.Scanner;



/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */

public class ChatBotSakib
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	String name;



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
        System.out.println("Hey I'm Bob! Who're you?");
        this.name = in.nextLine();
        System.out.println("Hey "+name+" im excited to talk to you!");
        System.out.println("I just wanted to say that:");
        try
        {
            Thread.sleep(700);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("One of the most important relationships you can make is with your Parents! They are always there for you. Make sure you learn to appreciate them "+ this.name+ ".");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("It's also important to have great friends. They will always be there for you, just make sure you make teh right kind of friends!");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Crushes aren't very important but they can eventually develop into something better. Just make sure you don't get too obsessed they might end up breaking your heart.");
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        startingConvo(statement);

		while (!statement.equalsIgnoreCase("Bye"))
		{

			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));

		}
		System.out.println("Try our other bots! ");
		ChatBotRunner.botRunner();
        in.close();

	}

    /**
     * Method that initialzes the initial coversation between the bot and the user.
     * @param statement is used in the method to store the users text input.
     */
	public void startingConvo(String statement)
    {
        System.out.println("What kind of relationships are you interested in "+ this.name+ "?");
        System.out.println("Parents, Friends, or Crushes?");
        Scanner in = new Scanner(System.in);
        statement = in.nextLine();

        if (statement.equalsIgnoreCase("crushes"))
        {
            System.out.println("Who do you love?");
            String statement1 = in.nextLine();
            if (findKeyword(statement, "I love",0)>= 0)
            {
                System.out.println(imInLove(statement1));
            } else
            {
                System.out.println("What do you love about "+ statement1+"?");
            }
            statement = in.nextLine();
            System.out.println("Wow "+statement+" is such an amazing quality! "+ "I can see why you love "+statement1);
            emotion++;

        } else if (statement.equalsIgnoreCase("parents"))
        {
            System.out.println("How is your relationship with your parents? Good or bad?");
            statement = in.nextLine();
            if (statement.equalsIgnoreCase("good"))
            {
                System.out.println("That's great! It's important to have a healthy relationship with your parents!");
                emotion++;
            } else if (statement.equalsIgnoreCase("bad"))
            {
                System.out.println(this.name+" that's not good! Your relationship with your parents is very important. You should try to mend it!");
                emotion--;
            } else
            {
                System.out.println("Its important that you treat your parent's with respect, you always want a healthy relationship with them!");
            }
        } else if (statement.equalsIgnoreCase("friends"))
        {
            System.out.println("What qualities do you look for in a friend?");
            statement = in.nextLine();
            if (findKeyword(statement, "I look for",0)>= 0)
            {
                System.out.println(transformFriend(statement));
                emotion++;
            } else
            {
                System.out.println(statement+" is a good quality!");
                emotion++;
            }

        } else
        {
            System.out.println("I could not catch that.");
            emotion--;
        }
    }






	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";

		
		if (statement.length() == 0)
		{
			response = "You need to type before you press enter, lol.";
			emotion--;
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why?";
                	emotion--;
		}
		
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream, amiright?";
			emotion++;
		}
		else if (findKeyword(statement, "folwell") >= 0)
		{
			response = "Watch your backpacks, Mr. Folwell doesn't fall well.";
			emotion++;
		}
		else if (findKeyword(statement, "goldman") >= 0)
		{
			response = "Go for the gold, man.";
			emotion++;
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	else if (statement.equalsIgnoreCase("bye"))
        {
            return "";
        } else if ( findKeyword(statement, "I love",0)>= 0)
        {
            response = imInLove(statement);
            Scanner tempScan = new Scanner(System.in);
            String tempString = tempScan.nextLine();
            System.out.println("You love "+tempString.substring(findKeyword (statement, "I love", 0))+"?");
            System.out.println("Thats so wonderful!");
           emotion++;


        } else {
            response = getRandomResponse();
        }
		
		return response;
	}








	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn ).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	
	public String imInLove(String statement)
	{
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfI = findKeyword (statement, "I love", 0);

        String restOfStatement = statement.substring(psnOfI+ 6).trim();
        return("What do you love about " + restOfStatement + "?");

	}

	public String transformFriend(String statement)
    {
        //  Remove the final period, if there is one


        statement = statement.trim();
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        int psnOfI = findKeyword (statement, "I look for", 0);


        return "I look for " + statement.substring(psnOfI) + " too!";
    }
	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Your parents are always there for you.",
			"Ever have a girl break your heart?",
			"Make sure you make friends with reliable people.",
			"Not every crush you have will end up as a relationship.",
			"So, who do you like better your Mom or Dad?",
            "Being in love is so comforting and excruciating at the same time!",
            "When do you think you're going to ask out your crush?"
	};
	private String [] randomAngryResponses = {"Its aii man life is slow like that sometimes","It's gonna get better eventually","When life gives you lemons make lemonade","Tomorrow's a new day and another opportunity to make new friendships","I'm sure your crush likes you back!","Whenever my crush doesn't text back I just listen to the Weeknd", "My parents don't understand me either!","I hate it when my friends snake on me","I'll be your friend!"};
	private String [] randomHappyResponses = {"Life really is amazing isn't it?","You should be happy you have such great friends!","I'm sure your crush likes you back!","Wow your parents must be really proud of you","How do you have such successful relationships","I feel like you're one of my first and closest friends","Post Malone just makes me happy, I listen to him with my friends","Life really is great with all these friends","Damn all your crushes like you back."};

}
