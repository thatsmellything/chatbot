package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class chatmodel
{
	//Data members
	private String currentUser;
	private String joke;
	private String content;
	
	private ArrayList<String> responseList = new ArrayList<String>();
	private ArrayList<String> spookyList = new ArrayList<String>();
	
	// Constructors
	public chatmodel()
	{
		this.joke = "Why did the monkey cross the road? it was stapled to the chicken";
		this.currentUser = new String("default user - boring!!!!");
		this.content = new String("Empty of all content but null");
		
		this.responseList = new ArrayList<String>();
		this.spookyList = new ArrayList<String>();
	
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
	}
	
	public chatmodel(String currentUser, String joke, String content)
	{
		
	}
	
	//Getters
	public String currentUser()
	{
		return currentUser;
	}
	public String joke()
	{
		return joke;
	}
	public String content()
	{
		return content;
	}
	
	//Setters
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

	
	
}
