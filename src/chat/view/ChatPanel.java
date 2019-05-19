package chat.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.event.*;
import javax.swing.GroupLayout.*;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.IOController;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

import javax.swing.border.MatteBorder;

public class ChatPanel extends JPanel
{
	private JTextField chatField;
	private JTextField searchField;
	private JButton searchButton;
	private JTextField searchField_1;
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadingButton;
	private JButton savingButton;
	private JButton tweetButton;
	private JButton searchTwitterButton;
	private JButton findTweetButton;
	private JButton getTLButton;
	private SpringLayout appLayout;
	private ChatController appController;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	private JScrollPane chatPane_1;
	private JButton resetButton;
	private JPanel buttonPanelBottom;
	private JPanel buttonPanelTop;
	private ImageIcon saveIcon;
	private ImageIcon loadIcon;
	private ImageIcon chatIcon;
	private ImageIcon tweetIcon;
	private ImageIcon checkerIcon;
	private ImageIcon searchIcon;
	
	
	private JScrollPane following;
	private JTextArea pastCommandsArea;
	private JButton findFollowersButton;
	
	
	final static Color  HILIT_COLOR = Color.LIGHT_GRAY;
    final static Color  ERROR_COLOR = Color.PINK;
    final static String CANCEL_ACTION = "cancel-search";
  
  
	
	public ChatPanel(ChatController appController)
	{
		super();
		
		this.appController = appController;
		appLayout = new SpringLayout();
		
		saveIcon = new ImageIcon(getClass().getResource("/chat/view/images/save.png"));
		loadIcon = new ImageIcon(getClass().getResource("/chat/view/images/load.png"));
		chatIcon = new ImageIcon(getClass().getResource("/chat/view/images/chat.png"));
		tweetIcon = new ImageIcon(getClass().getResource("/chat/view/images/tweet.png"));
		checkerIcon = new ImageIcon(getClass().getResource("/chat/view/images/search.png"));
		searchIcon = new ImageIcon(getClass().getResource("/chat/view/images/searchTwitter.png"));

		
		
		
//		
		savingButton = new JButton("Save", saveIcon);
		loadingButton = new JButton("Load", loadIcon);
		chatButton = new JButton("Chat", chatIcon);
		checkerButton = new JButton("Check Text", checkerIcon);
		tweetButton = new JButton("Send tweet", tweetIcon);
		searchTwitterButton = new JButton("Search Twitter", searchIcon);
		findTweetButton = new JButton("Find a Tweet");
		getTLButton = new JButton("InDepth Info >:)");
		getTLButton.setToolTipText("Finds super in depth info on the target. \nDeconstructs each tweet to the bare \nminimum of what they have to offer and \ndisplays it all to the user. Best to use\nwith combination of a text editor\nto find key values.");
		findFollowersButton = new JButton("Find Followers");
		searchButton = new JButton("Search");
		
		buttonPanelTop = new JPanel(new GridLayout(1,0));
		buttonPanelTop.setBorder(null);
		buttonPanelBottom = new JPanel(new GridLayout(1,0));
		buttonPanelBottom.setBorder(null);
		appLayout.putConstraint(SpringLayout.SOUTH, buttonPanelTop, -6, SpringLayout.NORTH, buttonPanelBottom);
		
		searchField = new JTextField("Search for keywords here");
		chatField = new JTextField("Talk to the bot here", 50);
		chatArea = new JTextArea("Chat Area", 20, 50);
//		
		pastCommandsArea = new JTextArea("Followers area", 10, 10);
		chatPane = new JScrollPane();
		following = new JScrollPane();
		setupLayout();
		setupScrollPane();
		setupPanel();
		setupListeners();
		setupButtonsBottom();
		setupButtonsTop();
	}
	
	private void setupButtonsTop()
	{
		buttonPanelTop.add(findFollowersButton);
		buttonPanelTop.add(findTweetButton);
		buttonPanelTop.add(getTLButton);
	}
	
	private void setupButtonsBottom()
	{
		buttonPanelBottom.add(savingButton);
		buttonPanelBottom.add(loadingButton);
		buttonPanelBottom.add(chatButton);
		buttonPanelBottom.add(checkerButton);
		buttonPanelBottom.add(tweetButton);
		buttonPanelBottom.add(searchTwitterButton);
		
	}
	
