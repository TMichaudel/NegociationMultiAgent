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
public class Contrainte {
    private ContrainteType type;
    private Date date;
    private Float prix;
    private String compagnie;
 
    public Contrainte(ContrainteType _type, Date _date) {
        this.type = _type;
        this.date = _date;
    }
    
    public Contrainte(ContrainteType _type, Float _prix){
        this.type = _type;
        this.prix = _prix;
    }
    
    public Contrainte(ContrainteType _type, String _compagnie){
        this.type = _type;
        this.compagnie = _compagnie;
    }

    public ContrainteType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrix() {
        return prix;
    }

    public String getCompagnie() {
        return compagnie;
    }
    
}