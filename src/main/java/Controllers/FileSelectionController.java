package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import validations.FileValidation;

public class FileSelectionController implements Initializable {
	
	private FileChooser fileChooser = new FileChooser();
	private Alert alert = null;
	private FileValidation fv = new FileValidation();
	private File f;
	private String filePath = "";
	private boolean proceedButtonGenerated = false;
	
	RunAutomationController ra = new RunAutomationController();
	@FXML
	private TextField filePathField;
	
    @FXML
    private FlowPane btncontainer1;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@FXML
	private void chooseFile() throws IOException {
		Button button = null;
		    f = fileChooser.showOpenDialog(null);

		    if (f != null) {	    	
			  if (fv.isExcel(f)) {
				  
			      filePathField.setText(f.getAbsolutePath());
			      System.out.println(f.getAbsolutePath());
		    } else {
		        alert = new Alert(Alert.AlertType.ERROR, "Please select a valid excel file.");
		        alert.setHeaderText("Invalid File!");
		        alert.showAndWait();
		    }
		    } else {
		        System.out.println("Canceled");
		    }
	}
	
	public void goBack(MouseEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
	}
	
	public void goToSheetSelection(ActionEvent event) throws IOException {
	    if (filePathField.getText() != "") {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SheetSelection.fxml"));
		    root = loader.load(); 
		    SheetSelectionController controller = loader.getController(); 
			    
		    controller.setFilePath(filePathField.getText());
		    controller.generateSheets(filePathField.getText());
		    
		    stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
	    } else {
	        alert = new Alert(Alert.AlertType.ERROR, "Please select a valid excel file.");
	        alert.setHeaderText("No file path selected");
	        alert.showAndWait();
	    }

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ExtensionFilter excelFilter = new ExtensionFilter("Excel", "*.xls", "*.xlsx");
		ExtensionFilter all = new ExtensionFilter("All", "*");
		fileChooser.getExtensionFilters().add(excelFilter);
		fileChooser.getExtensionFilters().add(all);
	}

}
