package com.example.vibetablegui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.fazecast.jSerialComm.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import static com.example.vibetablegui.mainGUI.comPort;

public class mainGUIController implements Initializable {

    static OutputStream outputStream;

    public void onStopButtonClick(ActionEvent actionEvent) throws IOException {
        OutputStream outputStream2 = comPort.getOutputStream();
        outputStream2.write('t');
        outputStream2.flush();
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private String[] direction = {"Forward","Backward"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(direction);
        myChoiceBox.setValue("Forward");
    }

    @FXML
    public void onSendButtonClick(ActionEvent actionEvent) throws Exception {
        OutputStream outputStream1 = comPort.getOutputStream();
        //outputStream1.write('s');
        String dirString = myChoiceBox.getValue();
        if (dirString == "Forward") {
            outputStream1.write("sf".getBytes());
        } else {
            outputStream1.write("sb".getBytes());
        }
        outputStream1.flush();
    }

}