package methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class CountDates {
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;
	private ArrayList<Integer> dates = new ArrayList<Integer>();
	
	private int blanks;
	
	private int numberOfDates;
	
	public int getNumberOfDates() {
		return numberOfDates;
	}
	
	public int getBlanks() {
		return blanks;
	} 
	
	
	public void countDates(String path, String coordinates, String sheetName) {
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			
			
			int colonIndex = coordinates.indexOf(":");

			
			
            if (colonIndex != -1) {

                CellReference start = new CellReference(coordinates.substring(0, colonIndex));
                CellReference end = new CellReference(coordinates.substring(colonIndex + 1));
                
                boolean countingBlanks = true;
                for (int row = start.getRow(); row <= end.getRow(); row++) {
                	Row currentRow = sheet.getRow(start.getRow());
                	for (int cell = start.getCol(); cell <= end.getCol(); cell++) {
               		 
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
                            row = mergedRegion.getLastRow();
                        }
               		
                       if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
                       	
                       	if (countingBlanks) {
                       		blanks++;
                       	
                       	}
                       	
                       } else if (currentCell.getCellType() == CellType.NUMERIC) {
                           countingBlanks = false;                  
                           numberOfDates++;
                           dates.add(currentCell.getNumericCellValue());
                       }
                    
               	   }
                }
                	
                
            } else {
                System.out.println("Invalid placeholder format");
            }
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	

}
