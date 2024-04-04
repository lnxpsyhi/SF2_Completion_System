package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import methods.CountOverall;

import validations.FileValidation;

public class HomeController implements Initializable {
	
//	private FileChooser fc = new FileChooser();
//	private FileValidation fv = new FileValidation();
//	private Alert alert = null;
//	
//	@FXML
//	private void genSheetBtns() {
//		File file = chooseFile();
//		String[] exten = file.getName().split(".");
//		switch (exten[exten.length]) {
//		case null:
//			alert.setAlertType(AlertType.ERROR);
//			alert.setHeaderText("The file is null");
//			alert.setContentText("Please make sure the selected file exist");
//		default:
//			break;
//			
//		}
//	}
//	
//	private File chooseFile() {
//		return fv.isExcel(fc.showOpenDialog(null));
//	}

	boolean configured = true;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Alert alert = new Alert(AlertType.WARNING);
	
	public void goToFileSelection(ActionEvent event) throws IOException {
		if (configured) {
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
