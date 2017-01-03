/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Date;

/**
 *
 * @author thiba
 */
public class Trajet {
    private String depart;
    private String destination;
    private Date date;
    private String compagnie;
    private Double prix;

    public Trajet(String depart, String destination, Date date, String compagnie, Double prix) {
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.compagnie = compagnie;
        this.prix = prix;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public Double getPrix() {
        return prix;
    }
    
    
}
