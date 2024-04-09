package methods;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class CountStudents {
	
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;
	
	private int boysNumber = 0;
	
	private int girlsNumber = 0;
	
	public int getBoysNumber() {
		return boysNumber;
	}
	
	public int getGirlsNumber() {
		return girlsNumber;
	}
	
	public void countStudents(String path, String coordinates, String sheetName) {
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			int colonIndex = coordinates.indexOf(":");

			
			boolean changed = false;
			
            if (colonIndex != -1) {

                CellReference start = new CellReference(coordinates.substring(0, colonIndex));
                CellReference end = new CellReference(coordinates.substring(colonIndex + 1));
                
                
                
         
                for (int row = start.getRow(); row <= end.getRow(); row++) {
              
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

                		if (cellIteration == 1) {
                        	if (!changed) {
                        		if (currentCell.getCellType().equals(CellType.NUMERIC)) {
                        			boysNumber++;
                        		} else if (currentCell.getCellType().equals(CellType.BLANK)) {
                        			changed = true;
                        		}
                        	} else {
                        		if (currentCell.getCellType().equals(CellType.NUMERIC)) {
                        			girlsNumber++;
                        		}
                        	}
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
