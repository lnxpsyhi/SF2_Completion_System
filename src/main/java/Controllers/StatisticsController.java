package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class StatisticsController implements Initializable {
	
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


    @FXML
    private CategoryAxis x;
    
    @FXML
    private NumberAxis y;
    
    @FXML
    private LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("1", 23));
        series.getData().add(new XYChart.Data<>("2", 21));
        series.getData().add(new XYChart.Data<>("3", 24));
        series.getData().add(new XYChart.Data<>("4", 14));
        series.getData().add(new XYChart.Data<>("5", 19));
        series.getData().add(new XYChart.Data<>("5", 19));
        series.getData().add(new XYChart.Data<>("52", 129));
        
        series.getData().add(new XYChart.Data<>("53", 19));
        
        lineChart.getData().add(series);
    }
}
