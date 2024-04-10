package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import custexception.ConfigurationException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	private Properties props = new Properties();
	private static final String CONFIG_PATH = "src/main/resources/config.properties";

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Alert alert = new Alert(AlertType.WARNING);

	// modify configuration properties
	
	/*
	
	@FXML
	private void configBtnOnClick() {
		
		
		
		// dateCoordinates=K16:BH16
		// absencesPresencesCoordinates=A18:BO78
		// statisticsCoordinates=BU81:CA97
		
		Dialog<Map<String, String>> dialog = new Dialog<>();
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		DialogPane dialogp = dialog.getDialogPane();
		
		// Pane
	    GridPane gridp = new GridPane();
	    gridp.setHgap(10);
        gridp.setVgap(10);
		
        // Text Fields
		TextField dateCoorTf = new TextField();
		dateCoorTf.setPromptText("Date Coordinates");
		
		TextField apCoorTf = new TextField();
		apCoorTf.setPromptText("Absences and Presences Coordinates");
		
		TextField statCoorTf = new TextField();
		statCoorTf.setPromptText("statistics Coordinates");
  		
		gridp.add(dateCoorTf, 0, 0);
		gridp.add(apCoorTf, 0, 1);
		gridp.add(statCoorTf, 0, 2);
		
		Platform.runLater(dateCoorTf::requestFocus);
		
		dialogp.setContent(gridp);
		
		dialog.setResultConverter(dialogBtn -> {
			if (dialogBtn == ButtonType.APPLY) {
				Map<String, String> keyVal = new HashMap<>();
				keyVal.put("dateCoordinates", dateCoorTf.getText());
				keyVal.put("absencesPresencesCoordinates", apCoorTf.getText());
				keyVal.put("statisticsCoordinates", statCoorTf.getText());
				return keyVal;
			}
			return null;
		});
		dialog.showAndWait().ifPresent(result -> {
			props.putIfAbsent("dateCoordinates", result.get("dateCoordinates"));
			props.putIfAbsent("absencesPresencesCoordinates", result.get("absencesPresencesCoordinates"));
			props.putIfAbsent("statisticsCoordinates", result.get("statisticsCoordinates"));
			try (OutputStream ostream = new FileOutputStream(CONFIG_PATH)) {
				props.store(ostream, "Warning! This is the configuration file. Do not delete it!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	*/
	
	public void goToDateConfiguration(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateConfiguration.fxml"));
		root = loader.load();
		DateConfigurationController controller = loader.getController();
		
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	// check if the properties are configured properly
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
		try (InputStream istream = new FileInputStream("src/main/resources/config.properties")) {
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
