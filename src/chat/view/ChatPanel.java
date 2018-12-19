package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.IOController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chat,controller.IOController;

public class ChatPanel extends JPanel
{

	private GUIController appController;
	private SpringLayout appLayout;
	
	public ChatPanel(GUIController appController)
	{
		super();
		this.appController = appController;
		appLayout = new appLayout();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(appLayout);
		this.add(chatButton);
		
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
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
		checkerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				String chatText = chatArea.getText();
				String path = getPath("save");
				IOController.saveText(appController,  path,  chatText);
				chatArea.setText("Chat saved!");
			}
		});
		loadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				String path = getPath("load");
				String chatText = IOController.loadFile(appController, path);
				chatArea.setText(chatText)
			}
		});
		myButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
	}
	
	
}
