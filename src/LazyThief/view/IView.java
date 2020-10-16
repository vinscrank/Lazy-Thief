/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

import LazyThief.model.FireBlock;
import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */

//Interfaccia per il Controller.
public interface IView {

    public BorderPane getLayout();

    public GameWindow getWindow();

    public void openStartWindow();

    public void openNewProfileWindow();

    public void openLoadProfileWindow();

    public void openMainMenu();

    public void setCampaignMode(boolean campaignMode);

    public void showErrorDialog(String message);

    public boolean showConfirmationDialog(String message, String title, String firstOption, String secondOption);

    public void prepareGameScreen(StartPosition playerBase, StartPosition terrain);

    public void showInformationDialog(String message, String title);

    public void animationArchery();

 

    public BattleField getBattlefield();

    public ArrayList<ImageView> getPenguins();

    public void killRect(int arrayIndex);

    public void ranking();

    public void DisccopiamentoText(FireBlock n1, FireBlock n2, String str, int index);



   

    

}
