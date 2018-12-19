package chat.model;

import java.util.ArrayList;
import chat.view.*;
import javax.swing.JOptionPane;

public class ChatBot
{
	// Data members
	private ChatPanel appLayout;
	private ChatFrame appFrame;
	private String currentUser;
	private String joke;
	private String content;

	private ArrayList<String> responseList = new ArrayList<String>();
	private ArrayList<String> spookyList = new ArrayList<String>();

	// Constructors
	public ChatBot()
	{
		this.joke = "Why did the monkey cross the road? it was stapled to the chicken";
		this.currentUser = new String("default user - boring!!!!");
		this.content = new String("Empty of all content but null");

		this.responseList = new ArrayList<String>();
		this.spookyList = new ArrayList<String>();
		
		this.appFrame = new ChatFrame(this);
		this.appLayout = new ChatPanel(this);
		
		buildTheLists();
	}
	
	

	private void buildTheLists()
	{
		responseList.add("Hello! How are you?");
		responseList.add("Goodbye!");
		responseList.add("Yes!");
		responseList.add("NO!");
		responseList.add("What is your favorite color?");
		responseList.add("Do you play any sports?");
		responseList.add("");

		spookyList.add("Halloween boooooooo!");
		spookyList.add("Destroy the child!");
		spookyList.add("Hype for swimming!");
	}

	public ChatBot(String currentUser, String joke, String content)
	{

	}

	// Getters
	public String getCurrentUser()
	{
		return currentUser;
	}

	public String getJoke()
	{
		return joke;
	}

	public String getContent()
	{
		return content;
	}

	public ArrayList<String> getSpookyList()
	{
		return spookyList;
	}

	public ArrayList<String> getResponseList()
	{
		return responseList;
	}

	// Setters
	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
		responseList.add(0, currentUser);
		JOptionPane.showMessageDialog(null, "Your name is " + responseList);
	}

	public void setJoke(String joke)
	{
		this.joke = joke;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void spookyList()
	{
		this.spookyList = spookyList;
	}

	// legit

	public boolean legitimacyChecker(String input)
	{
		boolean isValid = true;
		if (input == null)
		{
			isValid = false;
		}
		else if (input.equals(""))
		{
			isValid = false;
		}
		else if (input.contains("klj") || input.contains("wasdf"))
		{
			isValid = false;
		}
		return isValid;

	}

	// Spooky tester

	public Boolean spookyChecker(String inputContent)
	{
		boolean isSpooky = false;
		if (inputContent.contains("Halloween"))
		{
			isSpooky = true;
		}
		for (String Phrase : spookyList)
		{
			if (inputContent.contains(Phrase))
			{
				isSpooky = true;
			}
			if (inputContent.contains("Easter"))
			{
				isSpooky = false;
			}
		}
		return isSpooky;
	}

	// Content checker test

	public Boolean contentChecker(String text)
	{
		boolean hasContent = false;

		if (text.equals(content))
		{
			hasContent = true;
		}
		// else if
		{

		}
		return hasContent;
	}

	// process text

	public String processText(String userText)
	{
		String output = null;
		int randomIndex = (int)(Math.random()*responseList.size());
		output += "You said:" + userText;
		output += "\n chatbot says: " + responseList.get(randomIndex);
		if (contentChecker(userText))
		{
			output += "You said the special words.\n";
			// Backslash n creates a new line in the text back to the user
		}
		
		return output;
	}



	public boolean validityChecker(String text)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
