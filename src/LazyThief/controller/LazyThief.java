package LazyThief.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import LazyThief.view.View;

/**
 *
 * @author franc
 */
public class LazyThief extends Application {

    @Override
    public void start(Stage primaryStage) {

        View.getInstance().openStartWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
