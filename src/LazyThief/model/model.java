/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.shape.Rectangle;

import LazyThief.utils.Config;
import LazyThief.utils.Constant;
import LazyThief.utils.ReadCSV;
import LazyThief.utils.WriteCSV;

import LazyThief.controller.ControllerForModel;
import LazyThief.controller.ControllerForView;
import LazyThief.utils.Resources;

import LazyThief.view.View;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

/**
 *
 * /**
 *
 * @author User
 */
public class model implements IModel {

    
    

    private static model instance = null;
    private PlayerData playerData;
    private Player player;
    private boolean isCampaign;
    private int level;
    private StartPositionModel playerBase;
    private StartPositionModel terrain;

    private double battlefieldWidth;
    private double battlefieldHeight;

    private double bulletRadius;

    private ArrayList<StickmanModel> stickman_array;
    //private ArrayList<StarModel> star_array;
    private ArrayList<Circle> rectArray;

    private int indexTurn;

    private Circle bullet;

    private Circle gemma;
    private FireBlock blockFire;

    private ArrayList<Circle> bullet_array;
    private ArrayList<FireBlock> fire_array;
    private ArrayList<Integer> vite;
    public int cDaInvertire = 0;

    @Override
    public void setPlayerData() throws IOException {
        LinkedList<String[]> lstRows = null;
        //creazione array

        playerData = new PlayerData();
        if (new File("gameprofiles/saved.csv").exists()) {
            lstRows = ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8");
        } else {
            new File("gameprofiles").mkdir();
            new File("gameprofiles/saved.csv").createNewFile();
        }

        if (lstRows != null) {
            for (String[] currentPlayer : lstRows) {
                vite = new ArrayList<Integer>();
                //System.out.println(Integer.parseInt(currentPlayer[8]));
                vite.add(Integer.parseInt(currentPlayer[8]));
                vite.add(Integer.parseInt(currentPlayer[9]));
                vite.add(Integer.parseInt(currentPlayer[10]));
                vite.add(Integer.parseInt(currentPlayer[11]));
                vite.add(Integer.parseInt(currentPlayer[12]));
                vite.add(Integer.parseInt(currentPlayer[13]));
                vite.add(Integer.parseInt(currentPlayer[14]));
                vite.add(Integer.parseInt(currentPlayer[15]));
                vite.add(Integer.parseInt(currentPlayer[16]));
                vite.add(Integer.parseInt(currentPlayer[17]));

                playerData.add(new Player(Integer.parseInt(currentPlayer[0]),
                        currentPlayer[1], Integer.parseInt(currentPlayer[2]),
                        Integer.parseInt(currentPlayer[3]),
                        Integer.parseInt(currentPlayer[4]),
                        Integer.parseInt(currentPlayer[5]),
                        Integer.parseInt(currentPlayer[6]),
                        Integer.parseInt(currentPlayer[7]),
                        vite
                ));

            }
        }

    }

    

    @Override
    public PlayerData getPlayerData() {
        return this.playerData;
    }

    @Override
    public void deleteProfile(int idProfile) {
        for (Player p : this.playerData.getListOfPlayers()) {
            if (p.getPlayerId() == idProfile) {
                this.playerData.getListOfPlayers().remove(p);
            }
        }
        try {
            WriteCSV.print("gameprofiles/saved.csv", "UTF-8", this.playerData.asListOfStringArray());
        } catch (IOException ioe) {
            ControllerForModel.getInstance().notifyException("Si è verificato un errore: " + ioe);
        }
    }

    @Override
    public void setCurrentPlayer(Player p) {
        this.player = p;
    }

    

    @Override
    public int getPlayerNumberOfLives() {
        return this.player.getPlayerNumberOfLives();
    }

    @Override
    public String getPlayerName() {
        return this.player.getPlayerName();
    }

    @Override
    public int getPlyerVite() {
        return this.player.getPlayerNumberOfLives();
    }

   

