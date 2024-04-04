package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FileSelectionController implements Initializable {

	Parent root;
	Stage stage;
	Scene scene;
	
	public void goBack(MouseEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
	}
	
	public void goToSheetSelection(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/SheetSelection.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}
}
