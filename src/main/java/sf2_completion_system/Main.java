package sf2_completion_system;
import methods.Methods;

public class Main {

	private static String PATH = "/home/akira/Desktop/Sheets/HTML(copy).xls";
	
	public static void main(String[] args) {
		Methods m = new Methods();
		m.countDates(PATH, "K16:BH16");
		m.countAll(PATH, "A18:BO78");
	}

}