    @Override
    public int getPlayerCurrentLevel() {
        return this.player.getCurrentMission();
    }

    @Override
    public void init(int level, boolean isCampaign, StartPositionModel playerBase, StartPositionModel terrain) {

        this.isCampaign = isCampaign;
        this.level = level;
        setupConfiguration();
        this.playerBase = playerBase;
        this.terrain = terrain;
        bullet_array = new ArrayList<>();

        stickman_array = new ArrayList<>();
        //this.star_array =new ArrayList<>();
        this.rectArray = new ArrayList<>();
        this.fire_array = new ArrayList<>();

        this.indexTurn = 0;

        //Sarebbe opportuno recuperare questi dati da un file di configurazione.
        //definisce come è fatta
        this.bullet = new Circle(Config.getInstance().getPlayerBulletStartXPosition(),
                Config.getInstance().getPlayerBulletStartYPosition(),
                this.bulletRadius
        );

        StickmanModel stickman = new StickmanModel(Config.getInstance().getAlliedPenguinsStartXPosition(),
                Config.getInstance().getPenguinsStartYPosition(), //Penguin Position
                //Penguin Life Points
                true); //Allied Penguin
        this.stickman_array.add(stickman);

    }

    private void setupConfiguration() {
        this.bulletRadius = Config.getInstance().getBulletRadius();

        this.battlefieldWidth = Config.getInstance().getBattlefieldWidth();
        this.battlefieldHeight = Config.getInstance().getBattlefieldHeight();

    }

 

    @Override
    public ArrayList<StickmanModel> getPenguins() {
        return this.stickman_array;
    }

   
    @Override
    public ArrayList<Circle> getRects() {
        return this.rectArray;
    }

   

  

    @Override
    public Player getPlayer() {
        return this.player;
    }

   

   

    

    @Override
    public boolean isBulletGetSceneryLimits() {
        //double startXPosition = this.getBulletXStartPosition();
        double startYPosition = this.getBulletYStartPosition();

        //double xLandscapeBound = this.battlefieldWidth - this.bulletRadius;
        double yLandscapeBound = this.battlefieldHeight - this.bulletRadius;

        //boolean minLimitReach = startYPosition + this.bullet.getLayoutY()< 0 || startXPosition + this.bullet.getLayoutX()< 0;
        boolean maxLimitReach = startYPosition + this.bullet.getLayoutY() > yLandscapeBound;
        return maxLimitReach;
    }

  

    @Override
    public double getBulletXStartPosition() {
        return this.bullet.getCenterX();
    }

    @Override
    public double getBulletYStartPosition() {
        return this.bullet.getCenterY();
    }

    
    /*controlla se la pallina intercetta una delle pietre azzurre durante il suo tragitto e provvede a 
incrementare il punteggio del giocatore nel model  Richiama metodi della classe View per aggiornare la grafica.*/

    @Override
    public boolean stoneHit() {

        boolean collision = false;
        Rectangle rect;
        int i = 0;
        while (i < rectArray.size() && !collision) {
            gemma = rectArray.get(i);
            if ((gemma.intersects(View.getInstance().getBattlefield().getPlayerBullet().getBoundsInParent()))) {

                // stickman.decreaseStickmanLife(bullet);
                collision = true;
                model.getInstance().aumentaDiamanti();

                int NDiamanti = model.getInstance().getDiamanti();
                String str1 = Integer.toString(NDiamanti);
                View.getInstance().getBattlefield().getDiamanti().setText(str1);
                ControllerForModel.getInstance().notifyDead(i);
                //aggiorna numero scenario
                int nLivello = model.getInstance().getDiamanti();
                String strLivello = "";
                if ((nLivello >= 0) && (nLivello <= 4)) {
                    strLivello = "1";
                }
                if ((nLivello >= 5) && (nLivello <= 9)) {
                    strLivello = "2";
                }
                if ((nLivello >= 10) && (nLivello <= 14)) {
                    strLivello = "3";
                }
                View.getInstance().getBattlefield().getLivello().setText("Scenario:" + strLivello);
            }
            i++;
        }

        return collision;
    }
/*Il metodo blockHit() rileva le collisioni tra la pallina e i blocchi e utilizza il metodo invertiC() 
per capire in che modo deve rimbalzare la pallina a seconda del punto in cui viene colpito il blocco. 
Controlla se le vite del blocco sono terminate ed eventualmente lo rimuove dall’array fire_array e dalla grafica richiamando un metodo della classe View.*/

