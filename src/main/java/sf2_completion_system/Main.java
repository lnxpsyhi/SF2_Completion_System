package sf2_completion_system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private double xOffset;
	private double yOffset;

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
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		scene = new Scene(loadFXML("sf2"));
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				scene.setCursor(Cursor.OPEN_HAND);
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				scene.setCursor(Cursor.DEFAULT);
			}

		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("School Form 2 Completion System");
		primaryStage.getIcons().add(new Image(new FileInputStream(IMGICON)));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}

}
