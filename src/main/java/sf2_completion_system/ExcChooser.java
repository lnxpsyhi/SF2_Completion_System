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
import javafx.stage.FileChooser.ExtensionFilter;

public class ExcChooser implements Initializable {
	
	@FXML
	private TextField tf1 = new TextField();
	private File excfile;
	private FileChooser chooser = new FileChooser();
	
	@FXML
	private void chooseFile() {
		excfile = chooser.showOpenDialog(Window.getWindows().get(0));
		if (excfile != null) {
			tf1.setText(excfile.getPath());
		} else {
			System.out.println("No file selected!");
		}
		
	}
	
	@FXML
	private void toSF2GUI() throws IOException {
		Main.setRoot("sf2");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ExtensionFilter excft = new ExtensionFilter("Excel (*.xlsx, *.xls)", "*.xlsx", "*.xls");
		ExtensionFilter allft = new ExtensionFilter("All", "*");
		chooser.getExtensionFilters().add(excft);
		chooser.getExtensionFilters().add(allft);
	}

}
