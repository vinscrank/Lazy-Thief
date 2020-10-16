/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

/**
 *
 * @author franc
 */
public class PlayerControlPanel extends GridPane {

    private final ProgressBar launchForce;
    private JFXButton exit;
    private JFXButton animation;
    private final Label playerTurn;
    private final Label playerScore;

    public PlayerControlPanel() {
        super();
        //inizializza label player turn
        this.playerTurn = new Label("0");
        //inizIALIZZA PLAYER SCORE

        this.playerScore = new Label("N/D");
        //inizializza barra
        this.launchForce = new ProgressBar();
        this.launchForce.setProgress(0);
        this.getStylesheets().add(getClass().getResource("buttons.css").toExternalForm());
        //exit = new JFXButton("Esci");
        //animation=new JFXButton("Aaaaaaaaaaaaaaaa");

        VBox box = new VBox();

    }

    public void setPlayerTurn(int playerTime) {
        this.playerTurn.setText(String.valueOf(playerTime));
    }

    public void setPlayerScore(int score) {
        this.playerScore.setText(String.valueOf(score));
    }

    public void setProgressBar(double powerFire) {
        this.launchForce.setProgress(powerFire / 100);
    }

}
