package com.example.adsy.grtgaz;




public class Region {

    private String codeRegion, nom;

    public Region(String codeRegion, String nom) {

        this.setCodeRegion(codeRegion);
        this.setNom(nom);

    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
