module JAVAFX_Controllers {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.poi.ooxml;
    requires org.controlsfx.controls;

    opens JAVAFX_Controllers to javafx.fxml;
    exports JAVAFX_Controllers;
}