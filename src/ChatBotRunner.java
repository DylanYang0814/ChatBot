import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBotYang chatbot2 = new ChatBotYang();
		ChatBotAlam chatbot1 = new ChatBotAlam();
		ChatBotYu chatbot3 = new ChatBotYu();
		ChatBotSakib chatbot4 = new ChatBotSakib();
		

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the chatbot, nice to meet you.");
		System.out.println("Here is the menu:");
		System.out.println("For information on cooking, say 1 or cook");
		System.out.println("For information on laundry, folding, ironing, and general hygiene say 2 or hygiene");
		System.out.println("For information on college class, say 3 or class");
		System.out.println("For more information on college relationships say 4 or relationship");
        //System.out.println();
		String statement = in.nextLine();


		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1

            if (statement.equals("cook") || statement.equals("1"))
            {
                chatbot1.chatLoop(statement);
                statement = in.nextLine();
            }
            if (statement.equals("hyigene") || statement.equals("2"))
            {
				chatbot2.chatLoop(statement);
				statement = in.nextLine();
            }
            if (statement.equals("class") || statement.equals("3"))
            {
            	chatbot3.chatLoop(statement);
            	statement = in.nextLine();
            }
            if (statement.equals("relationship") || statement.equals("4"))
            {
            	chatbot4.chatLoop(statement);
            	statement = in.nextLine();
            }
		}
	}

}
