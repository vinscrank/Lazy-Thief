/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package LazyThief.view;

import LazyThief.controller.ControllerForModel;
import LazyThief.model.FireBlock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;

import LazyThief.utils.Resources;
import java.util.concurrent.atomic.AtomicBoolean;

import LazyThief.model.Player;
import LazyThief.model.PlayerData;
import LazyThief.model.model;
import LazyThief.utils.WriteCSV;

import java.util.LinkedList;

import javafx.animation.KeyValue;

import javafx.geometry.Pos;

import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Region;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javafx.stage.StageStyle;

/**
 *
 * @author franc
 */
public class View implements IView {

    //STATIC FIELDS
    //--------------------------------------------------------------------------
    private final static int START_WINDOW_WIDTH = 800;
    private final static int START_WINDOW_HEIGHT = 600;

    private final static int NEW_PROFILE_WINDOW_WIDTH = 550;
    private final static int NEW_PROFILE_WINDOW_HEIGHT = 215;

    private final static int LOAD_PROFILE_WINDOW_WIDTH = 600;
    private final static int LOAD_PROFILE_WINDOW_HEIGHT = 450;

    private final static int MAIN_MENU_WINDOW_WIDTH = 550;
    private final static int MAIN_MENU_WINDOW_HEIGHT = 350;


    //INSTANCE FIELDS
    //--------------------------------------------------------------------------
    private static View instance = null;
    public GameWindow gameWindow = null;
    private Stage shownWindow;
    private boolean campaignMode;
 
     public  Timeline time;

    //--------------------------------------------------------------------------
    //METHODS AREA
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //Methods used in order to manage the transition between different game screens.
    private void prepareSceneToShowWindow(String xmlSrc, String title, int windowWidth, int windowHeight) {
        closeGameWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(xmlSrc));
            shownWindow.getIcons().add(Resources.GeneralImages.ICON.getImage());
            shownWindow.setScene(new Scene(root, windowWidth, windowHeight));
            shownWindow.setTitle(title);
            shownWindow.show();
            shownWindow.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void openStartWindow() {
        prepareSceneToShowWindow("MainWindow.fxml",
                "StickmanArcher - Java Edition",
                START_WINDOW_WIDTH,
                START_WINDOW_HEIGHT);
    }

    private void closeGameWindow() {
        if (this.gameWindow != null) {
            Resources.Music.SOUNDTRACK.stop();
            gameWindow.close();
            gameWindow = null;
        }
    }

    @Override
    public void openNewProfileWindow() {
        this.prepareSceneToShowWindow("NewProfileWindow.fxml",
                "Creazione di un nuovo profilo di gioco",
                NEW_PROFILE_WINDOW_WIDTH,
                NEW_PROFILE_WINDOW_HEIGHT);
    }

    @Override
    public void openLoadProfileWindow() {
        prepareSceneToShowWindow("LoadProfileWindow.fxml",
                "Selezione del profilo di gioco",
                LOAD_PROFILE_WINDOW_WIDTH,
                LOAD_PROFILE_WINDOW_HEIGHT);
    }

    //metodi messaggi
    @Override
    public void showInformationDialog(String message, String title) {

        Alert dialogWindow = new Alert(AlertType.INFORMATION);
        ((Stage) dialogWindow.getDialogPane().getScene().getWindow()).getIcons()
                .add(Resources.GeneralImages.ICON.getImage());
        dialogWindow.setTitle(title);
        dialogWindow.setContentText(message);
        dialogWindow.showAndWait();

    }

    @Override
    public boolean showConfirmationDialog(String message, String title, String firstOption, String secondOption) {
        AtomicBoolean userChoose = new AtomicBoolean();
        Alert dialogWindow = new Alert(AlertType.INFORMATION);
        dialogWindow.setTitle(title);
        dialogWindow.setHeaderText(null);
        dialogWindow.setContentText(message);
        ((Stage) dialogWindow.getDialogPane().getScene().getWindow()).getIcons()
                .add(Resources.GeneralImages.ICON.getImage());

        ButtonType opt1 = new ButtonType(firstOption);
        ButtonType opt2 = new ButtonType(secondOption);

        dialogWindow.getButtonTypes().setAll(opt1, opt2);

        Optional<ButtonType> result = dialogWindow.showAndWait();
        userChoose.set(result.get() == opt1);

        dialogWindow.close();
        return userChoose.get();
    }

