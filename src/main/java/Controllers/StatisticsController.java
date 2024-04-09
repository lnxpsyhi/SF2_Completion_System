package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class StatisticsController implements Initializable {
	
	NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    
    @FXML
    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
	
    @FXML
    private Label enrolment;

    @FXML
    private Label lateEnrolment;

    @FXML
    private Label registeredLearners;

    @FXML
    private Label percentageEnrolment;

    @FXML
    private Label averageDaily;

    @FXML
    private Label percentageAttendance;

    @FXML
    private Label fiveConsecutiveDays;

    public void setEnrolment(String enrolment) {
        this.enrolment.setText(enrolment);
    }

    public void setLateEnrolment(String lateEnrolment) {
        this.lateEnrolment.setText(lateEnrolment);
    }

    public void setRegisteredLearners(String registeredLearners) {
        this.registeredLearners.setText(registeredLearners);
    }

    public void setPercentageEnrolment(String percentageEnrolment) {
        this.percentageEnrolment.setText(percentageEnrolment);
    }

    public void setAverageDaily(String averageDaily) {
        this.averageDaily.setText(averageDaily);
    }

    public void setPercentageAttendance(String percentageAttendance) {
        this.percentageAttendance.setText(percentageAttendance);
    }

    public void setFiveConsecutiveDays(String fiveConsecutiveDays) {
        this.fiveConsecutiveDays.setText(fiveConsecutiveDays);
    }
  
    public void setChart() {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName("Attendance per Day");
		
		chart.getData().add(series);
	}
}
