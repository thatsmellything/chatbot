package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chat.controller.IOController;

public class ChatPanel extends JPanel
{
	private ChatController appController;
	private SpringLayout appLayout;
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton resetButton;
	private JButton tweetButton;
	private JButton searchTwitterButton;
	private JTextField chatField;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	private JPanel buttonPanel;
	
	public ChatPanel(ChatController appController)
	{
		super();
		this.appController = appController;
		// Spring Layout
		appLayout = new SpringLayout();
		
		// Buttons
		chatButton = new JButton("Chat");
		
		checkerButton = new JButton("Check Text");
		appLayout.putConstraint(SpringLayout.NORTH, chatButton, 0, SpringLayout.NORTH, checkerButton);
		appLayout.putConstraint(SpringLayout.WEST, chatButton, 28, SpringLayout.EAST, checkerButton);
		appLayout.putConstraint(SpringLayout.EAST, checkerButton, -498, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, checkerButton, -39, SpringLayout.SOUTH, this);
		
		loadButton = new JButton("Load");
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, 0, SpringLayout.SOUTH, loadButton);
		appLayout.putConstraint(SpringLayout.SOUTH, loadButton, -207, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, loadButton, -768, SpringLayout.EAST, this);
		
		saveButton = new JButton("Save");
		appLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		appLayout.putConstraint(SpringLayout.EAST, saveButton, -874, SpringLayout.EAST, this);
		// saveButton
		appLayout.putConstraint(SpringLayout.SOUTH, saveButton, -207, SpringLayout.SOUTH, this);
		
		resetButton = new JButton("Reset");
		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 356, SpringLayout.EAST, resetButton);
		appLayout.putConstraint(SpringLayout.SOUTH, resetButton, -207, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, resetButton, -662, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, resetButton, 6, SpringLayout.EAST, loadButton);
		
		// Text Input/Output
		chatField = new JTextField("User Input", 50);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, 0, SpringLayout.EAST, chatField);
		appLayout.putConstraint(SpringLayout.NORTH, resetButton, 56, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.NORTH, loadButton, 56, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.NORTH, checkerButton, 56, SpringLayout.SOUTH, chatField);
		
		chatArea = new JTextArea("Bot Output", 20, 50);
		
		chatPane = new JScrollPane();
		
		buttonPanel = new JPanel(new GridLayout(1,0));
		
		setupButtons();
		setupPanel();
		setupLayout();
		setupListeners();
		setupScrollPane();
	}
	
	private void setupButtons()
	{
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(chatButton);
		buttonPanel.add(checkerButton);
		buttonPanel.add(tweetButton);
		buttonPanel.add(searchTwitterButton);
	}
	
	
	private void setupPanel()
	{
		this.setLayout(appLayout);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setBackground(Color.MAGENTA);
		this.add(chatButton);
		this.add(checkerButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(resetButton);
		this.add(chatField);
		this.add(chatPane);
		buttonPanel.setPreferredSize(new Dimension(900, 150));
		buttonPanel.setBackground(Color.CYAN);
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
			// the 'this' parameter tells the fileChooser window to pop in the ChatPanel
			result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				// Absolute path is the exact address
				path = fileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		
		return path;
	}
	
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, saveButton, 56, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, chatField);
		// chatField
		appLayout.putConstraint(SpringLayout.NORTH, chatField, 30, SpringLayout.SOUTH, chatPane);
		appLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatPane);
		appLayout.putConstraint(SpringLayout.EAST, chatField, 0, SpringLayout.EAST, chatPane);
		// chatPane
		appLayout.putConstraint(SpringLayout.NORTH, chatPane, 50, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatPane, 50, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatPane, -50, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				chatArea.setText("");
				String userText = chatField.getText();
				String response = "";
				response = appController.interactWithChatbot(userText);
				chatArea.append(response);
				chatArea.setCaretPosition(chatArea.getDocument().getLength());
				chatField.setText("");
			}
		});
		
		checkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String path = getPath("load");
				String chatText = IOController.loadFile(appController, path);
				chatArea.setText("Chat loaded! \n" + chatText);
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String chatText = chatArea.getText();
				String path = "./Chats";
				IOController.saveText(appController, path, chatText);
				chatArea.setText("Chat saved!");
			}
		});
		
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				chatArea.setText("");
			}
		});
	}
	
	private void setupScrollPane()
	{
		// Installs the text area
		chatArea.setEditable(false); // Disallows user to edit bot output text
		chatArea.setLineWrap(true); // Allows for a new line to start once the end of chatArea has been reached
		chatArea.setWrapStyleWord(true); // Only allows for wrapping of words
		
		// Clarifies the policies for scroll bars in the bot output field
		chatPane.setViewportView(chatArea); // Puts chatArea in chatPane
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // No horizontal scroll bar - we have wrapping
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
}
