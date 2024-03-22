package sf2_completion_system;
import methods.*;


public class Main {

	private static String PATH = "/home/akira/Desktop/Sheets/HTML(copy).xls";
	
	public static void main(String[] args) {

		CountOverall co = new CountOverall();
		
		co.countOverallTotalAbsences(PATH, "A18:BO78", "K16:BH16");

	}

}
