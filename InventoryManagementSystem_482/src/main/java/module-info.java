module com.inventory.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.inventory.inventorymanagementsystem to javafx.fxml;
    exports com.inventory.inventorymanagementsystem;
}