	private void setupPanel()
	{
		this.setLayout(appLayout);
		this.setPreferredSize(new Dimension(1024, 768));
		buttonPanelTop.setPreferredSize(new Dimension(900, 50));
		buttonPanelTop.setBackground(Color.GRAY);
		buttonPanelBottom.setPreferredSize(new Dimension(900, 150));
		buttonPanelBottom.setBackground(Color.GRAY);
		this.setBackground(Color.GRAY);
		this.add(searchButton);
		this.add(searchField_1);
		this.add(chatPane_1);
		this.add(chatField);
		this.add(buttonPanelTop);
		this.add(buttonPanelBottom);
	}
	
	void message(String msg) {
        chatArea.setText(msg);
    }
	
	public void search(String s) {
        DefaultHighlighter hilit = new DefaultHighlighter();
        DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
		hilit.removeAllHighlights();
		searchField.setHighlighter(hilit);
		Color searchFieldBg = searchField.getBackground();
        
		
        String searchWord = searchField.getText();
        if (searchWord.length() <= 0) {
            chatArea.setText("Nothing to search");
            return;
        }
         
        String content = chatArea.getText();
        int index = content.indexOf(searchWord, 0);
        if (index >= 0) {   // match found
            try {
                int end = index + searchWord.length();
                hilit.addHighlight(index, end, painter);
                chatArea.setCaretPosition(end);
                searchField.setBackground(searchFieldBg);
                message("'" + searchWord + "' found. Press ESC to end search");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        } else {
            searchField.setBackground(ERROR_COLOR);
            message("'" + searchWord + "' not found. Press ESC to start a new search");
        }
    }
	
	private void setupScrollPane()
	{
		
		chatPane_1.setViewportView(chatArea);
		
		pastCommandsArea = new JTextArea("Past Searches/Entries" + "\n\n" + "Find Followers: Finds the followers of the user searched" + "\n" + "Find Tweet: Will try to find a specific tweet" + "\n" + "InDepth Info: Finds all of the details to the users tweets and displays them" + "\n" + "Save: Saves the chat on the right" + "\n" + "Load: Loads a past save file" + "\n" + "Chat: Chats with the chatbot" + "\n" + "Check Text: Checks if the text supplied is valid" + "\n" + "Send Tweet: Posts a tweet on my account" + "\n" + "Search Twitter: Searches for the username supplied and reports back number of posts, most common word, and common words" + "\n\n" , 10, 20);
		pastCommandsArea.setToolTipText("Past Searches/Entries\nFind Followers: Finds the followers of the user searched\nFind Tweet: Will try to find a specific tweet\nInDepth Info: Finds all of the details to the users tweets and displays them\nSave: Saves the chat on the right\nLoad: Loads a past save file\nChat: Chats with the chatbot\nCheck Text: Checks if the text supplied is valid\nSend Tweet: Posts a tweet on my account\nSearch Twitter: Searches for the username supplied and reports back number of posts, most common word, and common words\n");
		chatPane_1.setRowHeaderView(pastCommandsArea);
		pastCommandsArea.setBackground(Color.DARK_GRAY);
		pastCommandsArea.setForeground(Color.CYAN);
		pastCommandsArea.setEditable(false);
		
		pastCommandsArea.setEditable(false);
		pastCommandsArea.setLineWrap(true);
		pastCommandsArea.setWrapStyleWord(true);
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				pastCommandsArea.append("\n" + "Chatted with Bot" + "\n");
				String userText = chatField.getText();
				String response = "";
				response += appController.interactWithChatbot(userText);
				chatArea.append(response);
				chatArea.setCaretPosition(chatArea.getDocument().getLength());
				
				chatField.setText("");
			}
		});
		
		savingButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						String chatText = chatArea.getText();
						String path = getPath("save");
						IOController.saveText(appController, path, chatText);
						chatArea.setText("Chat saved!");
						pastCommandsArea.append("\n" + "Saved Chat" + "\n");
					}
						
				});
		
		loadingButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent click)
					{
						pastCommandsArea.append("\n" + "Loaded File" + "\n");
					}
				});
		searchButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						String s = searchField.getText();
						search(s);
					}
			
				}
				);
		
		checkerButton.addActionListener(new ActionListener()  
				{
					public void actionPerformed(ActionEvent click)
					{
						pastCommandsArea.append("\n" + "Checked Text" + "\n");
					}
				
				});
		
		tweetButton.addActionListener(new ActionListener()  
		{
			public void actionPerformed(ActionEvent click)
			{
				String textToTweet = chatField.getText().trim();
				pastCommandsArea.append("\n" + "Posted tweet" + "\n");
				appController.tweet(textToTweet);
				
			}
		
		});

		searchTwitterButton.addActionListener(new ActionListener()  
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText().trim();
				pastCommandsArea.append("\n" + "Searched " + username + "\n");
				String display = appController.findWords(username);
				chatArea.append("\n\n" + display);
				
			}
		
		});
		
		findFollowersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText().trim();
				pastCommandsArea.append("\n" + "Found " + username + "'s followers" + "\n");
				String display = appController.findWords(username);
				chatArea.append("\n\n" + display);
				
			}
		});
		
		getTLButton.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent click)
				{
				String username = chatField.getText();
				pastCommandsArea.append("\n" + "Got in depth info on " + username + "'s account"  + "\n");
				ResponseList<Status> display = appController.findUserTL(username);
				chatArea.append("\n\n" + display);
				}
		});
				
		
		
	}
	
	private String getPath(String choice)
	{
		String path = ".";
		int result = -99;
		JFileChooser fileChooser = new JFileChooser();
		if(choice.equals("save"))
		{
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showSaveDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getCurrentDirectory().getAbsolutePath();
			}
		}
		else
		{
			result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		return path;
	}
	
	private void setupLayout()
	{
		savingButton = new JButton("Save");
		savingButton.setForeground(Color.BLUE);
		savingButton.setBackground(Color.GRAY);
		
		
		loadingButton = new JButton("Load");
		loadingButton.setForeground(Color.RED);
		
		
		chatButton = new JButton("Chat");
		
		
		checkerButton = new JButton("Check Text");
		checkerButton.setForeground(Color.ORANGE);
		
		searchField_1 = new JTextField("", 10);
		appLayout.putConstraint(SpringLayout.NORTH, searchField_1, 21, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.NORTH, searchButton, 0, SpringLayout.NORTH, searchField_1);
		appLayout.putConstraint(SpringLayout.EAST, searchButton, -6, SpringLayout.WEST, searchField_1);
		appLayout.putConstraint(SpringLayout.WEST, searchField_1, 809, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, searchField_1, -50, SpringLayout.EAST, this);
		searchField_1.setToolTipText("Search the chat text");
		searchField_1.setForeground(Color.GREEN);
		searchField_1.setBackground(Color.DARK_GRAY);
		
		chatField = new JTextField("", 50);
		chatField.setToolTipText("Please enter the twitter account here or talk with chatbot");
		chatField.setForeground(Color.GREEN);
		chatField.setBackground(Color.DARK_GRAY);
		appLayout.putConstraint(SpringLayout.NORTH, buttonPanelBottom, 60, SpringLayout.SOUTH, chatField);
		
		chatArea = new JTextArea("Chat Area", 20, 30);
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		pastCommandsArea.setToolTipText("Past Searches/Entries" + "\n\n" + "Find Followers: Finds the followers of the user searched" + "\n" + "Find Tweet: Will try to find a specific tweet" + "\n" + "InDepth Info: Finds all of the details to the users tweets and displays them" + "\n" + "Save: Saves the chat on the right" + "\n" + "Load: Loads a past save file" + "\n" + "Chat: Chats with the chatbot" + "\n" + "Check Text: Checks if the text supplied is valid" + "\n" + "Send Tweet: Posts a tweet on my account" + "\n" + "Search Twitter: Searches for the username supplied and reports back number of posts, most common word, and common words" + "\n");
		chatArea.setToolTipText("This is where the majority of the text goes from using the program. Only important information will be displayed on the screen to the left. The rest of it will be here :)");
		chatArea.setForeground(Color.GREEN);
		chatArea.setBackground(Color.DARK_GRAY);
		
		chatPane_1 = new JScrollPane();
		appLayout.putConstraint(SpringLayout.SOUTH, searchField_1, -3, SpringLayout.NORTH, chatPane_1);
		appLayout.putConstraint(SpringLayout.WEST, buttonPanelTop, 0, SpringLayout.WEST, chatPane_1);
		appLayout.putConstraint(SpringLayout.EAST, buttonPanelTop, 0, SpringLayout.EAST, chatPane_1);
		appLayout.putConstraint(SpringLayout.WEST, buttonPanelBottom, 0, SpringLayout.WEST, chatPane_1);
		appLayout.putConstraint(SpringLayout.EAST, buttonPanelBottom, 0, SpringLayout.EAST, chatPane_1);
		appLayout.putConstraint(SpringLayout.NORTH, chatField, 30, SpringLayout.SOUTH, chatPane_1);
		appLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatPane_1);
		appLayout.putConstraint(SpringLayout.EAST, chatField, 0, SpringLayout.EAST, chatPane_1);
		appLayout.putConstraint(SpringLayout.NORTH, chatPane_1, 50, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatPane_1, 50, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatPane_1, -50, SpringLayout.EAST, this);
	}
}