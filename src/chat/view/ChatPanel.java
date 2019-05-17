package chat.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.IOController;

public class ChatPanel extends JPanel
{
	private JTextField chatField;
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadingButton;
	private JButton savingButton;
	private JButton tweetButton;
	private JButton searchTwitterButton;
	private SpringLayout appLayout;
	private SpringLayout appLayout_1;
	private ChatController appController;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	private JScrollPane chatPane_1;
	private JButton resetButton;
	private JPanel buttonPanel;
	private ImageIcon saveIcon;
	private ImageIcon loadIcon;
	private ImageIcon chatIcon;
	private ImageIcon tweetIcon;
	private ImageIcon checkerIcon;
	private ImageIcon searchIcon;
	
	private JScrollPane following;
	private JTextArea fArea;
	
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

		
		
		this.appController = appController;
		appLayout_1 = new SpringLayout();
//		
		savingButton = new JButton("Save", saveIcon);
		loadingButton = new JButton("Load", loadIcon);
		chatButton = new JButton("Chat", chatIcon);
		checkerButton = new JButton("Check Text", checkerIcon);
		tweetButton = new JButton("Send tweet", tweetIcon);
		searchTwitterButton = new JButton("Search Twitter", searchIcon);
		
		buttonPanel = new JPanel(new GridLayout(1,0));
		
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
		setupButtons();
	}
	
	private void setupButtons()
	{
		buttonPanel.add(savingButton);
		buttonPanel.add(loadingButton);
		buttonPanel.add(chatButton);
		buttonPanel.add(checkerButton);
		buttonPanel.add(tweetButton);
		buttonPanel.add(searchTwitterButton);
	}
	
	private void setupPanel()
	{
		this.setLayout(appLayout_1);
		this.setPreferredSize(new Dimension(1024, 768));
		buttonPanel.setPreferredSize(new Dimension(900, 150));
		buttonPanel.setBackground(Color.WHITE);
		this.setBackground(Color.GRAY);
		this.add(chatPane_1);
		this.add(following);
		this.add(chatField);
		this.add(buttonPanel);
	}
	
	
	
	private void setupScrollPane()
	{
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		
		chatPane_1.setViewportView(chatArea);
		chatPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		fArea.setEditable(false);
		fArea.setLineWrap(false);
		fArea.setWrapStyleWord(false);
		
		fArea = new JTextArea("Your Followers", 10, 20);
		fArea.setBackground(Color.DARK_GRAY);
		fArea.setForeground(Color.CYAN);
		fArea.setEditable(false);
		chatPane_1.setRowHeaderView(fArea);
		following.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		following.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
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
		
		
		chatField = new JTextField("Talk to the bot here", 50);
		chatField.setForeground(Color.GREEN);
		chatField.setBackground(Color.DARK_GRAY);
		appLayout_1.putConstraint(SpringLayout.NORTH, buttonPanel, 60, SpringLayout.SOUTH, chatField);
		
		chatArea = new JTextArea("Chat Area", 20, 30);
		chatArea.setForeground(Color.GREEN);
		chatArea.setBackground(Color.DARK_GRAY);
		
		following = new JScrollPane();
		
		chatPane_1 = new JScrollPane();
		appLayout_1.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, chatPane_1);
		appLayout_1.putConstraint(SpringLayout.EAST, buttonPanel, 0, SpringLayout.EAST, chatPane_1);
		appLayout_1.putConstraint(SpringLayout.NORTH, chatField, 30, SpringLayout.SOUTH, chatPane_1);
		appLayout_1.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatPane_1);
		appLayout_1.putConstraint(SpringLayout.EAST, chatField, 0, SpringLayout.EAST, chatPane_1);
		appLayout_1.putConstraint(SpringLayout.NORTH, chatPane_1, 50, SpringLayout.NORTH, this);
		appLayout_1.putConstraint(SpringLayout.WEST, chatPane_1, 50, SpringLayout.WEST, this);
		appLayout_1.putConstraint(SpringLayout.EAST, chatPane_1, -50, SpringLayout.EAST, this);
	}
	
}