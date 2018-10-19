import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Scanner;
//Dylan (Chengjie) Yang Period 2&3
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotYang
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		//System.out.println (getGreeting());

        askName(statement);
        //Starter(statement);
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
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, what's? up?";
	}

    /**
     * Asks the name, and saves the name into memory
     * @param statement
     */
	private void askName(String statement)
    {
        System.out.println("Hello, what's your name?");
        Scanner firstName = new Scanner(System.in);
        String name = firstName.nextLine();
        System.out.println("That's an interesting name, " + name);
    }

    private String Starter(String statement)
    {
        String[] accept = {"Yes", "Yea", "Yeah", "Sure", "Ok", "Why not"};
        String[] decline = {"No", "Nah", "Nope", "Not really"};

        System.out.println("So, what do you want to know? I can teach you some things if you want.");
        Scanner in = new Scanner (System.in);
        statement = in.nextLine();
        int index = 0;
        int acceptNum = findKeyword(statement, accept[index], 0);
        while (acceptNum <= 0 && index < 5)
        {
            index++;
            acceptNum = findKeyword(statement, accept[index], 0);
            System.out.println(acceptNum);

            if (acceptNum == 0)
            {
                System.out.println("Glad u said yes");
            }

        }
        if (acceptNum == -1)
        {
            index = 0;
            int declineNum = findKeyword(statement, decline[index], 0);
            while (declineNum <= 0 && index < 3)
            {
                index++;
                declineNum = findKeyword(statement, decline[index], 0);
            }
        }
        if (acceptNum != -1)
        {
            System.out.println(index);
            return "Yes";
        }
        return "no";

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
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
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
		else if (findKeyword(statement, "learn") >=0 || findKeyword(statement, "teach") >= 0)
        {
            System.out.println("Here's a guide on how to wash your clothes.");
            try {
                Thread.sleep(1000);
                System.out.println("I suggest you use a washing machine, since its the 21st century and we ain't living in rocks");
                Thread.sleep(1000);
                System.out.println("First you need to have detergent, buy accordingly to your favorite scent and skin conditions");
                Thread.sleep(1000);
                System.out.println("Then, I recommend you to have to have fabric softener, buy accordingly to your favorite scent and skin conditions");
                Thread.sleep(2000);
                System.out.println("When you are ready to wash your clothes, separate the color sensitive clothes into one pile.");
                Thread.sleep(1000);
                System.out.println("Load your clothes in to the washing machine and make sure nothing is crumbled up");
                Thread.sleep(1000);
                System.out.println("Add all your materials such as detergent, bleach, and softener. For questions about materials feel free to ask");
                Thread.sleep(2000);
                System.out.println("Add your payment in quarters if you are using the machines at an laundromat");
                Thread.sleep(1000);
                System.out.println("Press start and wait!");
                Thread.sleep(1000);
                System.out.println("When your clothes are done washing, remove them, and add them to the dryer");
                Thread.sleep(1000);
                System.out.println("Toss in some scent sheets and dry according to your laundry size");
                Thread.sleep(1000);
                System.out.println("For summer clothes, 20-30 minutes on high will do");
                Thread.sleep(1000);
                System.out.println("For winter clothes, you would need 30-40 minutes on high");
                Thread.sleep(1000);
                System.out.println("For Questions on materials, say 'Can I use'");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
        }

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "Can I use", 0) >= 0)
        {
            response = transformCanIUse(statement);
        }
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else
		{
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
     * Takes input of the user, and returns a statement depending on
     * @param statement
     * @return
     */
	private String transformCanIUse(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "Can I use", 0);
		String restOfStatement = statement.substring(psn + 9).trim();

		String[] material = {"Soap", "Bleach", "Tide Pods", "Detergent Pods", "Baking Soda"};

		int index = 0;
		int matNum = findKeyword(statement, material[index], 0);
		while (matNum <= 0 && index < 4)
        {
            index++;
            matNum = findKeyword(statement, material[index], 0);

        }
        if (matNum == -1)
        {
            return "Why do you want to use " + restOfStatement + "?";
        }
        if (material[index].equals("Bleach"))
        {
            return "You could just bleach, but be careful";
        }
        if (material[index].equals("Soap") || material[index].equals("Baking Soda"))
        {
            return material[index] + " isn't really useful for laundry machines";
        }
        if (material[index].equals("Tide Pods") || material[index].equals("Detergent Pods"))
        {
            return material[index] + " are great, just don't eat it!";
        }

		return "Why do you want to use " + restOfStatement + "?";
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
		String restOfStatement = statement.substring(psn + 6).trim();
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
			"Hmmm.",
			"Do you really think so?",
			"Interesting..",
			"Tell me more",
			"Thinking...",
			"Could you say that again?",
            "Sorry, I didn't get that"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	
}
