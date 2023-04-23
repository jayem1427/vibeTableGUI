module com.example.vibetablegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;


    opens com.example.vibetablegui to javafx.fxml;
    exports com.example.vibetablegui;
}