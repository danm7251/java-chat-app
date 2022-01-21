package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));//Creates a root node for the scene from the fxml file
			Scene scene = new Scene(root);		//Creates a new scene from root and width and height
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("C:\\Users\\44773\\OneDrive\\Desktop\\Computing\\Java\\Eclipse\\Server\\src\\application\\icon.png");//Stores icon.png in a variable
			
			primaryStage.getIcons().add(icon);			//Adds icon to the stage
			primaryStage.setTitle("Server"); 			//Sets stage title to Server
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
