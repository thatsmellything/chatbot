package chat.controller;

import javax.swing.JOptionPane;

import chat.model.ChatBot;
import chat.tests.ChatbotTest;
import chat.tests.ControllerTest;


public class ChatController
{
	private ChatBot simpleBot;
	
	//Constructor
	public ChatController()
	{
		simpleBot = new ChatBot();
		
	}

	public void start()
	{	
		
		
		
		String userInput = "";
		while (!userInput.equalsIgnoreCase("quit"))
		{
		userInput = interactWithChatbot(userInput);
		simpleBot.setCurrentUser(userInput);
		}
		
		
	}
	
	public String interactWithChatbot(String text)
	{
		String output = "";
		String userInput = JOptionPane.showInputDialog(null, "Hi what do you want t talk about?");
		String userResponse = null;
		userInput = simpleBot.processText(userResponse);
		return output;
		
	}
	
	public String getChatbot()
	{
		return null;
		
	}
	
	public String useChatbotCheckers(String text)
	{
		String testedValues = "The following checkers passed:";
				if(simpleBot.contentChecker(text))
				{
					testedValues += "\nContent Checker";
				}
		if(simpleBot.spookyChecker(text))
		{
			testedValues += "\nSpooky Checker Happy Halloween";
		}
		if(simpleBot.validityChecker(text))
		{testedValues += "\nEverything is valid";
			
		}
		return testedValues;
	}
	
	private void close()
	{
		System.exit(0);
		
	}
	
	public void handleErrors(Exception error)
	{
		
		JOptionPane.showMessageDialog(appFrame, error.getMessage());
	}
	
	public Chatframe getAppFrame()
	{
		return appFrame;
	}
	
	public Chatbot getChatbot()
	{
		return simpleBot;
	}
	

	
	
	
	
}