    @Override
    public boolean blockHit() {

        boolean collision = false;
        FireBlock rect;
        int i = 0;
        while (i < this.fire_array.size() && !collision) {
            this.blockFire = fire_array.get(i);
            

            if (blockFire.getBoundsInParent().intersects(this.bullet.getBoundsInParent())) {
                if (invertiC() == 1 || invertiC() == 2 || invertiC() == 3 || invertiC() == 4) {
                    cDaInvertire = invertiC();

                    //dimisce vite blocco
                    this.blockFire.decrementLife();
                    //salva nella memoria del player le vite del blocco

                    int index = blockFire.getIndex();

                    this.player.arrayVite().set(index, ((this.player.arrayVite().get(index)) - 1));
                    try {
                        WriteCSV.print("gameprofiles/saved.csv", "UTF-8", this.playerData.asListOfStringArray());
                    } catch (IOException ioe) {
                        ControllerForModel.getInstance().notifyException("Si è verificato un errore: " + ioe);
                    }

                    View.getInstance().getBattlefield().getChildren().remove(View.getInstance().getBattlefield().getViteBlocchi().get(i));
                    View.getInstance().getBattlefield().getViteBlocchi().remove(View.getInstance().getBattlefield().getViteBlocchi().get(i));
                    //aggiorna numero vite
                    String str = Integer.toString(this.blockFire.getLife());

                    View.getInstance().DisccopiamentoText(this.blockFire, this.blockFire, str, i);

                    //aggiorna numero vite
                    if (this.blockFire.getLife() == 0) {
                        this.blockFire = null;
                        fire_array.remove(i);
                        //View.getInstance().getBattlefield().getFires().
                        //rimuove blocco
                        View.getInstance().getBattlefield().getChildren().remove(View.getInstance().getBattlefield().getFires().get(i));
                        View.getInstance().getBattlefield().getFires().remove(i);
                        View.getInstance().getBattlefield().getChildren().remove(View.getInstance().getBattlefield().getViteBlocchi().get(i));
                        View.getInstance().getBattlefield().getViteBlocchi().remove(View.getInstance().getBattlefield().getViteBlocchi().get(i));

                    }

                    collision = true;
                }
            }

            i++;
        }

        return collision;

    }

    /*Il metodo checkForUpdateGameProfiles() controlla se il giocatore ha totalizzato un record migliore rispetto ai suoi record precedenti in questi due casi
se ha terminato le vite 
se è riuscito a completare tutti gli scanri

*/
    @Override
    public int checkForUpdateGameProfiles() {
        int bonusScore = 0;
        int punti = model.getInstance().getDiamanti();

        String str1 = Integer.toString(punti);

        int numberOfLives = Constant.HARD_LEVEL_NUMBER_OF_LIVES;
        if ((this.player.hasPlayerTerminatedLives()) || (str1.equals("15"))) {

            //setta il record
            if (punti > this.player.getRecord()) {
                this.player.setRecord(punti);
                //this.player.viteFinali=this.player.getPlayerNumberOfLives();
            }

            String scoreInformation = new StringBuilder().append("\nPunti ottenuti: ").append(punti).toString();
            ControllerForView.getInstance().platform(scoreInformation, numberOfLives);

        }
        return bonusScore;

    }

    @Override
    public int getRecord() {
        return this.player.getRecord();
    }

