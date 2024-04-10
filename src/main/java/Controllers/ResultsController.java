package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ResultsController implements Initializable {

	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	ScrollPane scrollPane1 = new ScrollPane();
	
	@FXML
	ScrollPane scrollPane2 = new ScrollPane();
	
	private int enrolment = 0;
	private int lateEnrolment = 0;
	private int registeredLearners = 0;
	private int percentageEnrolment = 0;
	private int averageDaily = 0;
	private int percentageAttendance = 0;
	private int fiveConsecutiveDays = 0;
	
	private ArrayList<Integer> girlsTotalPerDay = new ArrayList<Integer>();
	public ArrayList<Integer> getGirlsTotalPerDay() {
		return girlsTotalPerDay;
	}
	public void setGirlsTotalPerDay(int girls) {
		girlsTotalPerDay.add(girls);
	}
	private ArrayList<Integer> boysTotalPerDay = new ArrayList<Integer>();
	public ArrayList<Integer> getBoysTotalPerDay() {
		return boysTotalPerDay;
	}
	public void setBoysTotalPerDay(int boys) {
		boysTotalPerDay.add(boys);
	}
	private ArrayList<Integer> combinedTotalPerDay = new ArrayList<Integer>(); 
	public ArrayList<Integer> getCombinedTotalPerDay() {
		return combinedTotalPerDay;
	}
	public void setCombinedTotalPerDay(int value) {
			combinedTotalPerDay.add(value);
	}
	private ArrayList<Integer> dates = new ArrayList<Integer>();
	
	 	public ArrayList<Integer> getDates() {
		 	return dates;
	 	 }
	 	public void setDates(int date) {
		 	dates.add(date);
	     }
	 	public void setEnrolment(int enrolment) {
	        this.enrolment = enrolment;
	    }

	    public void setLateEnrolment(int lateEnrolment) {
	        this.lateEnrolment = lateEnrolment;
	    }

	    public void setRegisteredLearners(int registeredLearners) {
	        this.registeredLearners = registeredLearners;
	    }

	    public void setPercentageEnrolment(int percentageEnrolment) {
	        this.percentageEnrolment = percentageEnrolment;
	    }

	    public void setAverageDaily(int averageDaily) {
	        this.averageDaily = averageDaily;
	    }

	    public void setPercentageAttendance(int percentageAttendance) {
	        this.percentageAttendance = percentageAttendance;
	    }

	    public void setFiveConsecutiveDays(int fiveConsecutiveDays) {
	        this.fiveConsecutiveDays = fiveConsecutiveDays;
	    }

	    // Getters
	    public int getEnrolment() {
	        return enrolment;
	    }

	    public int getLateEnrolment() {
	        return lateEnrolment;
	    }

	    public int getRegisteredLearners() {
	        return registeredLearners;
	    }

	    public int getPercentageEnrolment() {
	        return percentageEnrolment;
	    }

	    public int getAverageDaily() {
	        return averageDaily;
	    }

	    public int getPercentageAttendance() {
	        return percentageAttendance;
	    }

	    public int getFiveConsecutiveDays() {
	        return fiveConsecutiveDays;
	    }

	    
    @FXML
    private Label numberOfClasses;

    @FXML
    private Label totalBoys;

    @FXML
    private Label totalAbsencesBoys;

    @FXML
    private Label totalPresencesBoys;

    @FXML
    private Label totalGirls;

    @FXML
    private Label totalAbsencesGirls;

    @FXML
    private Label totalPresencesGirls;

    @FXML
    private Label overallAbsences;

    @FXML
    private Label overallPresences;

    @FXML
    private Label mostAbsences;

    @FXML
    private Label perfectAttendance;


    public void setNumberOfClasses(int numberOfClassesValue) {
        numberOfClasses.setText(Integer.toString(numberOfClassesValue));
    }

    public void setTotalBoys(int totalBoysValue) {
        totalBoys.setText(Integer.toString(totalBoysValue));
    }

    public void setTotalAbsencesBoys(int totalAbsencesBoysValue) {
        totalAbsencesBoys.setText(Integer.toString(totalAbsencesBoysValue));
    }

    public void setTotalPresencesBoys(int totalPresencesBoysValue) {
        totalPresencesBoys.setText(Integer.toString(totalPresencesBoysValue));
    }

    public void setTotalGirls(int totalGirlsValue) {
        totalGirls.setText(Integer.toString(totalGirlsValue));
    }

    public void setTotalAbsencesGirls(int totalAbsencesGirlsValue) {
        totalAbsencesGirls.setText(Integer.toString(totalAbsencesGirlsValue));
    }

    public void setTotalPresencesGirls(int totalPresencesGirlsValue) {
        totalPresencesGirls.setText(Integer.toString(totalPresencesGirlsValue));
    }

    public void setOverallAbsences(int overallAbsencesValue) {
        overallAbsences.setText(Integer.toString(overallAbsencesValue));
    }

    public void setOverallPresences(int overallPresencesValue) {
        overallPresences.setText(Integer.toString(overallPresencesValue));
    }

    public void setMostAbsences(String mostAbsencesValue) {
        mostAbsences.setText(mostAbsencesValue);
    }

    public void setPerfectAttendance(String perfectAttendanceValue) {
        perfectAttendance.setText(perfectAttendanceValue);
    }

    public void goToStatistics(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Statistics.fxml"));
    	root = loader.load();
    	StatisticsController controller = loader.getController();
    	
    	controller.setEnrolment(Integer.toString(getEnrolment()));
    	controller.setLateEnrolment(Integer.toString(getLateEnrolment()));
    	controller.setRegisteredLearners(Integer.toString(getRegisteredLearners()));
    	controller.setPercentageEnrolment(Integer.toString(getPercentageEnrolment()) + "%");
    	controller.setAverageDaily(Integer.toString(getAverageDaily()) + "%");
    	controller.setPercentageAttendance(Integer.toString(getPercentageAttendance()) + "%");
    	controller.setFiveConsecutiveDays(Integer.toString(getFiveConsecutiveDays()));

    
    	controller.setChart(getDates(), getCombinedTotalPerDay(), getBoysTotalPerDay(), getGirlsTotalPerDay());
    	    
    

    	   
    	
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);;

	}



}
