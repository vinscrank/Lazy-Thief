/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public class StickmanModel extends Rectangle {
    
   
    private boolean allied;
    
    
    public StickmanModel(double xPosition,double yPosition,boolean allied)
    {
        super(xPosition,yPosition,50,50);
        
        this.allied = allied;
    }
    
   
    
    public boolean isAllied()
    {
        return this.allied;
    }
    
    
    
   
    
}
