module sf2_completion_system {
	
	opens sf2_completion_system to javafx.fxml;
	
	exports sf2_completion_system;
	exports methods;

	requires java.base;
	requires java.desktop;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
}