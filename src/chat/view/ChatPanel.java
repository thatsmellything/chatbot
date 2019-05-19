package chat.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

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
	private JTextArea fArea;
	private JButton findFollowersButton;
	
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
		getTLButton = new JButton("In Depth Info >:)");
		getTLButton.setToolTipText("Finds super in depth info on the target. Deconstructs each tweet to the bare minimum of what they have to offer and displays it all to the user.");
		findFollowersButton = new JButton("Find Followers");
		
		buttonPanelTop = new JPanel(new GridLayout(1,0));
		buttonPanelTop.setBorder(null);
		buttonPanelBottom = new JPanel(new GridLayout(1,0));
		buttonPanelBottom.setBorder(null);
		appLayout.putConstraint(SpringLayout.SOUTH, buttonPanelTop, -6, SpringLayout.NORTH, buttonPanelBottom);
		
		chatField = new JTextField("Talk to the bot here", 50);
		chatArea = new JTextArea("Chat Area", 20, 50);
//		
		fArea = new JTextArea("Followers area", 10, 10);
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
		this.add(chatPane_1);
		this.add(chatField);
		this.add(buttonPanelTop);
		this.add(buttonPanelBottom);
	}
	
	
	
	private void setupScrollPane()
	{
		
		chatPane_1.setViewportView(chatArea);
		chatPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		fArea = new JTextArea("Your Followers", 10, 20);
		chatPane_1.setRowHeaderView(fArea);
		fArea.setBackground(Color.DARK_GRAY);
		fArea.setForeground(Color.CYAN);
		fArea.setEditable(false);
		
		fArea.setEditable(false);
		fArea.setLineWrap(false);
		fArea.setWrapStyleWord(true);
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
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
					}
						
				});
		
		loadingButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent click)
					{
						
					}
				});
		
		checkerButton.addActionListener(new ActionListener()  
				{
					public void actionPerformed(ActionEvent click)
					{
						
					}
				
				});
		
		tweetButton.addActionListener(new ActionListener()  
		{
			public void actionPerformed(ActionEvent click)
			{
				String textToTweet = chatField.getText().trim();
				appController.tweet(textToTweet);
				
			}
		
		});

		searchTwitterButton.addActionListener(new ActionListener()  
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText().trim();
				String display = appController.findWords(username);
				chatArea.append("\n\n" + display);
				
			}
		
		});
		
		findFollowersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText().trim();
				String display = appController.findWords(username);
				chatArea.append("\n\n" + display);
				
			}
		});
		
		getTLButton.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent click)
				{
				String username = chatField.getText();
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
		
		
		chatField = new JTextField("", 50);
		chatField.setToolTipText("Please enter the twitter account here or talk with chatbot");
		chatField.setForeground(Color.GREEN);
		chatField.setBackground(Color.DARK_GRAY);
		appLayout.putConstraint(SpringLayout.NORTH, buttonPanelBottom, 60, SpringLayout.SOUTH, chatField);
		
		chatArea = new JTextArea("Chat Area", 20, 30);
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		chatArea.setToolTipText("This is where the majority of the text goes from using the program. Only important information will be displayed on the screen to the left. The rest of it will be here :)");
		chatArea.setForeground(Color.GREEN);
		chatArea.setBackground(Color.DARK_GRAY);
		
		chatPane_1 = new JScrollPane();
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