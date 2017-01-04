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
public class Message {
    PerformatifType type;
    Agent emetteur;
    Agent recepteur;
    String depart;
    String destination;
    ArrayList<Contrainte> contraintes;
    ArrayList<Trajet> trajets;

    public Message(PerformatifType type, Agent emetteur, Agent recepteur, String depart, String Destination, ArrayList contraintes) {
        this.type = type;
        this.emetteur = emetteur;
        this.recepteur = recepteur;
        this.depart = depart;
        this.destination = destination;
        this.contraintes = contraintes;
        this.trajets = null;
    }
    
    public Message(PerformatifType type, Agent emetteur, Agent recepteur, ArrayList<Trajet> trajets){
        this.type = type;
        this.emetteur = emetteur;
        this.recepteur = recepteur;
        this.depart = null;
        this.destination = null;
        this.contraintes = null;
        this.trajets = trajets;
    }

    
    public PerformatifType getType() {
        return type;
    }

    public Agent getEmetteur() {
        return emetteur;
    }

    public Agent getRecepteur() {
        return recepteur;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Contrainte> getContraintes() {
        return contraintes;
    }

    public ArrayList<Trajet> getTrajets() {
        return trajets;
    }
    
    public void traiterMessage() {
        this.type=PerformatifType.TRAITE;
    }
    
    
}
