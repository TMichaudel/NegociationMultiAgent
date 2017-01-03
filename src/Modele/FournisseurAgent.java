/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 *
 * @author thiba
 */
public class FournisseurAgent extends Agent {
    private ArrayList<Trajet> trajets;

    public FournisseurAgent(ArrayList<Trajet> trajets) {
        this.trajets = trajets;
    }
    
    public boolean verifierTrajets(String depart, String destination){
        for(Trajet t : trajets){
            if((t.getDepart()==depart)&&(t.getDestination()==destination)){
                return true;
            }
        }
           return false; 
    }
}
