package sf2_completion_system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ExcChooser implements Initializable {
	
	private FileChooser chooser = new FileChooser();
	
	@FXML
	private void chooseFile() {
		File excelFile = chooser.showOpenDialog(Window.getWindows().get(0));
	}
	
	@FXML
	private void toSF2GUI() throws IOException {
		Main.setRoot("sf2");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
