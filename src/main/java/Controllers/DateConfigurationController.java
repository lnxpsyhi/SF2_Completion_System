package Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class DateConfigurationController implements Initializable {

	private Alert alert = new Alert(AlertType.WARNING);
	
	private Properties props = new Properties();
	private static final String CONFIG_PATH = "src/main/resources/config.properties";
	
	@FXML
	private TextField dateCoordinateField;
	
    @FXML
    public void goToAPConfiguration(ActionEvent event) {

        String dateCoordinates = dateCoordinateField.getText();

        props.setProperty("dateCoordinates", dateCoordinates);

        try (OutputStream output = new FileOutputStream(CONFIG_PATH)) {
            props.store(output, "Warning! This is the configuration file. Do not delete it!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try (InputStream istream = new FileInputStream(CONFIG_PATH)) {
			props.load(istream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
