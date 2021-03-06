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
        
        ArrayList<Trajet> trajets2 = new ArrayList();
        trajets2.add(new Trajet("Paris", "Lyon", new Date(2017, 6, 6), "Air Polytech", 40.0));
        trajets2.add(new Trajet("Paris", "Lyon", new Date(2017, 5, 10), "EasyJet", 60.0));
        
        Overview o = new Overview();
        FournisseurAgent fournisseur = new FournisseurAgent(trajets, o, "FlightSearcher");
        FournisseurAgent fournisseur2 = new FournisseurAgent(trajets2, o, "Traveler.com");
        
        o.addFournisseur(fournisseur);
        o.addFournisseur(fournisseur2);
            
        ArrayList<Contrainte > contraintes = new ArrayList();
        contraintes.add(new Contrainte(ContrainteType.PRIX, 76.0));
        contraintes.add(new Contrainte(ContrainteType.DATEMIN, new Date(2017, 5, 20)));
        
        ArrayList<Contrainte> contraintes2 = new ArrayList();
        contraintes2.add(new Contrainte(ContrainteType.PRIX, 30.0));
        contraintes2.add(new Contrainte(ContrainteType.DATEMIN, new Date(2017, 6, 20)));
        contraintes2.add(new Contrainte(ContrainteType.COMPAGNIECIBLE, "Air Polytech"));
        contraintes2.add(new Contrainte(ContrainteType.COMPAGNIEREFUS, "Air France"));
        contraintes2.add(new Contrainte(ContrainteType.COMPAGNIEREFUS,"EasyJet"));
        
        
        NegotiateurAgent neg = new NegotiateurAgent("Paris", "Lyon", contraintes, o, "Bob");
        NegotiateurAgent neg2 = new NegotiateurAgent("Paris", "Lyon", contraintes2, o, "Alice");
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(neg);
        sleep(100);
        executor.execute(neg2);
        sleep(100);
        executor.execute(fournisseur);
        sleep(100);
        executor.execute(fournisseur2);
        
        
    }
    
}
