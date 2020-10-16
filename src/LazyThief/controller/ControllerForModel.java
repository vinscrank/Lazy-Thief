/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package LazyThief.controller;

import LazyThief.view.View;

public class ControllerForModel implements IControllerForModel {

    private static ControllerForModel instance = null;

    public static IControllerForModel getInstance() {
        if (instance == null) {
            instance = new ControllerForModel();
        }
        return instance;
    }

    @Override
    public void notifyException(String message) {
        View.getInstance().showErrorDialog(message);
    }

    

    @Override
    public void notifyDead(int arrayIndex) {

        View.getInstance().killRect(arrayIndex);
    }

}
