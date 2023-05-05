package com.example.vibetablegui;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class mainGUI extends Application {
    protected static SerialPort comPort = SerialPort.getCommPort("COM7");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainGUI.class.getResource("mainGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vibration Table Control Application v1.0.0");
        stage.setScene(scene);
        stage.show();
        comPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);

        comPort.openPort();
    }

    @Override
    public void stop(){
        comPort.closePort();

    }
    public static void main(String[] args) {
        launch();
    }
}