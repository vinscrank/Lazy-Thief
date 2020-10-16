/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Config {
    //STATIC FIELDS

    private static Config config = null;

    //INSTANCE FIELDS
    private Properties properties;

    private Config() {
        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getResourceAsStream("/resources/config/level1.txt"), "ISO-8859-1"));

            this.properties = new Properties();
            this.properties.load(buffRead);
        } catch (FileNotFoundException fnfe) {

        } catch (IOException ioe) {

        } finally {
            try {
                if (buffRead != null) {
                    buffRead.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } // end finally
    } // end constructor

    public void changeConfigurationFile(String path) {

        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getResourceAsStream(path), "ISO-8859-1"));

            this.properties = new Properties();
            this.properties.load(buffRead);
        } catch (FileNotFoundException fnfe) {

            JOptionPane.showMessageDialog(null,
                    "Configuration file not found, the program will be closed. " + this.getConfigFileFullPath(path),
                    "Serious ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);

        } catch (IOException ioe) {

            JOptionPane.showMessageDialog(null,
                    "Unable to read the configuration file, the program will be closed.",
                    "Serious ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);

        } finally {
            try {
                if (buffRead != null) {
                    buffRead.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } // end finally

    }

    private String getConfigFileFullPath(String path) {
        String fileName = Config.class.getResource(path).toString();

        if (fileName.contains("//")) {
            fileName = fileName.substring("file:/".length()); //Windows version
        } else if (fileName.contains("/")) {
            fileName = fileName.substring("file:/".length()); //Linux version
        }
        fileName = fileName.replaceAll("%20", " ");
        return fileName;
    }

    public double getBattlefieldWidth() {
        return Double.parseDouble(this.properties.getProperty("battlefieldWidth"));
    }

    public double getBattlefieldHeight() {
        return Double.parseDouble(this.properties.getProperty("battlefieldHeight"));
    }

    public double getBulletRadiusY() {
        return Double.parseDouble(this.properties.getProperty("bulletRadiusY"));
    }

    public double getPlayerBulletStartXPosition() {
        return Double.parseDouble(this.properties.getProperty("alliedBulletXPosition"));
    }

    public double getPlayerBulletStartYPosition() {
        return Double.parseDouble(this.properties.getProperty("alliedBulletYPosition"));
    }

    public int getNumberThief() {
        return Integer.parseInt(this.properties.getProperty("numberThief"));
    }

    public double getAlliedPenguinsStartXPosition() {
        return Double.parseDouble(this.properties.getProperty("alliedThiefStartXPosition"));
    }

    public double getPenguinsStartYPosition() {
        return Double.parseDouble(this.properties.getProperty("ThiefStartYPosition"));
    }

    public double getEnemyPenguinsStartXPosition() {
        return Double.parseDouble(this.properties.getProperty("enemyThiefStartXPosition"));
    }

    public double getPlayerBaseStartX() {
        return Double.parseDouble(this.properties.getProperty("playerBaseStartX"));
    }

    public double getPlayerBaseStartY() {
        return Double.parseDouble(this.properties.getProperty("playerBaseStartY"));
    }

    public double getTerrainStartX() {
        return Double.parseDouble(this.properties.getProperty("terrainStartX"));
    }

    public double getTerrainStartY() {
        return Double.parseDouble(this.properties.getProperty("terrainStartY"));
    }

    public double getEnemyBulletStartX() {
        return Double.parseDouble(this.properties.getProperty("enemyBulletXPosition"));
    }

    public double getEnemyBulletStartY() {
        return Double.parseDouble(this.properties.getProperty("enemyBulletYPosition"));
    }

    public double getBulletRadius() {
        return Double.parseDouble(this.properties.getProperty("bulletRadius"));
    }

    //STATIC METHODS
    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

}
