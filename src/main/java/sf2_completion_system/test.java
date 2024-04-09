package sf2_completion_system;

import java.util.Properties;

//import methods.CountBoys;
//import methods.CountGirls;
//import methods.CountOverall;
import methods.CountStatistics;
import methods.CountStudents;

public class test {

	public static void main(String[] args) {
<<<<<<< HEAD
		String PATH = "/home/akira/Desktop/Sheets/HTML(copy).xls";
		CountStatistics cstats = new CountStatistics();
//      cstats.countStatistics(PATH, "A18:BO78", "K16:BH16", sheetName, "BU81:CA97");
//		CountBoys cb = new CountBoys();
//		cb.countBoys(PATH, "A18:BO78", "K16:BH16", 1);
//		CountGirls cg = new CountGirls();
//		cg.countGirls(PATH, "A18:BO78", "K16:BH16", 1);
//		CountOverall co = new CountOverall();
//		co.countOverallTotalAbsences(PATH, "A18:BO78", "K16:BH16", 1);

=======
		CountStudents cs = new CountStudents();
		cs.countStudents("/home/akira/Desktop/Sheets/HTML(copy).xls", "A18:BO78", "MAY");
		System.out.println(cs.getBoysNumber());
		System.out.println(cs.getGirlsNumber());
>>>>>>> tem-save-progress
	}

}
