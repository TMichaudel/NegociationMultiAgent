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
        ArrayList<Message> messages = overview.getMessages(this);

        for (FournisseurAgent fournisseur : overview.getListeFournisseurs()) {
            if (fournisseur.verifierTrajets(depart, destination)) {
                listeFournisseurs.add(fournisseur);
            }
        }
        if (!listeFournisseurs.isEmpty()) {
            while (!isSatisfied) {
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
                    Message m = new Message(PerformatifType.APPELDOFFRE, this, listeFournisseurs.get(0), this.depart, this.destination, this.contraintes);
                    overview.addMessage(m);
                }
            }
        } else {
            System.out.println("Aucun trajet correspondant pour le départ et la destination souhaitées : " + depart + " -> " + destination);
        }
    }

}
