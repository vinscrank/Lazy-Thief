/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

import LazyThief.utils.Constant;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public class Scenario1 extends StartPosition {

    public Scenario1(double startXPosition, double startYPosition) {
        super(startXPosition, startYPosition);
    }

    @Override
    public void setPattern() {
        pattern.add(new Rectangle(startXPosition,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition + 50,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition + 100,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition + 150,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition + 200,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

    }

    @Override
    public void setPatternTerrain() {
        patternTerrain.add(new Rectangle(startXPosition,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 50,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 100,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 150,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 200,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 250,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 300,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 350,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 400,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 450,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 500,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 550,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 600,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 650,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 700,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 750,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 800,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 850,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 900,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 950,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1000,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1050,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1100,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1150,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1200,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1250,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1300,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1350,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1400,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1450,
                startYPosition,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 50,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 100,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 150,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 200,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 250,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 300,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 350,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 400,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 450,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 500,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 550,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 600,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 650,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 700,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 750,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 800,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 850,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 900,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 950,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1000,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1050,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1100,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1150,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1200,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1250,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1300,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1350,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

        patternTerrain.add(new Rectangle(startXPosition + 1400,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));
        patternTerrain.add(new Rectangle(startXPosition + 1450,
                startYPosition - 50,
                Constant.BLOCK_DIMENSION,
                Constant.BLOCK_DIMENSION));

    }

}
