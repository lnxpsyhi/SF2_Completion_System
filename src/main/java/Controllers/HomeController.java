package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	private Properties props = new Properties();
	private static final String CONFIG_PATH = "src/main/resources/config.properties";

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Alert alert = new Alert(AlertType.WARNING);

	public void goToDateConfiguration(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateConfiguration.fxml"));
		root = loader.load();
		
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private boolean isConfigured(Properties props) throws IOException {
		
			String dateCoor = props.getProperty("dateCoordinates");
			String absencesPresencesCoor = props.getProperty("absencesPresencesCoordinates");
			String statisticsCoor = props.getProperty("statisticsCoordinates");
			
			boolean hasNull = dateCoor == null || absencesPresencesCoor == null || statisticsCoor == null;
			
	
			if (hasNull) {
				alert.setHeaderText("Missing Properties!");
				alert.setContentText("Has missing properties.");
				alert.showAndWait();
				return false;
			} else if (dateCoor.isBlank() | absencesPresencesCoor.isBlank() | statisticsCoor.isBlank()) {
				alert.setHeaderText("Missing Value!");
				alert.setContentText("Has missing properties value.");
				alert.showAndWait();
				return false;
			} else {
				return true;
			}
	}

	public void goToFileSelection(ActionEvent event) throws IOException {
		if (isConfigured(props)) {
			root = FXMLLoader.load(getClass().getResource("/fxml/FileSelection.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	// About
	public void goToAboutSection(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		// load configuration properties on start
		try (InputStream istream = new FileInputStream(CONFIG_PATH)) {
			props.load(istream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
