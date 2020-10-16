/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;



import java.util.ArrayList;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import LazyThief.utils.Resources;

/**
 *
 * @author franc
 */
public abstract class StartPosition {
    
    protected ArrayList<Rectangle> pattern;
    protected ArrayList<Rectangle> patternTerrain;
    protected double startXPosition;
    protected double startYPosition;
    protected static final double BLOCK_WIDTH = 50;
    protected static final double BLOCK_HEIGHT = 50;
    
    public StartPosition(double startXPosition,double startYPosition)
    {
        this.startXPosition = startXPosition;
        this.startYPosition = startYPosition;
        this.pattern = new ArrayList<>();       
         this.patternTerrain = new ArrayList<>();  
        this.setPattern();
        this.setPatternTerrain();
    }
    
    
    public abstract void setPattern();
    public abstract void setPatternTerrain();
    
    public void setFillPattern()
    {
        pattern.forEach((element) -> {
                element.setFill(new ImagePattern(Resources.GeneralImages.BLACKBASE.getImage()));
        });
    }
      public void setFillPatternTerrain()
    {
        patternTerrain.forEach((element) -> {
                element.setFill(new ImagePattern(Resources.GeneralImages.TERRAIN.getImage()));
        });
    }
    
    public void setPatternToBattlefield(BattleField battlefield)
    {
        this.getPattern().forEach((rect) -> {
            battlefield.getChildren().add(rect);
        });
    }
        public void setPatternTerrainToBattlefield(BattleField battlefield)
    {
        this.getPatternTerrain().forEach((rect) -> {
            battlefield.getChildren().add(rect);
        });
    }
    
    public ArrayList<Rectangle> getPattern()
    {
        return this.pattern;
    }
       public ArrayList<Rectangle> getPatternTerrain()
    {
        return this.patternTerrain;
    }
    
    public double getStartX()
    {
        return this.startXPosition;
    }
    
    public double getStartY()
    {
        return this.startYPosition;
    }
   
    
    
}
