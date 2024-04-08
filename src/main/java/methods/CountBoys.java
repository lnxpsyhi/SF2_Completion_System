package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class CountBoys {
	
	private FileInputStream inputStream;
	private Workbook workbook;
	private Sheet sheet;
	
	CountDates cd = new CountDates();
	CountStudents cs = new CountStudents();
	
	private int consecutiveAbsencesBoys = 0;
	private int consecutiveCount = 0;
	
	private ArrayList<Integer> boysTotalPerDay = new ArrayList<Integer>();
	private HashMap<String, Integer> boysRecord = new HashMap<>();
	private ArrayList<String> perfectAttendanceBoys = new ArrayList<String>();
    private Map<String, Integer> mostAbsencesBoys = new LinkedHashMap<>();

	private int boysTotalAbsences = 0;
	
	public int getBoysTotalPerDay(int i) {
		return boysTotalPerDay.get(i);
	}
	public ArrayList<Integer> getBoysTotalPerDay() {
		return boysTotalPerDay;
	}
	public int getBoysTotalAbsences() {
		return boysTotalAbsences;
	}

	private int boysTotalPresences = 0;
	
	public int getBoysTotalPresences() {
		return boysTotalPresences;
	}
	public int getConsecutiveAbsencesBoys() {
		return consecutiveAbsencesBoys;
	}
	public ArrayList<String> getPerfectAttendanceBoys() {
		return perfectAttendanceBoys;
	}
	public Map<String, Integer> getMostAbsencesBoys() {
		return mostAbsencesBoys;
	}
	
	public void countBoys(String path, String coordinates, String dateCoordinates, String sheetName) {
		cd.countDates(path, dateCoordinates, sheetName);
		cs.countStudents(path, coordinates, sheetName);
		
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			int colonIndex = coordinates.indexOf(":");

			boysTotalPresences = cs.getBoysNumber() * cd.getNumberOfDates();
			
			
			
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
                	
                	String name = "";
                	boolean alreadyIncremented = false;
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
                		
                        if (cellIteration == 2 && rowIteration <= cs.getBoysNumber()) {
                        	name = currentCell.getStringCellValue();
                        }
                        
                        if (cellIteration > 2 + cd.getBlanks() && cellIteration <= 2 + cd.getBlanks() + cd.getNumberOfDates()) {
                            int rowPerDayIteration = 0;
                            int presencePerDay = 0;
                            for (int row2 = currentCell.getAddress().getRow(); row2 <= start.getRow() + cs.getBoysNumber(); row2++) {
                                rowPerDayIteration++;
                                Row currentRow2 = sheet.getRow(row2);
                                Cell currentCell2 = currentRow2.getCell(cell);
                                
                  
                                CellRangeAddress mergedRegion2 = null;
                                for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                                    CellRangeAddress region = sheet.getMergedRegion(i);
                                    if (region.isInRange(row2, cell)) {
                                        mergedRegion2 = region;
                                        break;
                                    }
                                }
                                
                                
                                
                                if (mergedRegion2 != null) {
                                    
                                    int firstColumn = mergedRegion2.getFirstColumn();
                                    int firstRow = mergedRegion2.getFirstRow();
                                    currentCell2 = sheet.getRow(firstRow).getCell(firstColumn);
                                }
                                
                                if (currentCell2.getCellType().equals(CellType.BLANK) && rowPerDayIteration < cs.getBoysNumber() + 1) {
                                    presencePerDay++;
                                }
                                if (rowPerDayIteration == cs.getBoysNumber() + 1) {
                                    currentCell2.setCellValue(presencePerDay);
                                }
                 
                               
                            }
                            if (boysTotalPerDay.size() < cd.getNumberOfDates()) {
                            	boysTotalPerDay.add(presencePerDay);
                            }
                        }
                        
                       if (rowIteration <= cs.getBoysNumber() && cellIteration > 2 && cellIteration < 28 && currentCell.getStringCellValue().trim().equalsIgnoreCase("x")) {
                    	   consecutiveCount++;
                    	   if (consecutiveCount == 5 && !alreadyIncremented) {
                    		   consecutiveAbsencesBoys++;
                    		   alreadyIncremented = true;
                    	   }
                       } else {
                    	   consecutiveCount = 0;
                       }
                        
                		if (rowIteration <= cs.getBoysNumber() && cellIteration > 2 && cellIteration < 28 && currentCell.getStringCellValue().trim().equalsIgnoreCase("x")) {
                			studentAbsences++;
                			studentPresences--;
                			boysTotalAbsences++;
                			boysTotalPresences--;
                		}
                		
                		if (rowIteration <= cs.getBoysNumber() && cellIteration > 2 && cellIteration == 28) {
                			currentCell.setCellValue(studentAbsences);
                		} else if (rowIteration <= cs.getBoysNumber() && cellIteration > 2 && cellIteration == 29) {
                			currentCell.setCellValue(studentPresences);
                		} else if (rowIteration == cs.getBoysNumber() + 1 && cellIteration == 28) {
                			currentCell.setCellValue(getBoysTotalAbsences());
                		} else if (rowIteration == cs.getBoysNumber() + 1 && cellIteration == 29) {
                			currentCell.setCellValue(getBoysTotalPresences());
                		}
                		
                	}
                	
                	if (name != "") {
                		boysRecord.put(name, studentAbsences);
                	}
                	
                }
                
                attendanceTracker();
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
	public void attendanceTracker() {
	    PriorityQueue<Map.Entry<String, Integer>> top5 = new PriorityQueue<>(Map.Entry.comparingByValue());


	    for (Map.Entry<String, Integer> entry : boysRecord.entrySet()) {
	        int value = entry.getValue();
	        if (value == 0) {
	            perfectAttendanceBoys.add(entry.getKey());

	        } else {
	            top5.offer(entry);
	            if (top5.size() > 5) {
	                top5.poll();
	            }
	        }
	    }


		HashMap<String, Integer> temporary = new HashMap<>();

	    while (!top5.isEmpty()) {
	        Map.Entry<String, Integer> entry = top5.poll();
	        temporary.put(entry.getKey(), entry.getValue());
	    }

        temporary.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> mostAbsencesBoys.put(x.getKey(), x.getValue()));
        
       
        
	}
}
