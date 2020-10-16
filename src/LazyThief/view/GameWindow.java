package LazyThief.view;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import LazyThief.controller.ControllerForView;
import LazyThief.model.model;
import LazyThief.utils.Resources;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 *
 * @author franc
 */
public class GameWindow extends Stage {

    private BattleField battlefield = null;
    public BorderPane gameWindowLayout;
    private PlayerControlPanel playerData;
   
    private double lenghtLine;

    private Pair<Double, Double> initialTouch;
    private Pair<Double, Double> rectLineTouch;
    private Pair<Double, Double> finalTouch;
    private Canvas layer;
    public Canvas canvas;

    public GameWindow() {

        super();
        battlefield = new BattleField();

        //crea il canvas in battlefield che è un gruop
        rectLine();

        gameWindowLayout = new BorderPane();

        
        Image a = Resources.GeneralImages.S3.getImage();
        changeB(a);

        //playerData = new PlayerControlPanel();
        gameWindowLayout.setCenter(battlefield);
        //gameWindowLayout.setBottom(playerData);
        //SETTA IL COLORE DELLA SCENA
        Scene scene = new Scene(gameWindowLayout, 1500, 900, Color.BLACK);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //controlla se sono finite le gemme
                int bonus = model.getInstance().checkForUpdateGameProfiles();
                int vittoria = model.getInstance().getDiamanti();

            }
        });

        this.setScene(scene);

        this.setTitle("Stickman Archer - Java Edition");
        this.setResizable(false);

    }

    public BattleField getBattlefield() {
        return this.battlefield;
    }

    public PlayerControlPanel getPlayerData() {
        return this.playerData;
    }
    //METODO CHE FA TRAETTORIA

    public void rectLine() {

        canvas = new Canvas(1500, 900);

        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        initDraw(graphicsContext);
        //mouse premuto
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                Canvas newLayer = new Canvas(1500, 900);

                GraphicsContext context = newLayer.getGraphicsContext2D();

                initDraw(context);

                layer = newLayer;
                battlefield.getChildren().add(0, newLayer);
                initialTouch = new Pair<>(100.0, 350.0);

            }
        });
        //mouse tracsinato
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                GraphicsContext context = layer.getGraphicsContext2D();
                context.clearRect(0, 0, layer.getWidth(), layer.getHeight());

                context.setStroke(Color.BLACK);
                context.strokeLine(initialTouch.getKey(), initialTouch.getValue(), event.getSceneX(), event.getSceneY());
                finalTouch = new Pair<>(event.getSceneX(), event.getSceneY());
                //System.out.println("X"+event.getSceneX());

                //System.out.println("Y"+event.getSceneY());
                //metodi che servono per calcolare la lunghezza della linea
                double newCoordinates = (finalTouch.getKey() - initialTouch.getKey());
                double newCoordinates2 = (finalTouch.getValue() - initialTouch.getValue());
                //mette le coordinate in modulo
                if (newCoordinates2 < 1) {
                    newCoordinates2 = newCoordinates2 * (-1);
                }
                if (newCoordinates < 1) {
                    newCoordinates = newCoordinates * (-1);
                }
                //calcola lunghezza lati con teorema di pitagora
                lenghtLine = Math.sqrt((newCoordinates * (newCoordinates) + ((newCoordinates2) * (newCoordinates2))));
                //System.out.println(lenghtLine);

            }
        });
        //mouse rilasciato
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //avvia animazione per lancio freccia
                View.getInstance().animationArchery();

                //introduce un ritardo del thread principale
                GraphicsContext context = layer.getGraphicsContext2D();
                context.clearRect(0, 0, layer.getWidth(), layer.getHeight());

                //recupero angolo
                rectLineTouch = new Pair<>(210.0, 400.0);
                Line HorizontalLine = new Line();

                HorizontalLine.setStartX(initialTouch.getKey());
                //System.out.println("INIZIO X linea retta"+initialTouch.getKey());

                HorizontalLine.setStartY(initialTouch.getValue());
                //System.out.println(" INIZIO y linea retta"+initialTouch.getValue());

                HorizontalLine.setEndX(rectLineTouch.getKey());

                //System.out.println(" FINE X linea retta"+rectLineTouch.getKey());
                HorizontalLine.setEndY(rectLineTouch.getValue());
                // System.out.println(" FINE Y linea retta"+rectLineTouch.getValue());
                //System.out.println(rectLineTouch.getValue());
                //context.setLineDashOffset(5.0);
                context.setLineDashes(2);
                Line ObliqueLine = new Line();

                ObliqueLine.setStartX(initialTouch.getKey());
                // System.out.println(" INIZIO x linea obliqua"+initialTouch.getKey());
                ObliqueLine.setStartY(initialTouch.getValue());
                //System.out.println(" INIZIO Y linea obliqua"+initialTouch.getValue());
                ObliqueLine.setEndX(finalTouch.getKey());
                // System.out.println(" FINE X linea obliqua"+finalTouch.getKey());
                ObliqueLine.setEndY(finalTouch.getValue());
                //System.out.println(" FINE Y linea obliqua"+finalTouch.getValue());
                ObliqueLine.setStrokeWidth(10);

                //double m1 =  (rectLineTouch.getKey()-initialTouch.getKey()/(rectLineTouch.getValue()-initialTouch.getValue()));
                //ricava pendenza retta
                double m2 = (finalTouch.getValue() - initialTouch.getValue()) / (finalTouch.getKey() - initialTouch.getKey());
                //sistema tutte le pendenze
                m2 = m2 * (-1);

                // System.out.println("coefficenete "+m2);
                lenghtLine = lenghtLine / 50;
                // System.out.println("lunghezza "+lenghtLine);

                double A = Math.atan(m2);

                //questo metodo fa scoccare la freccia
                ShootMouseRelased(A);
                if (model.getInstance().getPlyerVite() > 0) {
                    model.getInstance().diminuisciVite();

                    //aggiorna il numero di pietre rimanenti e label
                    int NVite = model.getInstance().getNumeroVite();
                    String str1 = Integer.toString(NVite);
                    View.getInstance().getBattlefield().getTestoVite().setText(str1);
                }

                // View.getInstance().initArrow();
            }
        });

        battlefield.getChildren().add(canvas);

    }

    private void initDraw(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.fill();
        gc.strokeRect(
                0, //x of the upper left corner
                0, //y of the upper left corner
                canvasWidth, //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);

    }

    private void ShootMouseRelased(double angle) {
        //due metodi per scoccare la freccia: uno di view w uno di model
        battlefield.creaProiettile();
        model.getInstance().creaProiettileModel();


        // la potenza è il secondo parametro e deve aumentare linearmente con il mouse
        ControllerForView.getInstance().shoot(angle,
                lenghtLine
                
                );

        Resources.SoundEffects.CANNON.play();

    }

    public void changeB(Image a) {
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        gameWindowLayout.setBackground(new Background(new BackgroundImage(a, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

    }

}
