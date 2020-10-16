/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.model;

import java.util.LinkedList;

public class PlayerData {

    public LinkedList<Player> playerLst;

    public PlayerData() {
        this.playerLst = new LinkedList<Player>();
    }

    public void add(Player player) {
        this.playerLst.add(player);
    }

    public void remove(Player player) {
        this.playerLst.remove(player);
    }

    public LinkedList<Player> getListOfPlayers() {
        return this.playerLst;
    }

    public LinkedList<String[]> asListOfStringArray() {

        LinkedList<String[]> lstSA = null;

        lstSA = new LinkedList<String[]>();
        String[] sArr = null;
        for (Player p : this.playerLst) {
            //inizializza arrayVite

            sArr = new String[18];

            sArr[0] = String.valueOf(p.getPlayerId());
            sArr[1] = p.getPlayerName();
            sArr[2] = String.valueOf(p.getplayerActualScore());
            sArr[3] = String.valueOf(p.getPlayerNumberOfLives());
            sArr[4] = String.valueOf(p.getDifficultyLevel());
            sArr[5] = String.valueOf(p.getCurrentMission());
            sArr[6] = String.valueOf(p.getActualShape());
            sArr[7] = String.valueOf(p.getRecord());
            sArr[8] = String.valueOf(p.arrayVite().get(0));
            sArr[9] = String.valueOf(p.arrayVite().get(1));
            sArr[10] = String.valueOf(p.arrayVite().get(2));
            sArr[11] = String.valueOf(p.arrayVite().get(3));
            sArr[12] = String.valueOf(p.arrayVite().get(4));
            sArr[13] = String.valueOf(p.arrayVite().get(5));
            sArr[14] = String.valueOf(p.arrayVite().get(6));
            sArr[15] = String.valueOf(p.arrayVite().get(7));
            sArr[16] = String.valueOf(p.arrayVite().get(8));
            sArr[17] = String.valueOf(p.arrayVite().get(9));

            //System.out.println("record"+p.getRecord());
            lstSA.add(sArr);
        }

        return lstSA;
    }

    /* public ArrayList<Integer> asListOfStringArrayVite() {
         ArrayList <Integer> vite = new ArrayList<Integer>();

      
        
       
        for (Player p : this.playerLst) {
            vite.add(p.arl.get(0));
            vite.add(p.arl.get(1));
            vite.add(p.arl.get(2));
          
            //inizializza arrayVite
           
           
        }

        return vite;
    }*/
    public void classifica() {
        String[] sArr = null;
        for (Player p : this.playerLst) {
            sArr = new String[2];
            sArr[0] = p.getPlayerName();
            sArr[1] = String.valueOf(p.getRecord());
            System.out.println("NOme" + sArr[0] + "record" + sArr[1]);

        }
    }

   

} // end class
