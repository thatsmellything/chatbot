package chat.model;

import chat.controller.ChatController;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.*;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import java.util.List;
import java.util.stream.Collectors;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import chat.controller.IOController;

/**
 * Creates the java ChatTwtitter class, does all the twitter functions
 * @author jjud0535
 *
 */

public class ChatTwitter
{

	private ChatController app;
	private Twitter chatTwitter;
	
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	private HashMap<String, Integer> wordsAndCount;
	
	/**
	 * Just generally sets up the app
	 * @param app
	 */
	
	public ChatTwitter(ChatController app)
	{
		this.app = app;
		this.chatTwitter = TwitterFactory.getSingleton();
		
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.wordsAndCount = new HashMap<String, Integer>();
		this.totalWordCount = 0;
		
		
	}
	
	

	/**
	 * Sends a tweet to twitter via whats in the text field
	 * @param textToTweet
	 */

	public void sendTweet(String textToTweet)
	{
		try
		{
			chatTwitter.updateStatus(textToTweet + " @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			app.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{		
			app.handleErrors(otherError);
	}
	}

	
	/**
	 * collects tweets from a certain person
	 * @param username
	 */

	private void collectTweets(String username)
	{	
		Paging statusPage = new Paging(1,100);
		int page = 1;
		long lastID = Long.MAX_VALUE;
	
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{	
			ResponseList<Status> listedTweets = chatTwitter.getUserTimeline(username, statusPage);
			for(Status current : listedTweets)
			{
				if(current.getId() < lastID)
				{
					searchedTweets.add(current);
					lastID = current.getId();
				}
			}
		}
		catch(TwitterException searchTweetError)
			{
			app.handleErrors(searchTweetError);
			}
		page++;
		}
	}
	
	/**
	 * changes the statuses to actual words
	 */
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText().toLowerCase();
			tweetText = tweetText.replace("\n", " ");
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]).trim());
			}
		}
	}
	
	/**
	 * removes punctuation from words
	 * @param currentString
	 * @return
	 */
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!:;\"(){}^[]<>-"; 
												
		String scrubbedString = "";
		for (int i = 0; i < currentString.length(); i++)
		{
			if (punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}	
		}
		return scrubbedString;
	}
	
	/**
	 * removes spaces
	 */
	
	private void removeBlanks()
	{
		for (int index = tweetedWords.size() - 1; index >= 0; index--)
		{
			if (tweetedWords.get(index).trim().length() == 0)
			{
				tweetedWords.remove(index);
			}
		}
	}
	
	/**
	 * creates the ignored words array full of boring stuff
	 * @return
	 */
	
	private String [] createIgnoredWordArray()
	{
		String [] boringWords;
		// String fileText = IOController.loadFile(app, "commonWords.txt");
		int wordCount = 0;
		
		Scanner wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));
		
		while(wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();
		
		// Alternative file loading method.
		// Uses the InputStream class
		// Notice the lack of try/catch
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = wordScanner.nextLine();
		}
		
		wordScanner.close();
		return boringWords;
	}
	
	/**
	 * trims the boring words from the tweets
	 * @param boringWords
	 */
	
	private void trimTheBoringWords(String [] boringWords)
	{
		for (int index = tweetedWords.size() - 1; index >= 0; index--)
		{
			for (int removeIndex = 0; removeIndex < boringWords.length; removeIndex++)
			{
				if (tweetedWords.get(index).equalsIgnoreCase(boringWords[removeIndex]))
				{
					tweetedWords.remove(index);
					removeIndex = boringWords.length;
				}
			}
		}
	}
	
	/**
	 * makes a word count of user
	 */
	
	private void generateWordCount()
	{
		for (String word : tweetedWords)
		{
			if (!wordsAndCount.containsKey(word.toLowerCase()))
			{
				wordsAndCount.put(word.toLowerCase(), 1);
			}
			else
			{
				wordsAndCount.replace(word.toLowerCase(), wordsAndCount.get(word.toLowerCase()) + 1);
			}
		}
	}
	
	/**
	 * sorts the hashmap
	 * @return
	 */
	
	private ArrayList<Map.Entry<String, Integer>> sortHashMap()
	{
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsAndCount.entrySet());
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		return entries;
	}
	
	/**
	 * gets most common word from the user
	 * @param username
	 * @return
	 */
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String [] boring = createIgnoredWordArray();
		removeBlanks();
		trimTheBoringWords(boring);
		generateWordCount();

		ArrayList<Map.Entry<String, Integer>> sorted = sortHashMap();	    
		String mostCommonWord = sorted.get(0).getKey();
		int maxWord = 0;
		
		maxWord = sorted.get(0).getValue();

		mostCommon = "The most common word in " + username + "'s "+ searchedTweets.size() + " tweets is '" +
					mostCommonWord + "', and it was used " + maxWord + " times.\nThis is " + 
					(DecimalFormat.getPercentInstance().format(((double) maxWord) / totalWordCount)) + 
					" of total words: " + totalWordCount + " and is " + 
					(DecimalFormat.getPercentInstance().format(((double) maxWord) / wordsAndCount.size())) +
					" of the unique words: " + wordsAndCount.size() ;
		
		mostCommon += "\n\n" + sortedWords();
		
		return mostCommon;
	}


	/**
	 * just sorts the words that the user has said
	 * @return
	 */

	private String sortedWords()
	{
		String allWords = "";
		String [] words = new String [wordsAndCount.size()];
		ArrayList<String> wordList = new ArrayList<String>(wordsAndCount.keySet());
		
		for(int index = 0; index < wordsAndCount.size(); index++)
		{
			words[index] = wordList.get(index);
		}
		for(int index = 0; index < words.length - 1; index++)
		{
			int maxIndex = index;
			
			for (int inner = index + 1; inner < words.length; inner++)
			{
				if (words[inner].compareTo(words[maxIndex]) > 0)
				{
					maxIndex = inner;
				}
			}
			
			String tempMax = words[maxIndex];
			words[maxIndex] = words[index];
			words[index] = tempMax;
		}
		
		for (String word : words)
		{
			allWords += word + ", ";
		}
		
return allWords;
	}
	
	/**
	 * returns the twitter user timeline aka their twitter feed but in a text format
	 * @param user
	 * @return
	 */
	
	public ResponseList<Status> findUserTL(String user)
	{
		
		
		Paging statusPage = new Paging(1,100);
		int page = 1;
		long lastID = Long.MAX_VALUE;
	
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{	
			ResponseList<Status> listedTweets = chatTwitter.getUserTimeline(user, statusPage);
			for(Status current : listedTweets)
			{
				if(current.getId() < lastID)
				{
					searchedTweets.add(current);
					lastID = current.getId();
				}
				return listedTweets;
			}
		}
		catch(TwitterException searchTweetError)
			{
			app.handleErrors(searchTweetError);
			
			}
		page++;
		}
		return null;
		
		
	}



}
