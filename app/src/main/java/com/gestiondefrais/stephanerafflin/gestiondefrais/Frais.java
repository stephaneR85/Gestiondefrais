package com.gestiondefrais.stephanerafflin.gestiondefrais;

import java.sql.Blob;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */

public class Frais {

    private int _id;
    private String type;
    private double montant;
    private long date;
    private byte[] justif;

    public Frais(String type, double montant, long date, byte[] justif) {
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.justif = justif;
    }
    public Frais(){

    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public byte[] getJustif() {
        return justif;
    }

    public void setJustif(byte[] justif) {
        this.justif = justif;
    }

    @Override
    public String toString() {
        return "[Frais] id : " + _id + " - type : " + type + ", Montant : "
                + montant + " - Date : " + date;
    }

}


