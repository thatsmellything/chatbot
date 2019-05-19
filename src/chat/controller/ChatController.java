package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatFrame;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

import javax.swing.JOptionPane;
import chat.model.ChatTwitter;


public class ChatController 
{
	private Chatbot simpleBot;
	private ChatTwitter myTwitter;
	private ChatFrame appFrame;

	public ChatController() 
	{
		// Init the model before the view!
		simpleBot = new Chatbot();
		appFrame = new ChatFrame(this);
		myTwitter = new ChatTwitter(this);
	}

	public void start() 
	{
		
	}

	public String interactWithChatbot(String text) 
	{
		String output = "";
		output += simpleBot.processText(text);
		return output;
	}

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
	
	private void close()
	{
		System.exit(0);
	}
	
	public void handleErrors(Exception error)
	{
		JOptionPane.showMessageDialog(appFrame, error.getMessage());
	}
	
	public ChatFrame getAppFrame()
	{
		return appFrame;
	}
	
	public Chatbot getChatbot() 
	{
		return simpleBot;
	}
	
	public void tweet(String text)
	{
		myTwitter.sendTweet(text);
	}
	
	public String findWords(String user)
	{
		String results = myTwitter.getMostCommonWord(user);
		return results;
	}
	
	public ResponseList<Status> findUserTL(String user)
	{
		ResponseList<Status> results = myTwitter.findUserTL(user);
		return results;
	}
	
}
