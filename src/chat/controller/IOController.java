package chat.controller;

import java.util.Calendar;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

/**
 * This class is to handle all the file jazz for saving and loading
 * @author jjud0535
 *
 */

public class IOController 
{
	
	/**
	 * save the text that is in the chat field to a binary file output
	 * @param appController
	 * @param path
	 * @param textToSave
	 */
	
	public static void saveText(ChatController appController, String path, String textToSave)
	{
		try
		{
			// Names the file that contains the Chatbot text with the current date
			String filename = path;
			Calendar date = Calendar.getInstance();
			filename += "/" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH);
			filename += " at " + date.get(Calendar.HOUR) + "|" + date.get(Calendar.MINUTE) + " o'clock";
			filename += " chat save.txt";
			// Physically saves the file
			File saveFile = new File(filename);
			Scanner textScanner = new Scanner(textToSave);
			PrintWriter saveText = new PrintWriter(saveFile);
			// While there is text, the application will read and save it
			while(textScanner.hasNext())
			{
				String currentLine = textScanner.nextLine();
				saveText.println(currentLine);
			}
			
			textScanner.close();
			saveText.close();
			
		}
		catch(IOException error)
		{
			appController.handleErrors(error);
		}
		catch(Exception genericError)
		{
			appController.handleErrors(genericError);
		}
	}
	
	/**
	 * load a past saved file from your system
	 * @param appController
	 * @param path
	 * @return
	 */
	
	public static String loadFile(ChatController appController, String path)
	{
		String contents = "";
		
		try
		{
			File saveFile = new File(path);
			Scanner fileScanner;
			if(saveFile.exists())
			{
				fileScanner = new Scanner(saveFile);
				while(fileScanner.hasNext())
				{
					contents += fileScanner.nextLine() + "\n";
				}
				fileScanner.close();
			}
		}
		catch(IOException error)
		{
			appController.handleErrors(error);
		}
		catch(Exception genericError)
		{
			appController.handleErrors(genericError);
		}
		return contents;
	}
	
	
}