    @Override
    public void showErrorDialog(String message) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Si è verificato un errore...");
        error.setHeaderText("Si è verificato l'errore:");
        error.setContentText(message);
        error.show();
    }

    @Override
    public void openMainMenu() {
        prepareSceneToShowWindow("GameMenu.fxml",
                "Menu Principale",
                MAIN_MENU_WINDOW_WIDTH,
                MAIN_MENU_WINDOW_HEIGHT);
    }

    @Override
    public void setCampaignMode(boolean campaignMode) {
        this.campaignMode = campaignMode;
    }

    @Override
    public void prepareGameScreen(StartPosition playerBase, StartPosition terrain) {
        closeGameWindow();
        this.gameWindow = new GameWindow();
        //stage bianco
        gameWindow.initStyle(StageStyle.DECORATED);
        gameWindow.getIcons().add(Resources.GeneralImages.ICON.getImage());
        this.gameWindow.getBattlefield().initBattlefield(playerBase, terrain);
        Resources.Music.SOUNDTRACK.play();
        gameWindow.show();
        shownWindow.setScene(null);
        shownWindow.hide();

    }

    @Override
    public void animationArchery() {
        ImageView penguinToKill = this.gameWindow.getBattlefield().getStickmans().get(0);
        String penguinType = "";
        final Image[] deathAnimationImages = new Image[6];
        int frame = 0;
        for (Resources.stickmanAnimation currentFrame : Resources.stickmanAnimation.values()) {
            deathAnimationImages[frame] = currentFrame.getImage();
            frame++;
        }

        Duration frameDuration = Duration.seconds(0.5d / deathAnimationImages.length); // 1 second for complete animation

        Timeline deathAnimation = new Timeline(new KeyFrame(frameDuration, new EventHandler<ActionEvent>() {
            private int index = 0;

            @Override
            public void handle(ActionEvent event) {
                penguinToKill.setImage(deathAnimationImages[index]);
                index++;
            }
        }));
        deathAnimation.setCycleCount(deathAnimationImages.length);
        deathAnimation.setOnFinished(event -> {

        });
        deathAnimation.play();
    }

   

 
  
    
    @Override
    public void killRect(int arrayIndex) {

        //ImageView penguinToKill = this.gameWindow.getBattlefield().getStickmans().get(arrayIndex);
        //String penguinType = "";
        Circle penguinToKill = this.gameWindow.getBattlefield().getRects().get(arrayIndex);
        //penguinToKill.setImage(Resources.starAnimation.STAR1.getImage());
        // this.gameWindow.getBattlefield().getStars().remove(arrayIndex);
        //this.gameWindow.getBattlefield().getChildren().remove(penguinToKill);
        Resources.SoundEffects.COIN.play();
        this.gameWindow.getBattlefield().getPlayerBullet().setVisible(false);
        this.gameWindow.getBattlefield().getChildren().remove(this.gameWindow.getBattlefield().getPlayerBullet());
        //deathAnimation.stop();

        this.gameWindow.getBattlefield().getRects().remove(arrayIndex);
        this.gameWindow.getBattlefield().getChildren().remove(penguinToKill);
        model.getInstance().getRects().remove(arrayIndex);
        model.getInstance().getRects().clear();

        //riporta a 3 le vite di tutti i blocchi
        model.getInstance().getBlocchiVite().clear();
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);
        model.getInstance().getBlocchiVite().add(3);

        try {
            WriteCSV.print("gameprofiles/saved.csv", "UTF-8", model.getInstance().getPlayerData().asListOfStringArray());
        } catch (IOException ioe) {
            ControllerForModel.getInstance().notifyException("Si è verificato un errore: " + ioe);
        }
        //lo inserisco per verificare che non abbia vinto       
        int bonus = model.getInstance().checkForUpdateGameProfiles();
        this.gameWindow.getBattlefield().caricaLivello();

    }

    @Override
    public BattleField getBattlefield() {
        return this.gameWindow.getBattlefield();
    }
    
    @Override
    public BorderPane getLayout() {
       return this.gameWindow.gameWindowLayout;
    }
    
    @Override
    public GameWindow getWindow() {
       return this.gameWindow;
    }
    
   

    @Override
    public ArrayList<ImageView> getPenguins() {
        return this.gameWindow.getBattlefield().getStickmans();
    }

  

   

    @Override
    public void ranking() {

        TableView tableView = new TableView();
        Button b = new Button("MENU PRINCIPALE");

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                View.getInstance().openMainMenu();
            }
        });
        tableView.setPrefSize(800, 600);
        tableView.setMaxWidth(Region.USE_PREF_SIZE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<String, Person> column0 = new TableColumn<>("Posizione");
        column0.setCellValueFactory(new PropertyValueFactory<>("posizione"));

        TableColumn<String, Person> column1 = new TableColumn<>("NomeGiocatore");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, Person> column2 = new TableColumn<>("Record");
        column2.setCellValueFactory(new PropertyValueFactory<>("record"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        //recupera dati giocatori
        int index = 0;
        PlayerData a = model.getInstance().getPlayerData();
        LinkedList<Player> lista = a.getListOfPlayers();

        String[] sArr = null;
        for (Player p : lista) {
            sArr = new String[2];
            index++;

            sArr[0] = p.getPlayerName();
            sArr[1] = String.valueOf(p.getRecord());
            //System.out.println("il record di "+ p.getPlayerName() +"è "+ p.getRecord());

            int recordINT = Integer.parseInt(sArr[1]);

            tableView.getItems().add(new Person(sArr[0], recordINT));

        }

        column2.setComparator(column2.getComparator().reversed());
        tableView.getSortOrder().add(column2);
        //column2.setComparator(column2.getComparator().reversed());
        //tableView.getSortOrder().add(column2);

        VBox vbox = new VBox(tableView);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(b);

        Scene scene = new Scene(vbox);

        shownWindow.setScene(scene);

        shownWindow.show();

    }

    @Override
    public void DisccopiamentoText(FireBlock n1, FireBlock n2, String str, int index) {
        
        Text n = new Text(n1.getBoundsInLocal().getMinX()+10, n1.getBoundsInLocal().getMinY()+20, str);
        n.setFill(Color.BLACK);
        View.getInstance().getBattlefield().getViteBlocchi().add(index, n);
        View.getInstance().getBattlefield().getChildren().add(n);
        if (n1.getMoveLife() == true) {
                
               
                time = new Timeline();
                time.setCycleCount(Timeline.INDEFINITE);
                time.setAutoReverse(true);

                final KeyValue s = new KeyValue(n.yProperty(),20+300);
                final KeyFrame kfa = new KeyFrame(Duration.millis(4000), s);
                time.getKeyFrames().add(kfa);
                time.play();
            }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //Other Methods
    private View() {
        shownWindow = new Stage();
    }

    public static IView getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

}
