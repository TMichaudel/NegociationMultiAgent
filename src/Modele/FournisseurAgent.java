/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try {
                sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(FournisseurAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            Message message_reponse = null;
            for (Message m : overview.getMessages(this)) {
                //System.out.println("message reçu");
                switch (m.getType()) {
                    case APPELDOFFRE:
                        ArrayList<Trajet> trajets_reponse = new ArrayList();
                        for (Trajet t : trajets) {
                            //System.out.println(m.getDepart()+ " ; " + t.getDepart()+ " ; " + m.getDestination() + " ; " + t.getDestination());
                            if ((m.getDepart().equals(t.getDepart())) && (m.getDestination().equals(t.getDestination()))) {
                                boolean ok = true;
                                for (Contrainte c : m.getContraintes()) {
                                    switch (c.getType()) {
                                        case DATEMIN:
                                            if (c.getDate().after(t.getDate())) {
                                                //System.out.println("date pas ok");
                                                ok = false;
                                            }
                                            break;
                                        case PRIX:
                                            if (c.getPrix() < t.getPrix()) {
                                                //System.out.println("prix pas ok");
                                                ok = false;
                                            }
                                            break;
                                        case COMPAGNIECIBLE:
                                            if (!c.getCompagnie().equals(t.getCompagnie())) {
                                                //System.out.println("cible pas ok");
                                                ok = false;
                                            }
                                            break;
                                        case COMPAGNIEREFUS:
                                            if (c.getCompagnie().equals(t.getCompagnie())) {
                                                //System.out.println("refus pas ok");
                                                ok = false;
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                if (ok) {
                                    //System.out.println("trajets trouvés !");
                                    trajets_reponse.add(t);
                                }
                            }
                        }
                        if (!trajets_reponse.isEmpty()) {
                            message_reponse = new Message(PerformatifType.PROPOSITION, this, m.getEmetteur(), trajets_reponse);
                        } else {
                            message_reponse = new Message(PerformatifType.REFUS, this, m.getEmetteur(), null);
                        }
                        overview.traiterMessage(m.getEmetteur(), this);
                        overview.addMessage(message_reponse);
                        break;
                }
            }
        }
    }
}
