package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller
{


	private String state = "setup"; //Determines state of GUI
	@FXML							//Defining object IDs
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private TextField textfield1;
	@FXML
	private Button button1;

	
	
	public void buttonClicked(ActionEvent e)	//Toggles state and decides whether to call startServer or stopServer
	{ 	
		label3.setText("");
		String sPort = textfield1.getText();
		String validity = processPort(sPort);
		
		if(state=="setup")
		{
			if(validity=="valid")
			{
				try
				{
					label2.setText(sPort);
					startServer(Integer.parseInt(sPort));
					state="running";
				}
				catch(Exception ex)
				{
					label3.setLayoutX(87);
					label3.setText("Error starting Server");
				} 												
			}
			else if(validity=="not an integer")
			{
				label3.setLayoutX(78);
				label3.setText("Port must be a number");
			}
			else if(validity=="out of range")
			{
				label3.setLayoutX(29);
				label3.setText("Port must be between 1024 and 65535");
			}
			else if(validity=="unknown")
			{
				label3.setLayoutX(88);
				label3.setText("Invalid port number");
			}
			else
			{
				label3.setLayoutX(101);
				label3.setText("Validation error");
			}
		}
		else if(state=="running")
		{
			try
			{
				stopServer();
				state="setup";
			}
			catch(Exception ex) 
			{
				label3.setLayoutX(83);
				label3.setText("Error stopping Server");
			}
		}
	}
	
	

	public void startServer(int port)
	{
		label1.setText("Running Server");
		label1.setLayoutX(75);							  //Aligns new text to center
		button1.setText("Stop Server");
		
		textfield1.setDisable(true);					  //Disables and hides textfield
		textfield1.setVisible(false);
		label2.setVisible(true);						  //Replaces with label
	}
	
	
	public void stopServer()
	{
		label1.setText("Server Setup");
		label1.setLayoutX(88);							  //Aligns new text to center
		button1.setText("Start Server");

		textfield1.setDisable(false);					  //Enables and hides textfield
		textfield1.setVisible(true);
		label2.setVisible(false);						  //Removes label
	}

	
	public String processPort(String sPort) 
	{
		boolean isInt = sPort.matches("-?\\d+");
		String result;
		
		if(isInt)
		{
			try {
				int iPort = Integer.parseInt(sPort);
				if((iPort>1024)&&(iPort<=65535)) 
				{
					result = "valid";
				}
				else if((iPort<=1024)||(iPort>65535))
				{
					result = "out of range";
				}
				else
				{
					result = "unknown";
				}
			} 
			catch(Exception ex) 
			{
				result = "unknown";
			}
		} 
		else
		{
			result = "not an integer";
		}
		
		return result;
	}
}