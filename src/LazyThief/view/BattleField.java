/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package LazyThief.view;

import LazyThief.controller.ControllerForView;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import LazyThief.model.FireBlock;

import LazyThief.model.model;

import LazyThief.utils.Config;

import LazyThief.utils.Resources;
import java.awt.Font;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;


import javafx.scene.text.Text;

import javafx.util.Duration;

/**
 *
 * @author franc
 */
public class BattleField extends Pane {

    //public Rectangle rett;
    private Circle cir;

    private final ImageView landscape;
    private Circle playerBullet;
    private ArrayList<ImageView> stickmans;
    private ArrayList<ImageView> fires;
    private ArrayList<Text> viteBlocchi;
    private ArrayList<Circle> rects;
    private ArrayList<Circle> bullets;
    public Text labelStone;
    public Text labelDiamanti;
    public Text numeroLivello;
    public Timeline timelineV1;
    public Timeline timelineV2;
    public Timeline timelineLbl;

    //Setting the angle for the rotation 
    public BattleField() {

        super();

        this.landscape = new ImageView();
        this.landscape.toBack();
        

    }

    public void initBattlefield(StartPosition playerBase, StartPosition terrain) {

        stickmans = new ArrayList<>();

        rects = new ArrayList<>();
        bullets = new ArrayList<>();
        fires = new ArrayList<>();
        viteBlocchi = new ArrayList<>();;

        //prova 
        //setta pietre rimaste
        Rectangle i = new Rectangle(10, 10, 125, 80);
        i.setFill(Color.YELLOW);
        i.setStroke(Color.BLACK);

        i.toBack();
        this.getChildren().add(i);
        ImageView stone = new ImageView(Resources.GeneralImages.BULLET.getImage());
        stone.setX(20);
        stone.setY(20);
        //stone.toBack();
        this.getChildren().add(stone);
        int NVite = model.getInstance().getNumeroVite();
        String str1 = Integer.toString(NVite);
        labelStone = new Text(str1);
        labelStone.setX(100);
        labelStone.setY(60);
        labelStone.setFill(Color.BLACK);
        labelStone.setStyle("-fx-font: 24 arial;");
        this.getChildren().add(labelStone);

        //setta diamanti presi
        Rectangle ie = new Rectangle(180, 10, 125, 80);
        ie.setFill(Color.YELLOW);
        ie.setStroke(Color.BLACK);
        ie.toBack();
        this.getChildren().add(ie);

        ImageView diamante = new ImageView(Resources.GeneralImages.DIAMANTE.getImage());
        diamante.setX(190);
        diamante.setY(30);
        diamante.toBack();
        this.getChildren().add(diamante);
        int NDiamanti = model.getInstance().getDiamanti();
        String str2 = Integer.toString(NDiamanti);
        labelDiamanti = new Text(str2);
        labelDiamanti.setX(260);
        labelDiamanti.setY(60);
        labelDiamanti.setFill(Color.BLACK);
        labelDiamanti.setStyle("-fx-font: 24 arial;");
        this.getChildren().add(labelDiamanti);

        //button ranking
        Button buttonRanking = new Button();
        buttonRanking.setGraphic(new ImageView(Resources.GeneralImages.RANKING.getImage()));

        buttonRanking.setLayoutX(1240);
        buttonRanking.setLayoutY(15);
        this.getChildren().add(buttonRanking);
        buttonRanking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                View.getInstance().ranking();

            }

        });

        //button menu
        Button button5 = new Button();
        button5.setGraphic(new ImageView(Resources.GeneralImages.MENU.getImage()));

        button5.setLayoutX(1380);
        button5.setLayoutY(15);
        this.getChildren().add(button5);

        //pressione bottone uscita
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (ControllerForView.getInstance().getLoop() != null) {
                    ControllerForView.getInstance().getLoop().stop();
                }

                int bonus = model.getInstance().checkForUpdateGameProfiles();

                View.getInstance().openMainMenu();

            }

        });
        //numero livello
        Rectangle livelloNumero = new Rectangle(350, 10, 270, 80);
        livelloNumero.setFill(Color.YELLOW);
        livelloNumero.setStroke(Color.BLACK);
        livelloNumero.toBack();
        this.getChildren().add(livelloNumero);

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

        numeroLivello = new Text("Scenario:" + strLivello);
        // numeroLivello.setId("fancytext");
        numeroLivello.setX(355);
        numeroLivello.setY(60);
        numeroLivello.setFill(Color.BLACK);
        Font font = new Font("Serif", Font.ITALIC, 18);
        numeroLivello.setStyle("-fx-font-size: 40px;");

        
        /*numeroLivello.setStyle("-fx-font: 50px Arial Black;\n"
                + "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n"
                + "    -fx-stroke: black;\n"
                + "    -fx-stroke-width: 1;");*/
        
        this.getChildren().add(numeroLivello);

        //uomino
        ImageView newStick = new ImageView(Resources.GeneralImages.LAZY.getImage());
        //setta coordinate stickman
        newStick.setX(50);
        newStick.setY(250);
        newStick.toBack();
        stickmans.add(newStick);

        playerBase.setFillPattern();
        terrain.setFillPatternTerrain();
        playerBase.setPatternToBattlefield(this);
        terrain.setPatternTerrainToBattlefield(this);

        stickmans.forEach((stick) -> {

            this.getChildren().add(stick);
        });

        caricaLivello();
        //ControllerForView.getInstance().provaB();

    }

    public void caricaLivello() {
        ArrayList<Integer> vite;

        int livelloVita = model.getInstance().getDiamanti();
        if (livelloVita == 0) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV0(vite);

        }
        if (livelloVita == 1) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV1(vite);
        }

        if (livelloVita == 2) {
            vite = model.getInstance().getBlocchiVite();

            addBlockFireLV2(vite);
        }
        if (livelloVita == 3) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV3(vite);
        }
        if (livelloVita == 4) {

            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV4(vite);
        }
        if (livelloVita == 5) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV5(vite);
        }
        if (livelloVita == 6) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV6(vite);
        }
        if (livelloVita == 7) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV7(vite);
        }
        if (livelloVita == 8) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV8(vite);
        }
        if (livelloVita == 9) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV9(vite);
        }
        if (livelloVita == 10) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV10(vite);
        }
        if (livelloVita == 11) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV11(vite);
        }
        if (livelloVita == 12) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV12(vite);
        }
        if (livelloVita == 13) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV13(vite);
        }
        if (livelloVita == 14) {
            vite = model.getInstance().getBlocchiVite();
            addBlockFireLV14(vite);
        }

    
    }

    public Circle getPlayerBullet() {
        return this.playerBullet;
    }

    public void addStone(int n1, int n2) {

        model.getInstance().nuovoRect(n1, n2);
        ImageView img = new ImageView(Resources.GeneralImages.DIAMANTE.getImage());

        cir = new Circle();
        cir.setFill(new ImagePattern(Resources.GeneralImages.DIAMANTE.getImage()));

        cir.setCenterX(n1);
        cir.setCenterY(n2);
        cir.setRadius(20);

        rects.add(cir);
        rects.forEach((nuovoRect) -> {

            this.getChildren().add(nuovoRect);

        });
    }

  

    public void creaProiettile() {

        if (playerBullet != null) {

            this.getChildren().remove(playerBullet);
            playerBullet = null;

        }

        playerBullet = new Circle(Config.getInstance().getPlayerBulletStartXPosition(),
                Config.getInstance().getPlayerBulletStartYPosition(),
                Config.getInstance().getBulletRadius());
        playerBullet.setFill(
                new ImagePattern(Resources.GeneralImages.BULLET.getImage()));
        bullets.add(playerBullet);

        this.getChildren().add(playerBullet);
    }

    public ArrayList<ImageView> getStickmans() {
        return this.stickmans;
    }

    public ArrayList<Circle> getRects() {
        return this.rects;

    }

    public Circle getRect() {
        return this.cir;

    }

    public ArrayList<ImageView> getFires() {
        return this.fires;

    }

    public ArrayList<ImageView> getFire() {
        return this.fires;

    }

    public ArrayList<Text> getViteBlocchi() {
        return this.viteBlocchi;

    }

    public Text getTestoVite() {

        return labelStone;
    }

    public Text getDiamanti() {

        return labelDiamanti;
    }

    public Text getLivello() {

        return numeroLivello;
    }
   

    //aggiunge le pietre che fanno gli ostacoli per la gemma
    public void addBlockFireLV0(ArrayList<Integer> v) {
        //ci va la pietra nel primo IMPORTAnte
        addStone(900, 100);

    }

    public void addBlockFireLV1(ArrayList<Integer> v) {

        addStone(1200, 300);
        //questo non c'era prima 

        //fino a qua
    }

    public void addBlockFireLV2(ArrayList<Integer> v) {
        addStone(900, 700);
    }

    public void addBlockFireLV3(ArrayList<Integer> v) {

        addStone(900, 330);

        posizionaBlocco(0, 600, 300, v);
        posizionaBlocco(1, 1100, 300, v);

        fires.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

        });
        model.getInstance().getFireArray().forEach((nuovo) -> {
            //recupera posizione blocco
            Bounds boundsInScene = nuovo.localToScene(nuovo.getBoundsInLocal());
            int l = nuovo.getLife();
            String str1 = Integer.toString(l);

            Text n = new Text(boundsInScene.getMinX() + 10, boundsInScene.getMinY() + 20, str1);
            n.setFill(Color.BLACK);

            viteBlocchi.add(n);
        });

        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

        });

    }

    public void addBlockFireLV4(ArrayList<Integer> v) {
        fires.forEach((nuovo) -> {
            this.getChildren().remove(nuovo);
            //View.getInstance().animationFire();
        });
        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().remove(nuovo);
        });

        fires.clear();
        viteBlocchi.clear();
        model.getInstance().getFireArray().clear();

        addStone(900, 230);
        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(600, 200, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(600);
            blockFireView2.setY(200);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);

        }

        //ruota
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(1100, 200, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(1100);
            blockFireView3.setY(200);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);

        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(600, 300, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(600);
            blockFireView4.setY(300);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(1100, 300, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(1100);
            blockFireView5.setY(300);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }
        aggiungi();
    }

    public void addBlockFireLV5(ArrayList<Integer> v) {
        //cambio sfondo
        Image a = Resources.GeneralImages.S2.getImage();
        View.getInstance().getWindow().changeB(a);
        pulisci();
        addStone(1300, 380);
        //blocco rosso
        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(500, 200, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(500);
            blockFireView2.setY(200);
            fires.add(blockFireView2);
            b2.setLife(v.get(0));

        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(700, 200, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(700);
            blockFireView3.setY(200);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);

        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(900, 200, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(900);
            blockFireView4.setY(200);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
            //muovi

        }
        //arancione
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(500, 400, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE3.getImage());
            blockFireView5.setX(500);
            blockFireView5.setY(400);
            v.set(3, 1);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
            b5.setMoveLife(true);
            b5.setVerso(2);
            muoviBloccoV2(b5, blockFireView5, b5.viteNumero());
        }
        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(700, 400, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(700);
            blockFireView6.setY(400);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }
        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(900, 400, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView7.setX(900);
            blockFireView7.setY(400);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);
        }

        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(500, 600, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(500);
            blockFireView8.setY(600);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(700, 600, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(700);
            blockFireView9.setY(600);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(1100, 600, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE2.getImage());
            blockFireView10.setX(1100);
            blockFireView10.setY(600);
            v.set(8, 1);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
            b10.setMoveLife(true);
            b10.setVerso(1);
            muoviBloccoV1(b10, blockFireView10, b10.viteNumero());
        }
        aggiungi();
    }

    public void addBlockFireLV6(ArrayList<Integer> v) {
        pulisci();

        addStone(850, 380);
        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(700, 200, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE3.getImage());
            blockFireView2.setX(700);
            blockFireView2.setY(200);
            v.set(0, 1);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);
            b2.setMoveLife(true);
            b2.setVerso(2);
            muoviBloccoV2(b2, blockFireView2, b2.viteNumero());
        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(700, 280, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(700);
            blockFireView3.setY(280);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);
        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(700, 360, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE3.getImage());
            blockFireView4.setX(700);
            blockFireView4.setY(360);
            v.set(2, 1);
            b4.setLife(v.get(2));
            b4.setMoveLife(true);
            b4.setVerso(2);
            muoviBloccoV2(b4, blockFireView4, b4.viteNumero());
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(700, 440, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(700);
            blockFireView5.setY(440);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }
        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(780, 440, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(780);
            blockFireView6.setY(440);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }
        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(860, 440, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView7.setX(860);
            blockFireView7.setY(440);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);

        }
        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(940, 440, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(940);
            blockFireView8.setY(440);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(940, 360, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(940);
            blockFireView9.setY(360);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(940, 280, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView10.setX(940);
            blockFireView10.setY(280);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
        }
        if (v.get(9) > 0) {
            int i10 = 9;
            FireBlock b711 = model.getInstance().nuovoFire(840, 0, i10);
            ImageView blockFireView11 = new ImageView(Resources.fireAnimation.STONE2.getImage());
            blockFireView11.setX(840);
            blockFireView11.setY(0);
            v.set(9,1);
            b711.setLife(v.get(9));
            b711.setMoveLife(true);
            b711.setVerso(1);
            muoviBloccoV1(b711, blockFireView11, b711.viteNumero());
            fires.add(blockFireView11);

        }
        aggiungi();
    }

    public void addBlockFireLV7(ArrayList<Integer> v) {
        pulisci();
        addStone(970, 390);

        aggiungi();
        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(750, 350, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(750);
            blockFireView2.setY(350);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);
        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(830, 270, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(830);
            blockFireView3.setY(270);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);
        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(910, 190, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(910);
            blockFireView4.setY(190);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(910, 510, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(910);
            blockFireView5.setY(510);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }
        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(830, 430, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(830);
            blockFireView6.setY(430);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }
        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(670, 270, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView7.setX(670);
            blockFireView7.setY(270);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);
        }

        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(590, 190, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(590);
            blockFireView8.setY(190);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(670, 430, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(670);
            blockFireView9.setY(430);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(590, 510, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView10.setX(590);
            blockFireView10.setY(510);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
        }

        fires.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });
        model.getInstance().getFireArray().forEach((nuovo) -> {
            //recupera posizione blocco
            Bounds boundsInScene = nuovo.localToScene(nuovo.getBoundsInLocal());
            int l = nuovo.getLife();
            String str1 = Integer.toString(l);

            Text n = new Text(boundsInScene.getMinX() + 10, boundsInScene.getMinY() + 20, str1);
            n.setFill(Color.BLACK);

            viteBlocchi.add(n);
            //View.getInstance().animationFire();
        });

        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

        });
    }

    //aggiunge le pietre che fanno gli ostacoli per la gemma
    public void addBlockFireLV8(ArrayList<Integer> v) {
        //pulisce array prima del nuovo livello
        pulisci();
        addStone(980, 380);

        int x = 750;
        int y = 350;
        //rosso
        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(750, 350, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE2.getImage());
            blockFireView2.setX(750);
            blockFireView2.setY(350);
            v.set(0,1);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);
            b2.setMoveLife(true);
            b2.setVerso(1);
            muoviBloccoV1(b2, blockFireView2, b2.viteNumero());
        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(830, 270, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(830);
            blockFireView3.setY(270);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);
        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(910, 190, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(910);
            blockFireView4.setY(190);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(990, 190, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(990);
            blockFireView5.setY(190);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }
        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(1070, 270, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(1070);
            blockFireView6.setY(270);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }
        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(1150, 350, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE2.getImage());

            blockFireView7.setX(1150);
            blockFireView7.setY(350);
            v.set(5,1);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);
            b7.setMoveLife(true);
            b7.setVerso(1);
            muoviBloccoV1(b7, blockFireView7, b7.viteNumero());
        }

        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(1070, 430, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(1070);
            blockFireView8.setY(430);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(990, 510, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(990);
            blockFireView9.setY(510);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(910, 510, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView10.setX(910);
            blockFireView10.setY(510);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
        }
        if (v.get(9) > 0) {
            int i10 = 9;
            FireBlock b711 = model.getInstance().nuovoFire(830, 430, i10);
            ImageView blockFireView11 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView11.setX(830);
            blockFireView11.setY(430);
            b711.setLife(v.get(9));
            fires.add(blockFireView11);

        }
        aggiungi();

    }

    public void addBlockFireLV9(ArrayList<Integer> v) {
        pulisci();
        addStone(850, 460);

        int x = 750;
        int y = 350;

        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(700, 350, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(700);
            blockFireView2.setY(350);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);
        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(780, 350, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(780);
            blockFireView3.setY(350);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);
        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(860, 350, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(860);
            blockFireView4.setY(350);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(940, 350, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(940);
            blockFireView5.setY(350);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }
        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(940, 430, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(940);
            blockFireView6.setY(430);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }
        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(940, 510, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView7.setX(940);
            blockFireView7.setY(510);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);
        }

        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(860, 510, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(860);
            blockFireView8.setY(510);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(780, 510, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(780);
            blockFireView9.setY(510);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(700, 510, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView10.setX(700);
            blockFireView10.setY(510);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
        }
        if (v.get(9) > 0) {
            int i10 = 9;
            FireBlock b711 = model.getInstance().nuovoFire(700, 430, i10);
            ImageView blockFireView11 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView11.setX(700);
            blockFireView11.setY(430);
            b711.setLife(v.get(9));
            fires.add(blockFireView11);

        }

        fires.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });
        //metti sopra ogbi blocco le vite
        model.getInstance().getFireArray().forEach((nuovo) -> {
            //recupera posizione blocco
            Bounds boundsInScene = nuovo.localToScene(nuovo.getBoundsInLocal());
            int l = nuovo.getLife();
            String str1 = Integer.toString(l);

            Text n = new Text(boundsInScene.getMinX() + 10, boundsInScene.getMinY() + 20, str1);
            n.setFill(Color.BLACK);

            viteBlocchi.add(n);
            //View.getInstance().animationFire();
        });

        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });

    }

    public void addBlockFireLV10(ArrayList<Integer> v) {
        Image a = Resources.GeneralImages.S1.getImage();
        View.getInstance().getWindow().changeB(a);

        pulisci();
        addStone(890, 370);

        int x = 750;
        int y = 350;

        if (v.get(0) > 0) {
            int i1 = 0;
            FireBlock b2 = model.getInstance().nuovoFire(700, 350, i1);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(700);
            blockFireView2.setY(350);
            b2.setLife(v.get(0));
            fires.add(blockFireView2);
        }
        if (v.get(1) > 0) {
            int i2 = 1;
            FireBlock b3 = model.getInstance().nuovoFire(780, 270, i2);
            ImageView blockFireView3 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView3.setX(780);
            blockFireView3.setY(270);
            b3.setLife(v.get(1));
            fires.add(blockFireView3);
        }
        if (v.get(2) > 0) {
            int i3 = 2;
            FireBlock b4 = model.getInstance().nuovoFire(780, 430, i3);
            ImageView blockFireView4 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView4.setX(780);
            blockFireView4.setY(430);
            b4.setLife(v.get(2));
            fires.add(blockFireView4);
        }
        if (v.get(3) > 0) {
            int i4 = 3;
            FireBlock b5 = model.getInstance().nuovoFire(620, 270, i4);
            ImageView blockFireView5 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView5.setX(620);
            blockFireView5.setY(270);
            b5.setLife(v.get(3));
            fires.add(blockFireView5);
        }

        if (v.get(4) > 0) {
            int i5 = 4;
            FireBlock b6 = model.getInstance().nuovoFire(620, 430, i5);
            ImageView blockFireView6 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView6.setX(620);
            blockFireView6.setY(430);
            b6.setLife(v.get(4));
            fires.add(blockFireView6);

        }

        if (v.get(5) > 0) {
            int i6 = 5;
            FireBlock b7 = model.getInstance().nuovoFire(700, 510, i6);
            ImageView blockFireView7 = new ImageView(Resources.fireAnimation.STONE1.getImage());

            blockFireView7.setX(700);
            blockFireView7.setY(510);
            b7.setLife(v.get(5));
            fires.add(blockFireView7);
        }

        if (v.get(6) > 0) {
            int i7 = 6;
            FireBlock b8 = model.getInstance().nuovoFire(700, 190, i7);
            ImageView blockFireView8 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView8.setX(700);
            blockFireView8.setY(190);
            b8.setLife(v.get(6));
            fires.add(blockFireView8);
        }
        if (v.get(7) > 0) {
            int i8 = 7;
            FireBlock b9 = model.getInstance().nuovoFire(860, 350, i8);
            ImageView blockFireView9 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView9.setX(860);
            blockFireView9.setY(350);
            b9.setLife(v.get(7));
            fires.add(blockFireView9);
        }
        if (v.get(8) > 0) {
            int i9 = 8;
            FireBlock b10 = model.getInstance().nuovoFire(540, 350, i9);
            ImageView blockFireView10 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView10.setX(540);
            blockFireView10.setY(350);
            b10.setLife(v.get(8));
            fires.add(blockFireView10);
        }

        fires.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });
        //metti sopra ogbi blocco le vite
        model.getInstance().getFireArray().forEach((nuovo) -> {
            //recupera posizione blocco
            Bounds boundsInScene = nuovo.localToScene(nuovo.getBoundsInLocal());
            int l = nuovo.getLife();
            String str1 = Integer.toString(l);

            Text n = new Text(boundsInScene.getMinX() + 10, boundsInScene.getMinY() + 20, str1);
            n.setFill(Color.BLACK);

            viteBlocchi.add(n);
            //View.getInstance().animationFire();
        });

        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });

    }

    public void addBlockFireLV11(ArrayList<Integer> v) {
        pulisci();
        addStone(850, 460);
        int x = 800;
        int y = 300;
        posizionaBlocco(0, x, y, v);
        posizionaBlocco(1, x, y - 80, v);
        posizionaBlocco(2, x, y + 80, v);
        posizionaBlocco(3, x, y - 160, v);
        posizionaBlocco(4, x, y + 160, v);
        posizionaBlocco(5, x + 80, y - 80, v);
        posizionaBlocco(6, x + 80, y + 80, v);
        posizionaBlocco(7, x + 160, y - 80, v);
        posizionaBlocco(8, x + 160, y + 80, v);
        //posizionaBlocco(1,1100,300,v);

        aggiungi();

    }

    public void addBlockFireLV12(ArrayList<Integer> v) {
        pulisci();
        addStone(800, 400);
        int x = 700;
        int y = 500;
        posizionaBlocco(0, x, y, v);
        posizionaBlocco(1, x + 80, y, v);
        posizionaBlocco(2, x + 160, y, v);
        posizionaBlocco(3, x + 240, y, v);
        posizionaBlocco(4, x + 40, y - 80, v);
        posizionaBlocco(5, x + 120, y - 80, v);
        posizionaBlocco(6, x + 200, y - 80, v);
        posizionaBlocco(7, x + 80, y - 160, v);
        posizionaBlocco(8, x + 160, y - 160, v);
        posizionaBlocco(9, x + 120, y - 240, v);

        aggiungi();

    }

    public void addBlockFireLV13(ArrayList<Integer> v) {
        pulisci();
        addStone(1070, 120);
        int x = 950;
        int y = 100;
        posizionaBlocco(0, x + 80, y + 150, v);
        posizionaBlocco(1, x + 80, y + 300, v);
        posizionaBlocco(2, x + 80, y + 450, v);
        posizionaBlocco(3, x + 80, y + 600, v);

        posizionaBlocco(4, x + 80, y, v);

        //posizionaBlocco(1,1100,300,v);
        aggiungi();

    }

    public void addBlockFireLV14(ArrayList<Integer> v) {
        pulisci();
        addStone(850, 500);
        int x = 500;
        int y = 300;
        posizionaBlocco(0, x, y, v);
        posizionaBlocco(1, x + 150, y + 150, v);
        posizionaBlocco(2, x + 300, y + 300, v);
        posizionaBlocco(3, x + 150, y, v);
        posizionaBlocco(4, x, y - 150, v);
        posizionaBlocco(5, x + 300, y + 150, v);
        posizionaBlocco(6, x + 450, y + 150, v);
        posizionaBlocco(7, x + 450, y, v);
        posizionaBlocco(8, x + 600, y, v);
        posizionaBlocco(9, x + 600, y - 150, v);

        //posizionaBlocco(1,1100,300,v);
        aggiungi();

    }

    public void pulisci() {
        //pulisce array prima del nuovo livello
        fires.forEach((nuovo) -> {

            this.getChildren().remove(nuovo);

        });
        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().remove(nuovo);

        });

        fires.clear();
        viteBlocchi.clear();
        model.getInstance().getFireArray().clear();
    }

    public void aggiungi() {
        fires.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

            //View.getInstance().animationFire();
        });
        //metti sopra ogbi blocco le vite
        model.getInstance().getFireArray().forEach((nuovo) -> {
            //recupera posizione blocco
            Bounds boundsInScene = nuovo.localToScene(nuovo.getBoundsInLocal());
            int l = nuovo.getLife();
            String str1 = Integer.toString(l);
            //double f=(boundsInScene.getMaxY()-boundsInScene.getMaxY())/2;

            Text n = new Text(boundsInScene.getMinX() + 10.0, boundsInScene.getMinY() + 20.0, str1);
            n.setFill(Color.BLACK);

            viteBlocchi.add(n);
            //View.getInstance().animationFire();
            if (nuovo.getMoveLife() == true) {

                if (nuovo.getVerso() == 1) {
                    timelineLbl = new Timeline();
                    timelineLbl.setCycleCount(Timeline.INDEFINITE);
                    timelineLbl.setAutoReverse(true);

                    final KeyValue s = new KeyValue(n.yProperty(), 20 + 200);
                    final KeyFrame kfa = new KeyFrame(Duration.millis(4000), s);
                    timelineLbl.getKeyFrames().add(kfa);
                    timelineLbl.play();
                }
                if (nuovo.getVerso() == 2) {
                    timelineLbl = new Timeline();
                    timelineLbl.setCycleCount(Timeline.INDEFINITE);
                    timelineLbl.setAutoReverse(true);

                    final KeyValue s = new KeyValue(n.xProperty(), 300 + 10);
                    final KeyFrame kfa = new KeyFrame(Duration.millis(4000), s);
                    timelineLbl.getKeyFrames().add(kfa);
                    timelineLbl.play();
                }

            }
        });

        viteBlocchi.forEach((nuovo) -> {

            this.getChildren().add(nuovo);

        });
    }

    public void posizionaBlocco(int indice, int x, int y, ArrayList<Integer> v) {
        if (v.get(indice) > 0) {

            FireBlock b2 = model.getInstance().nuovoFire(x, y, indice);
            ImageView blockFireView2 = new ImageView(Resources.fireAnimation.STONE1.getImage());
            blockFireView2.setX(x);
            blockFireView2.setY(y);
            b2.setLife(v.get(indice));
            fires.add(blockFireView2);

        }

    }

    public void muoviBloccoV1(FireBlock blocco, ImageView img, Text str) {
        timelineV1 = new Timeline();
        timelineV1.setCycleCount(Timeline.INDEFINITE);
        timelineV1.setAutoReverse(true);
        final KeyValue b = new KeyValue(blocco.yProperty(), 200);
        final KeyFrame kf = new KeyFrame(Duration.millis(4000), b);
        timelineV1.getKeyFrames().add(kf);
        final KeyValue i = new KeyValue(img.yProperty(), 200);
        final KeyFrame kfe = new KeyFrame(Duration.millis(4000), i);
        timelineV1.getKeyFrames().add(kfe);

        timelineV1.play();
    }

    public void muoviBloccoV2(FireBlock blocco, ImageView img, Text str) {
        timelineV2 = new Timeline();
        timelineV2.setCycleCount(Timeline.INDEFINITE);
        timelineV2.setAutoReverse(true);
        final KeyValue b = new KeyValue(blocco.xProperty(), 300);
        final KeyFrame kf = new KeyFrame(Duration.millis(4000), b);
        timelineV2.getKeyFrames().add(kf);
        final KeyValue i = new KeyValue(img.xProperty(), 300);
        final KeyFrame kfe = new KeyFrame(Duration.millis(4000), i);
        timelineV2.getKeyFrames().add(kfe);

        timelineV2.play();
    }

}
