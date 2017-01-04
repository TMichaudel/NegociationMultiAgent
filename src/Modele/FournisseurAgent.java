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
    private Overview overview;

    public FournisseurAgent(ArrayList<Trajet> trajets, Overview overview) {
        this.trajets = trajets;
        this.overview = overview;
    }

    public boolean verifierTrajets(String depart, String destination) {
        for (Trajet t : trajets) {
            if ((t.getDepart() == depart) && (t.getDestination() == destination)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<Message> messages = overview.getMessages(this);
            Message message_reponse = null;
            for (Message m : messages) {
                switch (m.getType()) {
                    case APPELDOFFRE:
                        ArrayList<Trajet> trajets_reponse = new ArrayList();
                        for (Trajet t : trajets) {
                            if ((m.getDepart() == t.getDepart()) && (m.getDestination() == t.getDestination())) {
                                boolean ok = true;
                                for (Contrainte c : m.getContraintes()) {
                                    switch (c.getType()) {
                                        case DATEMIN:
                                            if (c.getDate().after(t.getDate())) {
                                                ok = false;
                                            }
                                            break;
                                        case PRIX:
                                            if (c.getPrix() < t.getPrix()) {
                                                ok = false;
                                            }
                                            break;
                                        case COMPAGNIECIBLE:
                                            if (!c.getCompagnie().equals(t.getCompagnie())) {
                                                ok = false;
                                            }
                                            break;
                                        case COMPAGNIEREFUS:
                                            if (c.getCompagnie().equals(t.getCompagnie())) {
                                                ok = false;
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                if (ok) {
                                    trajets_reponse.add(t);
                                }
                            }
                        }
                        if (!trajets_reponse.isEmpty()) {
                            message_reponse = new Message(PerformatifType.PROPOSITION, this, m.getEmetteur(), trajets_reponse);
                        } else {
                            message_reponse = new Message(PerformatifType.REFUS, this, m.getEmetteur(), null);
                        }
                        m.traiterMessage();
                        overview.addMessage(message_reponse);
                        break;
                }
            }
        }
    }
}
