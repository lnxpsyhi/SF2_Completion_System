package Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import methods.CountBoys;
import methods.CountDates;
import methods.CountGirls;
import methods.CountOverall;
import methods.CountStatistics;
import methods.CountStudents;

public class RunAutomationController implements Initializable {

	private String dateCoordinates;
	private String apCoordinates;
	private String statisticsCoordinates;

	private Parent root;
	private Stage stage;
	private Scene scene;
	private String filePath;
	private String sheetName;
	
	CountDates cd = new CountDates();
	CountStudents cs = new CountStudents();
	CountBoys cb = new CountBoys();
	CountGirls cg = new CountGirls();
	CountOverall co = new CountOverall();
	CountStatistics cstats = new CountStatistics();
	
	private Alert alert = new Alert(AlertType.INFORMATION);
	

	
	public void setDateCoor(String dateCoordinates) {
		this.setDateCoordinates(dateCoordinates);
	}

	public void setCoor(String coordinates) {
		this.setCoor(coordinates);
	}

	@FXML
	private Label filePathText;

	@FXML
	private Label sheetNameText;

	@FXML
	private Label dateCoorText;

	@FXML
	private Label apCoorText;

	@FXML
	private Label statsCoorText;

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
		filePathText.setText(filePath);
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		sheetNameText.setText(sheetName);
		this.sheetName = sheetName;
	}

	public void goBack(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SheetSelection.fxml"));
		root = loader.load();

		SheetSelectionController controller = loader.getController();

		controller.setFilePath(getFilePath());
		controller.generateSheets(getFilePath());

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void runAutomation(ActionEvent event) throws IOException {

		runAllMethods(getFilePath(), getSheetName(), getDateCoordinates(), getAPCoordinates(), getStatisticsCoordinates(), event);	

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

			dateCoorText.setText(getDateCoordinates());
			apCoorText.setText(getAPCoordinates());
			statsCoorText.setText(getStatisticsCoordinates());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runAllMethods(String PATH, String sheetName, String dateCoordinates, String apCoordinates, String statsCoordinates, ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Results.fxml"));
		root = loader.load();
		
		ResultsController controller = loader.getController();

		
		cd.countDates(PATH, dateCoordinates, sheetName);
		cs.countStudents(PATH, apCoordinates, sheetName);
		cb.countBoys(PATH, apCoordinates, dateCoordinates, sheetName);
		cg.countGirls(PATH, apCoordinates, dateCoordinates, sheetName);
		co.countOverallTotalAbsences(PATH, apCoordinates, dateCoordinates, sheetName);
		cstats.countStatistics(PATH, apCoordinates, dateCoordinates, sheetName, statsCoordinates);
		alert.setHeaderText("Done Calculating!");
		alert.setContentText("Your file has been updated");
		alert.showAndWait();
		
		controller.setNumberOfClasses(cd.getNumberOfDates());
		controller.setTotalBoys(cs.getBoysNumber());
		controller.setTotalAbsencesBoys(cb.getBoysTotalAbsences());
		controller.setTotalPresencesBoys(cb.getBoysTotalPresences());
		controller.setTotalGirls(cs.getGirlsNumber());
		controller.setTotalAbsencesGirls(cg.getGirlsTotalAbsences());
		controller.setTotalPresencesGirls(cg.getGirlsTotalPresences());
		controller.setOverallAbsences(co.getOverallAbsences());
		controller.setOverallPresences(co.getOverallPresences());
		StringBuilder mostAbsencesBuilder = new StringBuilder();
		StringBuilder perfectAttendanceBuilder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : co.getMostAbsencesOverall().entrySet()) {
		    String name = entry.getKey();
		    int absences = entry.getValue();
		    mostAbsencesBuilder.append(name).append(": ").append(absences).append("\n");
		}
		
		for (String name : co.getPerfectAttendanceOverall()) {
		    perfectAttendanceBuilder.append(name).append("\n");
		    System.out.println(perfectAttendanceBuilder.toString());
		}
		controller.setMostAbsences(mostAbsencesBuilder.toString());
		controller.setPerfectAttendance(perfectAttendanceBuilder.toString());
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
