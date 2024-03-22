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

public class CountOverall {
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;

	CountStudents cs = new CountStudents();
	CountBoys cb = new CountBoys();
	CountGirls cg = new CountGirls();
	
	
	private int overallAbsences = 0;
	private int overallPresences = 0;
	
	public int getOverallPresences() {
		return overallPresences;
	}
	public int getOverallAbsences() {
		return overallAbsences;
	}
	
	public void countOverallTotalAbsences(String path, String coordinates, String dateCoordinates) {
		cs.countStudents(path, coordinates);
		cb.countBoys(path, coordinates, dateCoordinates);
		cg.countGirls(path, coordinates, dateCoordinates);
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheetAt(1);
			
			int colonIndex = coordinates.indexOf(":");

			overallAbsences = cb.getBoysTotalAbsences() + cg.getGirlsTotalAbsences();
			overallPresences = cb.getBoysTotalPresences() + cg.getGirlsTotalPresences();
			
            if (colonIndex != -1) {

                CellReference start = new CellReference(coordinates.substring(0, colonIndex));
                CellReference end = new CellReference(coordinates.substring(colonIndex + 1));
                
                
                
                int rowIteration = 0;
                for (int row = start.getRow(); row <= end.getRow(); row++) {
                	rowIteration++;
                	Row currentRow = sheet.getRow(row);
                	int cellIteration = 0;

                	
                	
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
                      
                        if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 3 && cellIteration == 28) {
                			currentCell.setCellValue(getOverallAbsences());
                		} else if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 3 && cellIteration == 29) {
                			currentCell.setCellValue(getOverallPresences());
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
