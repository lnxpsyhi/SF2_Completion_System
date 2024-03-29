package sf2_completion_system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import methods.CountOverall;

public class SF2GUI implements Initializable {
	
	private CountOverall co = new CountOverall();
	
	@FXML
	private TextField tf1 = new TextField();
	
	private String path = "";
	
	private void genSheetBtns(File excelFile) {
		try (Workbook wb = new XSSFWorkbook()) {
			int numOfSheets = wb.getNumberOfSheets();
			for (int i = 0; i < numOfSheets; i++) {
				System.out.println(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private File verifyFile(String path) throws EncryptedDocumentException, IOException {
		File file = new File(path);
		Workbook wb = WorkbookFactory.create(file);
		return file;
	}
	
	@FXML
	private void open() throws EncryptedDocumentException, IOException {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(Window.getWindows().get(0));
		path = file != null ? file.getAbsolutePath() : "";
		genSheetBtns(verifyFile(path));
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
