module com.inventorymanagementsystem_482 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.inventorymanagementsystem_482 to javafx.fxml;
    exports com.inventorymanagementsystem_482;
}