package Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class RunAutomationController implements Initializable {
	
	
	private String dateCoordinates;
	private String apCoordinates;
	private String statisticsCoordinates;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	private String filePath;
	private String sheetName;
	
	public void setDateCoordinates(String dateCoordinates) {
		this.dateCoordinates = dateCoordinates;
	}
	
	public void setAPCoordinates(String apCoordinates) {
		this.apCoordinates = apCoordinates;
	}
	public String getDateCoordinates() {
		return dateCoordinates;
	}

	public void setStatisticsCoordinates(String statsCoordinates) {
		this.statisticsCoordinates = statsCoordinates;
	}
	public String getAPCoordinates() {
		return apCoordinates;
	}

	public String getStatisticsCoordinates() {
		return statisticsCoordinates;
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
		System.out.println("Date Coordinates: " + getDateCoordinates());
		System.out.println("Absences and Presences Coordinates: " + getAPCoordinates());
		System.out.println("Statistcs Coordinates: " + getStatisticsCoordinates());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadProperties();
	}

    private void loadProperties() {
        Properties prop = new Properties();
        try (InputStream istream = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(istream);
            dateCoordinates = prop.getProperty("dateCoordinates");
            apCoordinates = prop.getProperty("absencesPresencesCoordinates");
            statisticsCoordinates = prop.getProperty("statisticsCoordinates");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	


	
}
