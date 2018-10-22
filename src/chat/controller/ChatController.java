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
		
		ChatBot simpleBot = new ChatBot();
		
		String userInput = "";
		while (!userInput.equalsIgnoreCase("quit"))
		{
		userInput = JOptionPane.showInputDialog(null, "Type 'Quit' to end the program. Press enter to continue.");
		userInput = JOptionPane.showInputDialog(null, "What is your name?");
		simpleBot.setCurrentUser(userInput);
		}
		
		
	}
	
	public String interactWithChatbot(String text)
	{
		String userInput = JOptionPane.showInputDialog(null, "Hi what do you want t talk about?");
		userInput = simpleBot.processText(userInput);
		return userInput;
		
	}

}
