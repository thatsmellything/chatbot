package chat.controller;

import chat.model.ChatBot;
import chat.view.ChatFrame;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

import javax.swing.JOptionPane;
import chat.model.ChatTwitter;

/**
 * Final Controller
 * @author jjud0535
 * The controller for the whole project
 */

public class ChatController 
{
	private ChatBot simpleBot;
	private ChatTwitter myTwitter;
	private ChatFrame appFrame;

	/**
	 * This controller builds the appFrame and myTwitter for the twitter
	 * functionality of the program
	 */
	
	public ChatController() 
	{
		// Init the model before the view!
		simpleBot = new ChatBot();
		appFrame = new ChatFrame(this);
		myTwitter = new ChatTwitter(this);
	}

	/**
	 * start the program, put in methods that you want it to
	 * run right off the bat if you want
	 */
	
	public void start() 
	{
		
	}
	
	/**
	 * Method to interact with the bot
	 * @param text
	 * @return
	 */
	

	public String interactWithChatbot(String text) 
	{
		String output = "";
		output += simpleBot.processText(text);
		return output;
	}
	
	/**
	 * checks the text for keywords
	 * @param text
	 * @return
	 */

	public String useChatbotCheckers(String text) 
	{
		String content = "The following checkers passed:";
		
		if(simpleBot.contentChecker(text)) 
		{
			content += "\nContent Checker";
		}
		if(simpleBot.spookyChecker(text))
		{
			content += "\n Spooky Checker Happy Halloween";
		}
		
		return content;
	}
	
	/**
	 * When the user wants the program to close it closes
	 */
	
	private void close()
	{
		System.exit(0);
	}
	
	/**
	 * Catches errors and then displays them in the console
	 */
	
	public void handleErrors(Exception error)
	{
		JOptionPane.showMessageDialog(appFrame, error.getMessage());
	}
	
	/**
	 * gets the appFrame
	 * @return
	 */
	
	public ChatFrame getAppFrame()
	{
		return appFrame;
	}
	
	/**
	 * returns the simple bot
	 * @return
	 */
	
	public ChatBot getChatbot() 
	{
		return simpleBot;
	}
	
	/**
	 * Allows the sending of a tweet
	 * @param text
	 */
	
	public void tweet(String text)
	{
		myTwitter.sendTweet(text);
	}
	
	/**
	 * puts in the username to find most common words
	 * @param user
	 * @return
	 */
	
	public String findWords(String user)
	{
		String results = myTwitter.getMostCommonWord(user);
		return results;
	}
	
	/**
	 * returns the Twitter user statuses to show all the sneaky stuff
	 * @param user
	 * @return
	 */
	
	public ResponseList<Status> findUserTL(String user)
	{
		ResponseList<Status> results = myTwitter.findUserTL(user);
		return results;
	}
	
}
