package sf2_completion_system;

import java.util.Properties;

//import methods.CountBoys;
//import methods.CountGirls;
//import methods.CountOverall;
import methods.CountStatistics;
import methods.CountStudents;

public class test {

	public static void main(String[] args) {
		CountStudents cs = new CountStudents();
		cs.countStudents("/home/akira/Desktop/Sheets/HTML(copy).xls", "A18:BO78", "MAY");
		System.out.println(cs.getBoysNumber());
		System.out.println(cs.getGirlsNumber());
	}

}
