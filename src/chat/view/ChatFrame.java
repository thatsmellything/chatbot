package chat.view;
import javax.swing.JFrame;
import chat.controller.ChatController;
public class ChatFrame extends JFrame
{

	private ChatController app;
	private ChatPanel appPanel;
	
	public ChatFrame(ChatController appCOntroller)
	{
		super();
		this.app = app;
		this.appPanel = new ChatPanel(app);
		setupFrame();
		
	}
	
	private void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setTitle("Chatbot GUI and Twitter 3.0");
		this.setSize(800, 800);
		this.setResizable(false);
		this.setVisible(true);
	}
}
