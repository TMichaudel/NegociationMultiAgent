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
public class NegotiateurAgent extends Agent {

    private String depart;
    private String destination;
    private ArrayList<Contrainte> contraintes;
    private boolean isSatisfied;
    private ArrayList<FournisseurAgent> listeFournisseurs;

    public NegotiateurAgent(String depart, String destination, ArrayList<Contrainte> contraintes, Overview overview) {
        this.isSatisfied = false;
        this.depart = depart;
        this.destination = destination;
        this.contraintes = contraintes;
        this.overview = overview;
        this.listeFournisseurs = new ArrayList();
    }

    @Override
    public void run() {

        for (FournisseurAgent fournisseur : overview.getListeFournisseurs()) {
            if (fournisseur.verifierTrajets(depart, destination)) {
                listeFournisseurs.add(fournisseur);
            }
        }
        if (!listeFournisseurs.isEmpty()) {
            while (!isSatisfied) {
                ArrayList<Message> messages = overview.getMessages(this);
                for (Message m : messages) {
                    switch (m.getType()) {
                        case REFUS:
                            Contrainte c_mod;
                            int index = Math.toIntExact(Math.round(Math.random() * (contraintes.size())));
                            c_mod = contraintes.get(index);
                            switch (c_mod.getType()) {
                                case PRIX:
                                    c_mod.augmenterPrix();
                                    System.out.println("prix augmenté");
                                    contraintes.add(c_mod);
                                    break;
                                case DATEMIN:
                                    c_mod.diminuerDate();
                                    System.out.println("date diminuée");
                                    contraintes.add(c_mod);
                                    break;
                                default:
                                    System.out.println("Compagnie suprimée");
                                    break;
                            }
                            contraintes.remove(index);
                            break;
                        case PROPOSITION:
                            isSatisfied = true;
                            for (Trajet t : m.getTrajets()) {
                                System.out.println(t.getDepart() + " ; " + t.getDestination() + " ; " + t.getDate() + " ; " + t.getCompagnie());
                            }
                            break;
                        default:
                            break;
                    }
                    m.traiterMessage();
                }
                if (!isSatisfied) {
                    for (FournisseurAgent f : listeFournisseurs) {
                        //On regarde si on est pas déjà en cours de communication avec ce fournisseur
                        if (!overview.messagePending(this, f));
                        {
                            Message m = new Message(PerformatifType.APPELDOFFRE, this, f, this.depart, this.destination, this.contraintes);
                            overview.addMessage(m);
                            //System.out.println("Message envoyé");
                        }
                    }

                }
                try {
                    sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NegotiateurAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("Aucun trajet correspondant pour le départ et la destination souhaitées : " + depart + " -> " + destination);
        }
    }

}
