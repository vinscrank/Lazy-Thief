/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import LazyThief.controller.ControllerForView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML ControllerForView class
 *
 * @author franc
 */
public class NewProfileWindowController implements Initializable {

    @FXML
    private JFXTextField playerName;
    @FXML
    private JFXButton confirmData;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // difficultyLevel.valueProperty().addListener((obs, oldval, newVal) ->
        //difficultyLevel.setValue(Math.round(newVal.doubleValue())));
    }

    public void onButtonClicked() {
        String name = this.playerName.getText();
        //int level = (int)this.difficultyLevel.getValue()-1;
        //System.out.println(level);
        try {
            if (!name.equals("")) {
                ControllerForView.getInstance().createNewGameProfile(name, 2);
                //open profile page
                View.getInstance().openLoadProfileWindow();
            }

        } catch (IOException ioe) {
            Alert abortMessage = new Alert(AlertType.ERROR);
            abortMessage.setTitle("Errore Irreversibile");
            abortMessage.setHeaderText("Si è verificato un errore!");
            abortMessage.setContentText("Impossibile settare il file dei salvataggi! Il programma verrà arrestato.");
            abortMessage.showAndWait();
            System.exit(1);
        }

    }

    public void onBackButtonClicked() {
        View.getInstance().openStartWindow();
    }

}
