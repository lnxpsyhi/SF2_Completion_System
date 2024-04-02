package sf2_completion_system;

import methods.CountDates;
import methods.CountStatistics;

public class test {

	public static void main(String[] args) {
		String PATH = "/home/akira/Desktop/Sheets/HTML(copy).xls";
		CountStatistics cstats = new CountStatistics();
		CountDates cd = new CountDates();
		cd.countDates(PATH, "K16:BH16", 1);
		cstats.countStatistics(PATH, "A18:BO78", "K16:BH16", 1, "BU81:CA97");

	}

}
