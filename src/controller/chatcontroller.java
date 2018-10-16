package controller;

import model.chatmodel;
import javax.swing.JOptionPane;

public class chatcontroller
{
	private chatmodel simpleBot;
	
	//Constructor
	public chatcontroller()
	{
		simpleBot = new chatmodel();
		
	}

	public void start()
	{	
		
		chatmodel simpleBot = new chatmodel();
		
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
