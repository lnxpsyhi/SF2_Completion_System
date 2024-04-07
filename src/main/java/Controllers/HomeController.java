package Controllers;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HomeController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Alert alert = new Alert(AlertType.WARNING);

	Properties prop;

	private boolean isConfigured() throws IOException {
	    prop = new Properties();
	    InputStream istream = new FileInputStream("src/main/resources/config.properties");
	    prop.load(istream);
	    istream.close(); 

	   
	    return prop.getProperty("dateCoordinates").isEmpty() || 
	           prop.getProperty("absencesPresencesCoordinates").isEmpty() || 
	           prop.getProperty("statisticsCoordinates").isEmpty();
	}
	
	public void goToFileSelection(ActionEvent event) throws IOException {
	    if (!isConfigured()) {
	        root = FXMLLoader.load(getClass().getResource("/fxml/FileSelection.fxml"));
	        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    } else {
	        alert.setHeaderText("Configuration Alert");
	        alert.setContentText("Please configure your settings before proceeding.");
	        alert.showAndWait();
	    }
	}
	
	public void goToAboutSection(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
