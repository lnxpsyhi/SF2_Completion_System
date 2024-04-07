package Controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class RunAutomationController {
	
	
	private String coordinates;
	private String dateCoordinates;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	private String filePath;
	private String sheetName;
	
	public void setDateCoor(String dateCoordinates) {
		this.setDateCoordinates(dateCoordinates);
	}
	
	public void setCoor(String coordinates) {
		this.setCoordinates(coordinates);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
	    this.filePath = filePath;
	}
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public void goBack(MouseEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SheetSelection.fxml"));
	    root = loader.load(); 
	     
	    SheetSelectionController controller = loader.getController(); 
	    
	    
	    controller.setFilePath(getFilePath());
	    controller.generateSheets(getFilePath());
	    
	    stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void runAutomation() {
		System.out.println(getFilePath() + " : " + getSheetName());
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getDateCoordinates() {
		return dateCoordinates;
	}

	public void setDateCoordinates(String dateCoordinates) {
		this.dateCoordinates = dateCoordinates;
	}

	


	
}
