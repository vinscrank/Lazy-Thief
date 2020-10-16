/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import LazyThief.model.scenario1Model;

import LazyThief.model.StickmanModel;
import LazyThief.utils.Config;
import LazyThief.utils.Constant;
import LazyThief.utils.ReadCSV;

import LazyThief.utils.WriteCSV;
import LazyThief.view.Scenario1;

import LazyThief.view.View;
import LazyThief.model.model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;

public class ControllerForView implements IControllerForView {

    private static ControllerForView instance = null;
  
    private Timeline loop;
    
  

    //crea profilo giocatore e scrive sul csv
    @Override
    public void createNewGameProfile(String name, int difficultyLevel) throws IOException {
        int diamonds = Constant.DIAMONDS;
        int playerId = -1;
        int numberOfLives = 0;
        switch (difficultyLevel) {
            case Constant.EASY_LEVEL:
                numberOfLives = Constant.EASY_LEVEL_NUMBER_OF_LIVES;
                break;
            case Constant.MEDIUM_LEVEL:
                numberOfLives = Constant.MEDIUM_LEVEL_NUMBER_OF_LIVES;
                break;
            case Constant.HARD_LEVEL:
                numberOfLives = Constant.HARD_LEVEL_NUMBER_OF_LIVES;
                break;
            default:
                break;
        }

        if (new File("gameprofiles/saved.csv").exists()) {
            if (!ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8").isEmpty()) {
                playerId = Integer.parseInt(ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8").getLast()[0]);
            }
        } else {
            new File("gameprofiles").mkdir();
            new File("gameprofiles/saved.csv").createNewFile();
        }

        String[] playerData = new String[18];
        playerData[0] = String.valueOf(++playerId); //Setting up the ID of the player.
        playerData[1] = name; //Setting up the name of the player.
        playerData[2] = String.valueOf(0); //Setting up the High-Score.
        playerData[3] = String.valueOf(numberOfLives); //Setting up the number of lives.
        playerData[4] = String.valueOf(difficultyLevel); //Setting upt the current difficulty level.
        playerData[5] = String.valueOf(1); //Setting up the current scenery in the campaign.
        playerData[6] = String.valueOf(diamonds);
        playerData[7] = String.valueOf(0);
        playerData[8] = String.valueOf(3);
        playerData[9] = String.valueOf(3);
        playerData[10] = String.valueOf(3);
        playerData[11] = String.valueOf(3);
        playerData[12] = String.valueOf(3);
        playerData[13] = String.valueOf(3);
        playerData[14] = String.valueOf(3);
        playerData[15] = String.valueOf(3);
        playerData[16] = String.valueOf(3);
        playerData[17] = String.valueOf(3);

        WriteCSV.printRow("gameprofiles/saved.csv", "UTF-8", playerData);

        model.getInstance().setPlayerData();
    }

    @Override
    public LinkedList<String[]> getListOfPlayers() throws IOException {
        model.getInstance().setPlayerData();
        return model.getInstance().getPlayerData().asListOfStringArray();
    }

    @Override
    public void deleteProfile(int idProfile) {
        model.getInstance().deleteProfile(idProfile);
    }

    @Override
    public void loadGameProfile(int idProfile) throws IOException {
        model.getInstance().setPlayerData();
        model.getInstance().getPlayerData().getListOfPlayers().stream().filter((p) -> (p.getPlayerId() == idProfile)).forEachOrdered((p) -> {
            model.getInstance().setCurrentPlayer(p);
        });
    }

    @Override
    public int getNumberOfLives() {
        return model.getInstance().getPlayerNumberOfLives();
    }

    @Override
    public int getActualScore() {
        return model.getInstance().getDiamanti();
    }

 
    @Override
    public int getRecord() {
        return model.getInstance().getRecord();
    }

    @Override
    public String getPlayerName() {
        return model.getInstance().getPlayerName();
    }

    @Override
    public int getPlayerMission() {
        return model.getInstance().getPlayerCurrentLevel();
    }

    @Override
    public void loadLevel(int level, boolean modality) {

        String path = "/resources/config/level1.txt";

        switch (level) {
            case Constant.MISSION_ONE_ID:
                Config.getInstance().changeConfigurationFile(path);
                model.getInstance().init(level,
                        modality,
                        //mette i due blocchi base per il primo scenario del nostro giocatore
                        new scenario1Model(Config.getInstance().getPlayerBaseStartX(), Config.getInstance().getPlayerBaseStartY()),
                        //mette i due blocchi base per il primo scenario del giocatore avversario
                        new scenario1Model(Config.getInstance().getTerrainStartX(), Config.getInstance().getTerrainStartY()));

                View.getInstance().prepareGameScreen(
                        new Scenario1(Config.getInstance().getPlayerBaseStartX(), Config.getInstance().getPlayerBaseStartY()),
                        new Scenario1(Config.getInstance().getTerrainStartX(), Config.getInstance().getTerrainStartY()));

                break;

            default:
                View.getInstance().showInformationDialog("Complimenti, hai completato tutte le missioni. "
                        + "Ora puoi rigiocare un qualunque scenario senza perdere vite",
                        "Campagna Completata");
                break;
        }
    }

    @Override
    public ArrayList<double[]> getStickmans() {
        ArrayList<double[]> coordinates = new ArrayList<>();
        ArrayList<StickmanModel> stickman_array = model.getInstance().getPenguins();
        stickman_array.forEach((StickmanModel nextPenguin) -> {
            double[] coordinatesOfNextStick = {
                nextPenguin.getX(), nextPenguin.getY()
            };

            coordinates.add(coordinatesOfNextStick);
        });

        return coordinates;
    }

  

    @Override
    public void shoot(double angle, double speed) {
        
        if (loop!= null)
                loop.stop();

        final Bounds bounds = View.getInstance().getLayout().getBoundsInLocal();

        loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            int i = 0;
            int timeTranslation = 0;
            double deltaX = speed / 2 * Math.cos(angle);
            double deltaY = -speed / 2 * Math.sin(angle);

            @Override
            public void handle(final ActionEvent t) {
                //parte nuova

                final boolean atRightBorder = model.getInstance().getPlayerBullet().getLayoutX() + 85 >= (bounds.getMaxX());
                final boolean atLeftBorder = model.getInstance().getPlayerBullet().getLayoutX() + 85 <= (bounds.getMinX());
                final boolean atTopBorder = (model.getInstance().getPlayerBullet().getLayoutY() + 305) <= (bounds.getMinY());

                if (atRightBorder) {

                    deltaX *= -1;
                }
                if (atTopBorder) {
                    deltaY *= -1;
                }
                if (atLeftBorder) {
                    deltaX *= -1;
                }

                if (!model.getInstance().stoneHit()) {

                    View.getInstance().getBattlefield().getPlayerBullet().setLayoutX(View.getInstance().getBattlefield().getPlayerBullet().getLayoutX() + deltaX);
                    View.getInstance().getBattlefield().getPlayerBullet().setLayoutY(View.getInstance().getBattlefield().getPlayerBullet().getLayoutY() + deltaY);
                    model.getInstance().getPlayerBullet().setLayoutX(model.getInstance().getPlayerBullet().getLayoutX() + deltaX);
                    model.getInstance().getPlayerBullet().setLayoutY(model.getInstance().getPlayerBullet().getLayoutY() + deltaY);

                    if (model.getInstance().blockHit()) {
                        i = i + 1;

                        if (model.getInstance().getcDaInvertire() == 1) {

                            deltaX *= -1;
                            deltaY *= 1;
                        } else if (model.getInstance().getcDaInvertire() == 2) {

                            deltaX *= -1;
                            deltaY *= 1;

                        } else if (model.getInstance().getcDaInvertire() == 4) {

                            deltaX *= 1;
                            deltaY *= -1;

                        } else if (model.getInstance().getcDaInvertire() == 3) {

                            deltaX *= 1;
                            deltaY *= -1;

                        }

                    }
                } else {
                    //ferma animazione quando prende la pietra
                    loop.stop();
                }
            }

            int bonus = model.getInstance().checkForUpdateGameProfiles();

        }));
        int bonus = model.getInstance().checkForUpdateGameProfiles();

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

    }

   

    @Override
    public int getCurrentMission() {
        return model.getInstance().getLevel();
    }

    

  

   

    @Override
    public void platform(String npunti, int nvite) {
        Platform.runLater(() -> {
            boolean replay;
            if (model.getInstance().getPlayer().getActualShape() == 15) {
                replay = View.getInstance().showConfirmationDialog(
                        new StringBuilder().append("VITTORIA")
                        .append(npunti).toString(),
                        "Partita Terminata",
                        "Rigioca",
                        "Torna al menù principale");

            } else {

                replay = View.getInstance().showConfirmationDialog(
                        new StringBuilder().append("Partita terminata!")
                        .append(npunti).toString(),
                        "Partita Terminata",
                        "Rigioca",
                        "Torna al menù principale");
            }

            //salva sul file
            try {
                WriteCSV.print("gameprofiles/saved.csv", "UTF-8", model.getInstance().getPlayerData().asListOfStringArray());
            } catch (IOException ioe) {
                ControllerForModel.getInstance().notifyException("Si è verificato un errore: " + ioe);
            }
            if (replay) {
                //setta le vite 
                model.getInstance().getPlayer().setPLayerLIfe(nvite);
                //setta i dimanti a 0
                model.getInstance().getPlayer().setActualShape(0);

                ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getCurrentMission(),
                        model.getInstance().getCampaignMode());

            } else {
                if (loop != null) {
                    ControllerForView.getInstance().getLoop().stop();
                }
                View.getInstance().openMainMenu();
                
            }

        });
    }

    @Override
    public Timeline getLoop() {
        return loop;
    }

    //OTHER METHODS
    private ControllerForView() {
        //this.disablePlayerControl = false;
    }

    public static IControllerForView getInstance() {
        if (instance == null) {
            instance = new ControllerForView();
        }
        return instance;
    }

}
