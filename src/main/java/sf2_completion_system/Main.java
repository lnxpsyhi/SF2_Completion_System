package sf2_completion_system;
import methods.*;


public class Main {

	private static String PATH = "/home/akira/Desktop/Sheets/HTML(copy).xls";
	
	public static void main(String[] args) {

		CountOverall co = new CountOverall();

		co.countOverallTotalAbsences(PATH, "A18:BO78", "K16:BH16", 1);
		
		
	}

//	private static String PATH = "/home/akira/NetBeansProjects/SF2_JAVA_PROJECT/src/excel/SF2.xlsx";
//	
//	public static void main(String[] args) {
//
//		CountOverall co = new CountOverall();
//
//		co.countOverallTotalAbsences(PATH, "A13:AD55", "D11:AB11", 0);
//		
//		
//	}
}
