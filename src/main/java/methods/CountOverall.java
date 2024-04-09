package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
	CountDates cd = new CountDates();
	
	Map<String, Integer> mostAbsencesOverall = new LinkedHashMap<>();
	ArrayList<String> perfectAttendanceOverall = new ArrayList<String>();
	
	private int overallAbsences = 0;
	private int overallPresences = 0;
	private ArrayList<Integer> combinedTotalPerDay = new ArrayList<Integer>(); 
	public ArrayList<Integer> getCombinedTotalPerDay() {
		return combinedTotalPerDay;
	}

		
	
	public int getOverallPresences() {
		return overallPresences;
	}
	public int getOverallAbsences() {
		return overallAbsences;
	}
	public Map<String, Integer> getMostAbsencesOverall() {
		return mostAbsencesOverall;
	}
	public ArrayList<String> getPerfectAttendanceOverall() {
		return perfectAttendanceOverall;
	}
	public void countOverallTotalAbsences(String path, String coordinates, String dateCoordinates, String sheetName) {
		cs.countStudents(path, coordinates, sheetName);
		cb.countBoys(path, coordinates, dateCoordinates, sheetName);
		cg.countGirls(path, coordinates, dateCoordinates, sheetName);
		cd.countDates(path, dateCoordinates, sheetName);
		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			 for (int i = 0; i < cd.getNumberOfDates(); i++) {
	            	combinedTotalPerDay.add(cb.getBoysTotalPerDay(i) + cg.getGirlsTotalPerDay(i));
	            }  
			
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
                	int count = 0;
                	
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
                        
                        if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 3 && cellIteration > 2 + cd.getBlanks() && cellIteration <= 2 + cd.getBlanks() + cd.getNumberOfDates()) {
                        	currentCell.setCellValue(combinedTotalPerDay.get(count));
                        	count++;
                        } else
                        	if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 3 && cellIteration == 28) {
                			currentCell.setCellValue(getOverallAbsences());
                		} else if (rowIteration == cs.getBoysNumber() + cs.getGirlsNumber() + 3 && cellIteration == 29) {
                			currentCell.setCellValue(getOverallPresences());
                		}                 			                        
                	}    
                }
            } else {
                System.out.println("Invalid placeholder format");
            }
           attendanceTracker();
            try (FileOutputStream fileout = new FileOutputStream(path)) {
                workbook.write(fileout);
            }
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void attendanceTracker() {
		
		HashMap<String, Integer> temporary = new HashMap<>();
		temporary.putAll(cb.getMostAbsencesBoys());
		temporary.putAll(cg.getMostAbsencesGirls());
		
		temporary.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> mostAbsencesOverall.put(x.getKey(), x.getValue()));
		
		perfectAttendanceOverall.addAll(cb.getPerfectAttendanceBoys());
		perfectAttendanceOverall.addAll(cg.getPerfectAttendanceGirls());
		System.out.println("Most absences: " + getMostAbsencesOverall());
		System.out.println("Perfect Attendance: " + getPerfectAttendanceOverall());
	}
}
