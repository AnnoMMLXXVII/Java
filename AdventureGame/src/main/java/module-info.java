module com.game.adventuregame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.game.adventuregame to javafx.fxml;
    exports com.game.adventuregame;
    exports com.game.adventuregame.controller;
    opens com.game.adventuregame.controller to javafx.fxml;
    exports com.game.adventuregame.model;
    opens com.game.adventuregame.model to javafx.fxml;
}