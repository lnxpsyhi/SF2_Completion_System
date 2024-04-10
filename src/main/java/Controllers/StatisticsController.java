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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {

	private Parent root;
	private Stage stage;
	private Scene scene;
	
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

    @SuppressWarnings("unchecked")
	public void setChart(ArrayList<Integer> dates, ArrayList<Integer> overallArray, ArrayList<Integer> boysArray, ArrayList<Integer> girlsArray) {
        overall = new XYChart.Series<>();
        boys = new XYChart.Series<>();
        girls = new XYChart.Series<>();
        
        overall.setName("Overall");
        boys.setName("Boys");
        girls.setName("Girls");

        for (int i = 0; i < dates.size(); i++) {
            overall.getData().add(new XYChart.Data<>(dates.get(i).toString(), overallArray.get(i)));
            boys.getData().add(new XYChart.Data<>(dates.get(i).toString(), boysArray.get(i)));
            girls.getData().add(new XYChart.Data<>(dates.get(i).toString(), girlsArray.get(i)));
        }
        
        lineChart.getData().addAll(overall, boys, girls);
    }


    public void goHome(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
    	root = loader.load();
    	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    
    @FXML
    private CategoryAxis x;
    
    @FXML
    private NumberAxis y;
    
    @FXML
    private LineChart<String, Number> lineChart;
    XYChart.Series<String, Number> overall;
    XYChart.Series<String, Number> boys;
    XYChart.Series<String, Number> girls;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
