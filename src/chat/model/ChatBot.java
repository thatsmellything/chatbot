package chat.model;

import java.util.ArrayList;

public class Chatbot
{
	// Data Members
	private String currentUser;
	private String joke;
	private String content;
	
	private ArrayList<String> responseList;
	private ArrayList<String> spookyList;
	
	public Chatbot()
	{
		// Constructors
		this.currentUser = new String("Craig Johnson");
		this.content = new String("A lonely tumbleweed blows across the land as the crickets chirp through the empty wind.");
		this.joke = "What's the deal with airline food?";
		
		// Reply Lists
		this.responseList = new ArrayList<String>(15);
		this.spookyList = new ArrayList<String>();
		
		buildLists();
		
	}
	
	private void buildLists()
	{
//		responseList
		// Greetings
		responseList.add("Hello!");
		responseList.add("Good day!");
		responseList.add("How are you?");
		responseList.add("What's up?");
		responseList.add("Hallo!");
		responseList.add("Wie geht's?");
		// Farewells
		responseList.add("Goodbye!");
		responseList.add("C'ya!");
		responseList.add("Tschüss!");
		// Confirm
		responseList.add("Yes.");
		responseList.add("Sure!");
		responseList.add("Naturally.");
		responseList.add("Ja.");
		// Deny
		responseList.add("No.");
		responseList.add("Negative.");
		responseList.add("Not a chance.");
		responseList.add("Nein.");
		
//		spookyList
		spookyList.add("Halloween");
		spookyList.add("spooky");
		spookyList.add("Th-Th-Th-The S-S-Scissorman! NOT THE SCISSORMAN!");
		spookyList.add("It's close to midnight... a-something evil's lurking in the dark.");
		spookyList.add("And then there were none!");
		spookyList.add("Do you want to play a game?");
		spookyList.add("He did the Monster Mash...");
		spookyList.add("...It was a Graveyard Smash.");
		spookyList.add("You'll float too!");
		spookyList.add("All work and no play makes Jack a dull boy.");
		
	}
	
	public String processText(String userText)
	{
		String text = "";
		
		if(userText == null)
		{
			text += "null!\n";
		}
		else
		{
			text += "You said: " + userText + "\n";
			
			if(contentChecker(userText))
			{
				text += "You said the special words!\n";
			}
			int randomIndex = (int)(Math.random() * responseList.size());
			text += "Chatbot says: " + responseList.get(randomIndex) + "\n";
		}
		
		return text;
	}
	
	public boolean contentChecker(String hasInput)
	{
		Boolean goodContent = false;
		
		if(legitimacyChecker(hasInput) && hasInput.equals(content))
		{
			goodContent = true;
		}
		else if(hasInput.contains(" " + content + " "))
		{
			goodContent = true;
		}
		else
		{
			goodContent = false;
		}
		
		return goodContent;
	
	}
	
	public boolean spookyChecker(String input)
	{
		Boolean isSpooky = false;
		
		if(input.contains("Halloween"))
		{
			isSpooky = true;
		}
		
		for(String phrase : spookyList)
		{
			if(input.contains(phrase))
			{
				isSpooky = true;
			}
			if(input.contains("Easter"))
			{
				isSpooky = false;
			}
		}
		
		return isSpooky;
	}
	// Props to Brian for the framework of spookyChecker. Thanks Brian!
	
	public Chatbot(String content)
	{
		this.content = content;
	}
	
	public boolean legitimacyChecker(String text)
	{
		boolean isValid = false;
		
		if (text != null || text.length() > 3) 
		{
			isValid = true;
		}
		else
		{
			isValid = false;
		}
		
		return isValid;
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
	public ArrayList<String> getResponseList()
	{
		return responseList;
	}
	public ArrayList<String> getSpookyList()
	{
		return spookyList;
	}
		
	// Setters
	public void setCurrentUser(String name)
	{
		this.currentUser = name;
	}
	public void setJoke(String joke)
	{
		this.joke = joke;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public void setResponseList(ArrayList<String> responseList)
	{
		this.responseList = responseList;
	}
	public void setSpookyList(ArrayList<String> spookyList)
	{
		this.spookyList = spookyList;
	}
}
