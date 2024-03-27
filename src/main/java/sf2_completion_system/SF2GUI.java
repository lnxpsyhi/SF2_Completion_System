package sf2_completion_system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SF2GUI implements Initializable {
	
	@FXML
	private TextField tf1 = new TextField();
	
	private String path = "";
	
	@FXML
	private void open() {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(Window.getWindows().get(0));
		path = file != null ? file.getAbsolutePath() : "";
		System.out.println(path);
		tf1.setText(path);
	}
	
	@FXML
	private void exit() {
		System.exit(0);
	}
	
	@FXML
	private void toSecondary() throws IOException {
		Main.setRoot("excchooser");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
