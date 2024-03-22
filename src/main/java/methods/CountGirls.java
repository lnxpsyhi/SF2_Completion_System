package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class CountGirls {
	
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;
	
	CountDates cd = new CountDates();
	CountStudents cs = new CountStudents();
	
    private int girlsTotalAbsences = 0;
	
	public int getGirlsTotalAbsences() {
		return girlsTotalAbsences;
	}
	
	private int girlsTotalPresences = 0;
	
	public int getGirlsTotalPresences() {
		return girlsTotalPresences;
	}
	
	public void countGirls(String path, String coordinates, String dateCoordinates) {
		cd.countDates(path, dateCoordinates);
		cs.countStudents(path, coordinates);
		
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheetAt(1);
			
			int colonIndex = coordinates.indexOf(":");

			girlsTotalPresences = cs.getGirlsNumber() * cd.getNumberOfDates();
			
			
            if (colonIndex != -1) {

                CellReference start = new CellReference(coordinates.substring(0, colonIndex));
                CellReference end = new CellReference(coordinates.substring(colonIndex + 1));
                
                
                
                int rowIteration = 0;
                for (int row = start.getRow(); row <= end.getRow(); row++) {
                	rowIteration++;
                	Row currentRow = sheet.getRow(row);
                	int cellIteration = 0;
                	int studentAbsences = 0;
                	int studentPresences = cd.getNumberOfDates();
                	
               
                	
                	for (int cell = start.getCol(); cell <= end.getCol(); cell++) {
                		cellIteration++;
                		Cell currentCell = currentRow.getCell(cell);
                		CellRangeAddress mergedRegion = null;
                		
                		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                            CellRangeAddress region = sheet.getMergedRegion(i);
                            if (region.isInRange(row, cell)) {
                                mergedRegion = region;
                                break;
                            }
                        }
                        
                        if (mergedRegion != null) {
                            cell = mergedRegion.getLastColumn();
                        }
                		
                                  
                        
                		if (rowIteration > cs.getBoysNumber() + 1 && rowIteration <= cs.getBoysNumber() + cs.getGirlsNumber() + 2 && cellIteration > 2 && cellIteration < 28 && currentCell.getStringCellValue().trim().equalsIgnoreCase("x")) {
                			studentAbsences++;
                			studentPresences--;
                			girlsTotalAbsences++;
                			girlsTotalPresences--;
                		}
                		
                		if (rowIteration > cs.getBoysNumber() + 1 && rowIteration <= cs.getBoysNumber() + cs.getGirlsNumber() + 1 && cellIteration == 28) {
                			currentCell.setCellValue(studentAbsences);
                		} else if (rowIteration > cs.getBoysNumber() + 1 && rowIteration <= cs.getBoysNumber() + cs.getGirlsNumber() + 1 && cellIteration == 29) {
                			currentCell.setCellValue(studentPresences);
                		} else if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 2 && cellIteration == 28) {
                			currentCell.setCellValue(getGirlsTotalAbsences());
                		} else if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 2 && cellIteration == 29) {
                			currentCell.setCellValue(getGirlsTotalPresences());
                		}
                		
                	}
                	
                	
                }
                
            }
           
            
            try (FileOutputStream fileout = new FileOutputStream(path)) {
                workbook.write(fileout);
            }
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
