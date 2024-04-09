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
	
	CountStudents cs = new CountStudents();
	CountBoys cb = new CountBoys();
	CountGirls cg = new CountGirls();
	CountDates cd = new CountDates();
	CountOverall co = new CountOverall();
	
	private int enrolmentBoys = 0;
	private int enrolmentGirls = 0;
	private int enrolmentTotal = 0;
	
	private int lateEnrolmentBoys = 0;
	private int lateEnrolmentGirls = 0;
	private int lateEnrolmentTotal = 0;
	
	private int percentageBoys = 0;
	private int percentageGirls = 0;
	private int percentageTotal = 0;
	
	private double averageBoys = 0;
	private double averageGirls = 0;
	private double averageTotal = 0;
	
	private double percentageMonthBoys = 0;
	private double percentageMonthGirls = 0;
	private double percentageMonthTotal = 0;
	
	public int getEnrolmentBoys() {
		return enrolmentBoys;
	}
	
	public int getEnrolmentGirls() {
		return enrolmentGirls;
	}
	
	public int getEnrolmentTotal() {
		return enrolmentTotal;
	}
	
	public int getLateEnrolmentBoys() {
		return lateEnrolmentBoys;
	}
	
	public int getLateEnrolmentGirls() {
		return lateEnrolmentGirls;
	}
	
	public int getLateEnrolmentTotal() {
		return lateEnrolmentTotal;
	}
	
	public int getPercentageBoys() {
        return percentageBoys;
    }

    public int getPercentageGirls() {
        return percentageGirls;
    }

    public int getPercentageTotal() {
        return percentageTotal;
    }
	
    public double getAverageBoys() {
        return averageBoys;
    }

    public double getAverageGirls() {
        return averageGirls;
    }
    
    public double getAverageTotal() {
        return averageTotal;
    }
    
    public double getPercentageMonthBoys() {
        return percentageMonthBoys;
    }

    public double getPercentageMonthGirls() {
        return percentageMonthGirls;
    }

    public double getPercentageMonthTotal() {
        return percentageMonthTotal;
    }
	
	public void setLateEnrolmentBoys(int lateEnrolmentBoys) {
		this.lateEnrolmentBoys = lateEnrolmentBoys;
	}
	
	public void setLateEnrolmentGirls(int lateEnrolmentGirls) {
		this.lateEnrolmentGirls = lateEnrolmentGirls;
	}
	public void setLateEnrolmentTotal(int lateEnrolmentTotal) {
		this.lateEnrolmentTotal = lateEnrolmentTotal;
	}

	public void countStatistics(String path, String coordinates, String dateCoordinates, String sheetName, String statsCoordinates) {
		cs.countStudents(path, coordinates, sheetName);
		cb.countBoys(path, coordinates, dateCoordinates, sheetName);
		cg.countGirls(path, coordinates, dateCoordinates, sheetName);
		cd.countDates(path, dateCoordinates, sheetName);
		co.countOverallTotalAbsences(path, coordinates, dateCoordinates, sheetName);
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			enrolmentBoys = cs.getBoysNumber() - getLateEnrolmentBoys();
			enrolmentGirls = cs.getGirlsNumber() - getLateEnrolmentGirls();
			enrolmentTotal = getEnrolmentBoys() + getEnrolmentGirls();
			
			percentageBoys = cs.getBoysNumber() / getEnrolmentBoys() * 100;
			percentageGirls = cs.getGirlsNumber() / getEnrolmentGirls() * 100;
			percentageTotal = (cs.getBoysNumber() + cs.getGirlsNumber()) / (getEnrolmentBoys() + getEnrolmentGirls()) * 100;
			
			averageBoys = cb.getBoysTotalPresences() / cd.getNumberOfDates();
			averageGirls = cg.getGirlsTotalPresences() / cd.getNumberOfDates();
			averageTotal = co.getOverallPresences() / cd.getNumberOfDates();
					
			percentageMonthBoys = averageBoys / cs.getBoysNumber() * 100;
			percentageMonthGirls = averageGirls / cs.getGirlsNumber() * 100;
			percentageMonthTotal = averageTotal / (cs.getBoysNumber() + cs.getGirlsNumber()) * 100;
			
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
                    		 currentCell.setCellValue(getEnrolmentBoys());
                    		 break;
                    	 case 2: 
                    		 currentCell.setCellValue(getEnrolmentGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(getEnrolmentTotal());
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
                    		 currentCell.setCellValue(getLateEnrolmentTotal());
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
                    		 currentCell.setCellValue(getPercentageBoys());
                    		 break;
                    	 case 2:
                    		 currentCell.setCellValue(getPercentageGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(getPercentageTotal());
                    		 break;
                    	 }
                     } else if (rowIteration == 5) {
                    	 switch (cellIteration) {
                    	 case 1: 
                    		 currentCell.setCellValue(getAverageBoys());
                    		 break;
                    	 case 2:
                    		 currentCell.setCellValue(getAverageGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(getAverageTotal());
                    		 break;
                    	 }
                     } else if (rowIteration == 6) {
                    	 switch (cellIteration) {
                    	 case 1:
                    		 currentCell.setCellValue(getPercentageMonthBoys());
                    		 break;
                    	 case 2:
                    		 currentCell.setCellValue(getPercentageMonthGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(getPercentageMonthTotal());
                    		 break;
                    	 }
                     } else if (rowIteration == 7) {
                    	 switch (cellIteration) {
                    	 case 1: 
                    		 currentCell.setCellValue(cb.getConsecutiveAbsencesBoys());
                    		 break;
                    	 case 2: 
                    		 currentCell.setCellValue(cg.getConsecutiveAbsencesGirls());
                    		 break;
                    	 case 3:
                    		 currentCell.setCellValue(cb.getConsecutiveAbsencesBoys() + cg.getConsecutiveAbsencesGirls());
                    		 break;
                    	 }
                     }
                          
                     
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
