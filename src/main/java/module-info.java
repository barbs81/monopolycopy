/*module com.example.monopoly {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.monopoly.monopoly to javafx.fxml;
    exports com.monopoly.monopoly;
}*/

module com.monopoly.monopoly {
        requires javafx.controls;
        requires javafx.fxml;

        requires org.controlsfx.controls;
        requires org.kordamp.ikonli.javafx;
        requires org.kordamp.bootstrapfx.core;
        requires org.json;

        opens com.monopoly.monopoly to javafx.fxml;
        exports com.monopoly.monopoly;
        }