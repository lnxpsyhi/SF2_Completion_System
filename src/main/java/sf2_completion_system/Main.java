package sf2_completion_system;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	private static final File IMGICON = new File("src/main/resources/images/file.png");
	
	private static Scene scene;
	
	static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
	
	private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
	
	public static void main(String[] args) {
		
		launch(args);

		//CountOverall co = new CountOverall();
		//co.countOverallTotalAbsences(PATH, "A18:BO78", "K16:BH16", 1);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		scene = new Scene(loadFXML("sf2"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Form 2 Completion System");
        primaryStage.getIcons().add(new Image(new FileInputStream(IMGICON)));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
	}
	
}
