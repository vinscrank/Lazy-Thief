/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import LazyThief.controller.ControllerForView;

/**
 *
 * @author User
 */
public class GameMenuController implements Initializable {

    @FXML
    JFXButton multiplayerButton;
    @FXML
    JFXButton changeProfileButton;
    @FXML
    JFXButton exitButton;
    @FXML
    JFXButton playCampaignButton;

    @FXML
    Label playerName;
    @FXML
    Label numberLives;
    @FXML
    Label highScore;
    @FXML
    Label record;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerName.setText(ControllerForView.getInstance().getPlayerName());
        numberLives.setText(String.valueOf(ControllerForView.getInstance().getNumberOfLives()));
        highScore.setText(String.valueOf(ControllerForView.getInstance().getActualScore()));
        record.setText(String.valueOf(ControllerForView.getInstance().getRecord()));

    }

    public void onPlayCampaignButtonClicked() {
        View.getInstance().setCampaignMode(true);
        //ControllerForView.getInstance().setMultiplayerMode(false);
        ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getPlayerMission(), true);

    }

    public void onChangeProfileButtonClicked() {
        View.getInstance().openLoadProfileWindow();
    }

    public void onPlayPodiumButtonClicked() {

        View.getInstance().ranking();
    }

    public void onExitButtonClicked() {
        System.exit(0);
    }
}
