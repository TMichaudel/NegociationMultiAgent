/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;
import Modele.*;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author thiba
 */
public class NegociationMultiAgent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Trajet> trajets = new ArrayList();
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 5, 20), "Air France", 100.0));
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 6, 5), "Air France", 80.0));
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 6, 6), "Air France", 75.0));
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 6, 1), "EasyJet", 60.0));
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 5, 20), "EasyJet", 80.0));
        trajets.add(new Trajet("Paris", "Lyon", new Date(2017, 5, 19), "RyanAir", 50.0));
        
        Overview o = new Overview();
        FournisseurAgent fournisseur = new FournisseurAgent(trajets, o);
        o.addFournisseur(fournisseur);
        
        
        
        ArrayList<Contrainte > contraintes = new ArrayList();
        contraintes.add(new Contrainte(ContrainteType.PRIX, 76.0));
        contraintes.add(new Contrainte(ContrainteType.DATEMIN, new Date(2017, 5, 20)));
        NegotiateurAgent neg = new NegotiateurAgent("Paris", "Lyon", contraintes, o);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(neg);
        sleep(100);
        executor.execute(fournisseur);
        
        
    }
    
}
