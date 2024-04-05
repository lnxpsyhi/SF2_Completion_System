package validations;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileValidation {

	// check if the file is a valid excel file if not return nulls
	public boolean isExcel(File file) {
		try (Workbook wb = WorkbookFactory.create(file)) {
			return true;
		} catch (EncryptedDocumentException | IOException  | NullPointerException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}
}
