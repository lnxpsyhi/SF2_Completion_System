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

public class CountStatistics {
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;
	
	private int lateEnrolmentBoys = 0;
	private int lateEnrolmentGirls = 0;
	
	public int getLateEnrolmentBoys() {
		return lateEnrolmentBoys;
	}
	
	public int getLateEnrolmentGirls() {
		return lateEnrolmentGirls;
	}
	
	public void setLateEnrolmentBoys(int lateEnrolmentBoys) {
		this.lateEnrolmentBoys = lateEnrolmentBoys;
	}
	
	public void setLateEnrolmentGirs(int lateEnrolmentGirls) {
		this.lateEnrolmentGirls = lateEnrolmentGirls;
	}
	
	CountStudents cs = new CountStudents();
	CountBoys cb = new CountBoys();
	CountGirls cg = new CountGirls();
	CountDates cd = new CountDates();
	CountOverall co = new CountOverall();
	public void countStatistics(String path, String coordinates, String dateCoordinates, int sheetNo, String statsCoordinates) {
		cs.countStudents(path, coordinates, sheetNo);
		cb.countBoys(path, coordinates, dateCoordinates, sheetNo);
		cg.countGirls(path, coordinates, dateCoordinates, sheetNo);
		cd.countDates(path, dateCoordinates, sheetNo);
		co.countOverallTotalAbsences(path, coordinates, dateCoordinates, sheetNo);
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheetAt(sheetNo);
			
			int colonIndex = statsCoordinates.indexOf(":");
			
			if (colonIndex != -1) {

                CellReference start = new CellReference(statsCoordinates.substring(0, colonIndex));
                CellReference end = new CellReference(statsCoordinates.substring(colonIndex + 1));
                
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
                               row = mergedRegion.getLastRow();                           
                           }
                           
                     if (rowIteration == 1) {
                    	 switch(cellIteration) {
                    	 case 1:
                    		 currentCell.setCellValue(cs.getBoysNumber() - getLateEnrolmentBoys());
                    		 break;
                    	 case 2: 
                    		 currentCell.setCellValue(cs.getGirlsNumber() - getLateEnrolmentGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue((cs.getBoysNumber() - getLateEnrolmentBoys()) + (cs.getGirlsNumber() - getLateEnrolmentGirls()));
                    		 break;
                    	 }
                     } else if (rowIteration == 2) {
                    	 switch (cellIteration) {
                    	 case 1:
                    		 currentCell.setCellValue(getLateEnrolmentBoys());
                    		 break;
                    	 case 2:
                    		 currentCell.setCellValue(getLateEnrolmentGirls());
                    		 break;
                    	 case 3: 
                    		 currentCell.setCellValue(getLateEnrolmentBoys() + getLateEnrolmentGirls());
                    		 break;
                    	 }
                     } else if (rowIteration == 3) {
                    	 switch (cellIteration) {
                    	 case 1:
                    		 currentCell.setCellValue(cs.getBoysNumber());
                    		 break;
                    	 case 2: 
                    		 currentCell.setCellValue(cs.getGirlsNumber());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(cs.getBoysNumber() + cs.getGirlsNumber());
                    		 break;
                    	 }
                     } else if (rowIteration == 4) {
                    	 switch (cellIteration) {
                    	 case 1:
                    		 currentCell.setCellValue(cs.getBoysNumber() / (cs.getBoysNumber() - getLateEnrolmentBoys()) * 100);
                    		 break;
                    	 case 2:
                    		 currentCell.setCellValue(cs.getGirlsNumber() / (cs.getGirlsNumber() - getLateEnrolmentGirls()) * 100);
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue((cs.getBoysNumber() + cs.getGirlsNumber()) / (cs.getBoysNumber() - getLateEnrolmentBoys()) + (cs.getGirlsNumber() - getLateEnrolmentGirls()) * 100);
                    		 break;
                    	 }
                     }
                          
                     System.out.println((cs.getBoysNumber() + cs.getGirlsNumber()) / (cs.getBoysNumber() - getLateEnrolmentBoys()) + (cs.getGirlsNumber() - getLateEnrolmentGirls()));
                }
               }
                
			} else {
                System.out.println("Invalid placeholder format");
            }
			try (FileOutputStream fileout = new FileOutputStream(path)) {
                workbook.write(fileout);
            }
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
