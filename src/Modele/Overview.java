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
public class Overview {
    private ArrayList<Message> listeMessages;
    private ArrayList<FournisseurAgent> listeFournisseurs;

    public Overview() {
        this.listeMessages = new ArrayList();
        this.listeFournisseurs = new ArrayList();
    }
    
    public void addFournisseur( FournisseurAgent fournisseur){
        this.listeFournisseurs.add(fournisseur);
    }

    public ArrayList<Message> getListeMessages() {
        return listeMessages;
    }

    public ArrayList<FournisseurAgent> getListeFournisseurs() {
        return listeFournisseurs;
    }
    
        public void addMessage(Message m) {
        listeMessages.add(m);
    }

    public boolean messagePending(Agent emetteur, Agent recepteur) {
        boolean pending = false;
        for (Message m : listeMessages){
            if((m.getEmetteur()==emetteur)&&(m.getRecepteur()==recepteur) &&(m.getType()!=PerformatifType.TRAITE)){
                pending = true;
            }
        }
        return pending;
    }
    public ArrayList<Message> getReponses(Agent a) {
        ArrayList<Message> result = new ArrayList();
        for (Message m : this.listeMessages) {
            if ((m.getEmetteur().equals(a)) && !(m.getType().equals(PerformatifType.TRAITE))) {
                result.add(m);
            }
        }
        return result;
    }

    public ArrayList<Message> getMessages(Agent a) {
        ArrayList<Message> result = new ArrayList();
        for (Message m : this.listeMessages) {
            if ((m.getRecepteur().equals(a)) && !(m.getType().equals(PerformatifType.TRAITE))) {
            	result.add(m);
            }
        }
        return result;
    }
    
    public void traiterMessage (Agent emetteur, Agent recepteur) {
        for (Message m : listeMessages){
            if((m.getEmetteur()==emetteur)&&(m.getRecepteur()==recepteur) &&(m.getType()!=PerformatifType.TRAITE)){
                m.traiterMessage();
            }
        }
    }
}
