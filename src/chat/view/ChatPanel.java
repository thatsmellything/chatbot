package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				
			}
		});
		myButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
		myButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Click)
			{
				
			}
		});
	}
}
