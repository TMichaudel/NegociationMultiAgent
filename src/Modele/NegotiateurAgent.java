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
        this.listeFournisseurs=new ArrayList();
    }
    
    @Override
    public void run(){
        for(FournisseurAgent fournisseur : overview.getListeFournisseurs()){
            if(fournisseur.verifierTrajets(depart, destination)){
                listeFournisseurs.add(fournisseur);
            }
        }
        if(!listeFournisseurs.isEmpty()){
            while(!isSatisfied){
            }
        }
        else
        {
            System.out.println("Aucun trajet correspondant pour le départ et la destination souhaitées : "+depart+" -> "+destination);
        }
    }
    
}
