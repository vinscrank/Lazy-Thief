/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public abstract class StartPositionModel {

    protected ArrayList<Rectangle> pattern;
    protected ArrayList<Rectangle> patternTerrain;

    protected double startXPosition;
    protected double startYPosition;

    public StartPositionModel(double startXPosition, double startYPosition) {
        pattern = new ArrayList<>();
        patternTerrain = new ArrayList<>();
        this.startXPosition = startXPosition;
        this.startYPosition = startYPosition;
        this.setPattern();
        this.setPatternTerrain();
    }

    public abstract void setPattern();

    public abstract void setPatternTerrain();

    public ArrayList<Rectangle> getPattern() {
        return this.pattern;
    }

    public ArrayList<Rectangle> getPatternTerrain() {
        return this.patternTerrain;
    }

    public double getStartXPosition() {
        return this.startXPosition;
    }

    public double getStartYPosition() {
        return this.startYPosition;
    }

}
