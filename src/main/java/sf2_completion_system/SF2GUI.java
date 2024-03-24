package sf2_completion_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SF2GUI implements Initializable {
	
	@FXML
	private void toSecondary() throws IOException {
		Main.setRoot("excchooser");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
