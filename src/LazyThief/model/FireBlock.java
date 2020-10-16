/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author User
 */
public class FireBlock extends Rectangle {

    int index;
    int life;
    public Text text;

    public boolean moveLife = false;
    int verso;

    public FireBlock(double startXPosition, double startYPosition, double dim1, double dim2, int index) {

        super(startXPosition, startYPosition, dim1, dim2);
        life = 3;
        String lifeString = Integer.toString(life);;
        text = new Text(startXPosition + 10, startYPosition + 20, lifeString);
        text.setFill(Color.BLACK);
        this.index = index;

    }

    public void setLife(int l) {
        life = l;
    }

    public int getLife() {
        return life;
    }

    public void decrementLife() {
        life = life - 1;
    }

    public Text viteNumero() {
        return text;
    }

    public int getIndex() {
        return index;

    }

    public boolean getMoveLife() {
        return moveLife;
    }

    public void setMoveLife(boolean v) {
        this.moveLife = v;
    }

    public void setVerso(int v) {
        this.verso = v;
    }

    public int getVerso() {
        return verso;
    }
}
