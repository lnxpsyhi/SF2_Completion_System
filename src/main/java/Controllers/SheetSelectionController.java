package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class SheetSelectionController implements Initializable {
	
	private String filePath = "";
	private String sheetName = "";
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePath() {
		return filePath;
	}
    @FXML
    private VBox sheetButtonsContainer; 

    public void setSheetName(String sheetName) {
    	this.sheetName = sheetName;
    }
    public String getSheetName() {
    	return sheetName;
    }
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Button button;
  
    public void goBack(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/FileSelection.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateSheets(getFilePath());
    }

    public void generateSheets(String filePath) {
    	try {
            if (filePath != null && !filePath.isEmpty()) {
                FileInputStream fis = new FileInputStream(getFilePath());
                Workbook workbook = WorkbookFactory.create(fis);

                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    String sheetName = workbook.getSheetName(i);
                    addButtonForSheet(sheetName);
                }
   
                workbook.close();
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addButtonForSheet(String sheetName)  {
        button = new Button(sheetName);
        button.setOnAction(event -> {
        
        setSheetName(sheetName);
        System.out.println(getSheetName());
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RunAutomation.fxml"));
			root = loader.load();
			RunAutomationController controller = loader.getController(); 
            controller.setFilePath(getFilePath());
            controller.setSheetName(getSheetName());
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        });
        sheetButtonsContainer.getChildren().add(button); 
    }
    
    
    
}
