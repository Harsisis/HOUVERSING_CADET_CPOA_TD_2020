package main.pojo;

import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String identifiant;
    private String mdp;
    private String adrNumero;
    private String adrVoie;
    private String adrCP;
    private String adrVille;
    private String adrPays;

    private List<Commande> commandeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdrNumero() {
        return adrNumero;
    }

    public void setAdrNumero(String adrNumero) {
        this.adrNumero = adrNumero;
    }

    public String getAdrVoie() {
        return adrVoie;
    }

    public void setAdrVoie(String adrVoie) {
        this.adrVoie = adrVoie;
    }

    public String getAdrCP() {
        return adrCP;
    }

    public void setAdrCP(String adrCP) {
        this.adrCP = adrCP;
    }

    public String getAdrVille() {
        return adrVille;
    }

    public void setAdrVille(String adrVille) {
        this.adrVille = adrVille;
    }

    public String getAdrPays() {
        return adrPays;
    }

    public void setAdrPays(String adrPays) {
        this.adrPays = adrPays;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }
}