    @Override
    public int getNumeroVite() {
        return this.player.getPlayerNumberOfLives();
    }

    @Override
    public int getDiamanti() {
        return this.player.getActualShape();
    }

    @Override
    public ArrayList<Integer> getBlocchiVite() {
        return this.player.arrayVite();
    }

    @Override
    public void diminuisciVite() {

        this.player.decreaseNumberOfLives();

        try {
            WriteCSV.print("gameprofiles/saved.csv", "UTF-8", playerData.asListOfStringArray());
        } catch (IOException ioe) {
            ControllerForModel.getInstance().notifyException(ioe.toString());
        }

    }

    @Override
    public void aumentaDiamanti() {

        this.player.incrementActualShape();

        try {
            WriteCSV.print("gameprofiles/saved.csv", "UTF-8", playerData.asListOfStringArray());
        } catch (IOException ioe) {
            ControllerForModel.getInstance().notifyException(ioe.toString());
        }

    }

    @Override
    public int getPlayerDifficultyLevel() {
        return this.player.getDifficultyLevel();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public Rectangle getBlockfire() {
        return this.blockFire;
    }

    @Override
    public boolean getCampaignMode() {
        return this.isCampaign;
    }

    @Override
    public Circle getPlayerBullet() {
        return this.bullet;
    }

    @Override
    public FireBlock nuovoFire(double doubleX, double doubleY, int index) {

        blockFire = new FireBlock(doubleX, doubleY, 80, 80, index);
        blockFire.setLife(this.player.arrayVite().get(index));
        this.fire_array.add(blockFire);

        //View.getInstance().downStar();
        return blockFire;
    }

    @Override
    public void nuovoRect(double doubleX, double doubleY) {

        gemma = new Circle();
        gemma.setCenterX(doubleX);
        gemma.setCenterY(doubleY);
        gemma.setRadius(20.0f);

        this.rectArray.add(gemma);

    }

    @Override
    public void creaProiettileModel() {

        if (bullet != null) {

            bullet = null;

        }

        bullet = new Circle(Config.getInstance().getPlayerBulletStartXPosition(),
                Config.getInstance().getPlayerBulletStartYPosition(),
                Config.getInstance().getBulletRadius());
        this.bullet_array.add(bullet);

    }

    @Override
    public int invertiC() {
        int b = 0;

        Bounds rectangleBounds = model.getInstance().getBlockfire().getLayoutBounds();

        boolean rectangle_left = ((getPlayerBullet().getLayoutX()) + 85) <= (rectangleBounds.getMinX());

        boolean rectangle_top = (getPlayerBullet().getLayoutY() + 305) <= (rectangleBounds.getMinY());

        boolean rectangle_bottom = (getPlayerBullet().getLayoutY() + 305) >= (rectangleBounds.getMaxY());

        boolean rectangle_right = (getPlayerBullet().getLayoutX() + 85) >= (rectangleBounds.getMaxX());
        Resources.SoundEffects.BOUNCE.play();

        if (rectangle_left) {
            rectangle_right = false;
            rectangle_bottom = false;
            rectangle_top = false;

            b = 1;
        } else {
            if (rectangle_right) {
                rectangle_left = false;
                rectangle_bottom = false;
                rectangle_top = false;
                b = 2;
            } else {
                if (rectangle_top) {
                    rectangle_right = false;
                    rectangle_bottom = false;
                    rectangle_left = false;
                    b = 3;
                } else {
                    if (rectangle_bottom) {
                        rectangle_right = false;
                        rectangle_bottom = false;
                        rectangle_left = false;
                        b = 4;

                    }
                }
            }
        }

        return b;
    }

    @Override
    public ArrayList<FireBlock> getFireArray() {
        return this.fire_array;
    }

    @Override
    public int getcDaInvertire() {
        return this.cDaInvertire;
    }

   

    //OTHER METHODS
    public static IModel getInstance() {
        if (instance == null) {
            instance = new model();
        }
        return instance;
    }

}
