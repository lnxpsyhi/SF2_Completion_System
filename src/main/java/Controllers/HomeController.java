package Controllers;

<<<<<<< HEAD
=======
import java.io.InputStream;
>>>>>>> f76455fb257ac1d987144bea48b9662c15916269
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
<<<<<<< HEAD
	
	@FXML
	private void close() {
		System.exit(0);
	}
	
	
	// handle configuration
	@FXML
	private void config() {
		Properties prop = new Properties();
		try (InputStream istream = new FileInputStream("src/main/resources/config.properties")) {
			prop.load(istream);
			String coor = prop.getProperty("coordinates");
			String dateCoor = prop.getProperty("dateCoordinates");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/RunAutomation.fxml"));
				RunAutomationController cont = loader.getController();
				cont.setCoor(coor);
				cont.setDateCoor(dateCoor);

			} catch (NullPointerException npe) {
				alert.setAlertType(AlertType.ERROR);
				alert.setHeaderText("Not Configured Properly");
				alert.setContentText("Please make sure that all of the properties are configured properly.");
				alert.showAndWait();
				configured = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
=======

	Properties prop;

	private boolean isConfigured() throws IOException {
	    prop = new Properties();
	    InputStream istream = new FileInputStream("src/main/resources/config.properties");
	    prop.load(istream);
	    istream.close(); 

	   
	    return prop.getProperty("dateCoordinates").isEmpty() || 
	           prop.getProperty("absencesPresencesCoordinates").isEmpty() || 
	           prop.getProperty("statisticsCoordinates").isEmpty();
>>>>>>> f76455fb257ac1d987144bea48b9662c15916269
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
