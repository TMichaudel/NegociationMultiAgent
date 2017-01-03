/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;
import Modele.*;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author thiba
 */
public class NegociationMultiAgent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Trajet> trajets = new ArrayList();
        trajets.add(new Trajet("Paris", "Lyon", new Date(), "Air France", 100.0));
    }
    
}
