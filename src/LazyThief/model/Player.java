/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


/**
 *
 * @author franc
 */
public class Player {

    private int playerId;
    private String playerName;
    private int playerActualScore;
    private int playerNumberOfLives;
    private int currentMission;
    private int difficultyLevel;
    private int actualShape;
    private int record;
    public int viteFinali;
    private ArrayList<Integer> arl;

    public Player(int playerId, String playerName, int playerActualScore, int playerNumberOfLives, int difficultyLevel, int currentMission, int diamonds, int record, ArrayList<Integer> vite) {
        arl = new ArrayList<Integer>();

        this.playerId = playerId;
        this.playerName = playerName;
        this.playerActualScore = playerActualScore;
        this.playerNumberOfLives = playerNumberOfLives;
        this.currentMission = currentMission;
        this.difficultyLevel = difficultyLevel;
        this.actualShape = diamonds;
        this.record = record;
        this.arl = vite;

    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setActualShape(int a) {
        this.actualShape = a;
    }

    public void setPLayerLIfe(int a) {
        this.playerNumberOfLives = a;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void incrementActualShape() {
        this.actualShape++;
    }

    public int getActualShape() {
        return this.actualShape;
    }

    public int getplayerActualScore() {
        return this.playerActualScore;
    }

    public int getPlayerNumberOfLives() {
        return this.playerNumberOfLives;
    }

  


    public void decreaseNumberOfLives() {
        this.playerNumberOfLives--;
    }

    public boolean hasPlayerTerminatedLives() {
        return this.playerNumberOfLives <= 0;
    }

    public int getCurrentMission() {
        return this.currentMission;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getRecord() {
        return this.record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

   
    public ArrayList<Integer> arrayVite() {
        return this.arl;
    }

}
