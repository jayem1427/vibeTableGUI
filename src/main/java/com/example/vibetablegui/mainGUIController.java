package com.example.vibetablegui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.fazecast.jSerialComm.*;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import static com.example.vibetablegui.mainGUI.comPort;

public class mainGUIController {
    @FXML
    private Label welcomeText;
    static OutputStream outputStream;

    @FXML
    public void onSendButtonClick(ActionEvent actionEvent) throws Exception {
//        String s = "s";
//        comPort.writeBytes(s.getBytes("UTF8"), s.length());
//        System.out.println(comPort.writeBytes(s.getBytes(), s.length()));
        OutputStream outputStream1 = comPort.getOutputStream();
        outputStream1.write('s');
        outputStream1.flush();
    }

    public void onStopButtonClick(ActionEvent actionEvent) {
//        SerialPort comPort = SerialPort.getCommPort("COM3");
//        comPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
//        comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);

//        comPort.openPort();
        comPort.writeBytes("t".getBytes(), "t".length());
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
    }
}