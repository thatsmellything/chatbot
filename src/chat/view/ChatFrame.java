package chat.view;
import javax.swing.JFrame;
import chat.controller.ChatController;
public class ChatFrame extends JFrame
{

	private GUIController appController;
	private Panel appPanel;
	
	public Frame(GUIController appCOntroller)
	{
		super();
		this.appController = appController;
		this.appPanel = new Panel(appController);
		setupFrame();
		
	}
	
	private void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setTitle("Chatbot GUI");
		this.setSize(800, 800);
		this.setResizable(true);
		this.setVisible(true);
	}
}
