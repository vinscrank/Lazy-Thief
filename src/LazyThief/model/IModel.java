/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



/**
 *
 * @author User
 */
public interface IModel {

    public int getcDaInvertire();

   

    public void setPlayerData() throws IOException, FileNotFoundException;

    public PlayerData getPlayerData();

    public Rectangle getBlockfire();

    public void deleteProfile(int idProfile);

    public void setCurrentPlayer(Player p);


    public int getPlayerNumberOfLives();

    public String getPlayerName();


    public int getPlayerCurrentLevel();

    public void init(int level, boolean isCampaign, StartPositionModel playerBase, StartPositionModel terrain);
    //public void setupResults();

   

    public ArrayList<StickmanModel> getPenguins();

  

    public boolean isBulletGetSceneryLimits();

    public double getBulletXStartPosition();

    public double getBulletYStartPosition();



    public int getPlayerDifficultyLevel();

    public int getLevel();

    public boolean getCampaignMode();

    public Circle getPlayerBullet();

    public boolean stoneHit();

    public void nuovoRect(double doubleX, double doubleY);

    public ArrayList<Circle> getRects();

    

    public void creaProiettileModel();

    public FireBlock nuovoFire(double doubleX, double doubleY, int index);

    public boolean blockHit();

    public ArrayList<FireBlock> getFireArray();

    public int checkForUpdateGameProfiles();

    public void diminuisciVite();

    public int getNumeroVite();

    public int getDiamanti();

    public void aumentaDiamanti();

    public int getRecord();

    public int invertiC();

    

    public ArrayList<Integer> getBlocchiVite();

    public int getPlyerVite();

    public Player getPlayer();
}
