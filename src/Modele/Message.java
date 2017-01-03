/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author thiba
 */
public class Message {
    PerformatifType type;
    PerformatifType reponse;
    Agent emetteur;
    Agent recepteur;

    public PerformatifType getType() {
        return type;
    }

    public Agent getEmetteur() {
        return emetteur;
    }

    public Agent getRecepteur() {
        return recepteur;
    }

    public PerformatifType getReponse() {
        return reponse;
    }
    
    
}
