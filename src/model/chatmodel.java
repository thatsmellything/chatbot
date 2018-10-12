package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class chatmodel
{
	//Data members
	private String currentUser;
	private String joke;
	private String content;
	
	ArrayList<String> responseList = new ArrayList<String>();
	ArrayList<String> spookyList = new ArrayList<String>();
	
	// Constructors
	public chatmodel()
	{
		
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
		responseList.add(currentUser);
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
