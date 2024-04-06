package sf2_completion_system;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	private double xOffset;
	private double yOffset;
	private static final File IMGICON = new File("src/main/resources/images/file.png");
	
	public void start(Stage stage) {
		
		// configuration
		Properties config = new Properties();
		try (InputStream istream = new FileInputStream("src/main/resources/config.properties")) {
			config.load(istream);
			String test = config.getProperty("test");
			System.out.println(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
			Scene scene = new Scene(root);
			
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
					scene.setCursor(Cursor.OPEN_HAND);
					stage.setX(event.getScreenX() - xOffset);
					stage.setY(event.getScreenY() - yOffset);
				}
			});
			scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					scene.setCursor(Cursor.DEFAULT);
				}
			});
			
			stage.setScene(scene);
			stage.setTitle("School Form 2 Completion System");
			stage.getIcons().add(new Image(new FileInputStream(IMGICON)));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
