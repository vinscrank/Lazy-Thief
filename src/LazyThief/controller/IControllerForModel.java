/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.controller;

/**
 *
 * @author User
 */
public interface IControllerForModel {

    public void notifyException(String message);

   

    public void notifyDead(int arrayIndex);

}
