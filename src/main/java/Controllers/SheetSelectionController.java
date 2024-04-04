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

    @FXML
    private VBox sheetButtonsContainer; 

    Parent root;
    Stage stage;
    Scene scene;
    
    public void goBack(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/FileSelection.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String filePath = "/home/akira/Downloads/School-Forms-1-7.xlsx";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                String sheetName = workbook.getSheetName(i);
                addButtonForSheet(sheetName);
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addButtonForSheet(String sheetName) {
        Button button = new Button(sheetName);
        button.setOnAction(event -> {
            
            System.out.println("Button for sheet '" + sheetName + "' clicked!");
        });
        sheetButtonsContainer.getChildren().add(button); 
    }
}
