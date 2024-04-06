package Controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AboutController {

	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void goToAboutToolsSection(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/AboutTools.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();

	}
	
	public void goBack(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();

	}
	
	public void goBackToAboutSection(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();

	}
}
