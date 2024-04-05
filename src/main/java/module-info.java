module sf2_completion_system {
	
	opens Controllers to javafx.fxml;
	
	exports sf2_completion_system;
	exports methods;
	
	requires java.desktop;
	requires java.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires javafx.base;
}