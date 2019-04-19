package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatFrame;
import javax.swing.JOptionPane;

public class ChatController 
{
	private Chatbot simpleBot;
	private ChatFrame appFrame;

	public ChatController() 
	{
		// Init the model before the view!
		simpleBot = new Chatbot();
		appFrame = new ChatFrame(this);
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
	
}
