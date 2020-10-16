/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.view;

public class Person {

    private String firstName = null;
    private int record;
    private int posizione;
    private int vitefinali;

    public Person() {
    }

    public Person(String firstName, int record) {

        this.firstName = firstName;
        this.record = record;
        //this.vitefinali=vitefinali;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getRecord() {
        return this.record;
    }

    public void setRecord(int r) {
        this.record = record;
    }

    public int getPosizione() {
        return this.posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public int getViteFianeli() {
        return this.vitefinali;
    }

    public void setViteFinalu(int vitefinali) {
        this.vitefinali = vitefinali;
    }
}
