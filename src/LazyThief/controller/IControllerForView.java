/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.animation.Timeline;

/**
 *
 * @author User
 */
public interface IControllerForView {

    public Timeline getLoop();

   

    public void createNewGameProfile(String name, int difficultyLevel) throws IOException;

    public LinkedList<String[]> getListOfPlayers() throws IOException, FileNotFoundException;

    public void deleteProfile(int idProfile);

    public void loadGameProfile(int idProfile) throws IOException, FileNotFoundException;

    public int getNumberOfLives();

    public int getActualScore();

    public String getPlayerName();

    public int getRecord();

    public int getPlayerMission();

    public void loadLevel(int level, boolean modality);

    public ArrayList<double[]> getStickmans();

    
    public void shoot(double angle, double speed);

   

    public int getCurrentMission();

    public void platform(String npunti, int nvite);

}
