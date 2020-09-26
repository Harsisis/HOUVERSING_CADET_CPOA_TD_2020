package main.pojo;

import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String prenom;
//    private String identifiant;
//    private String mdp;
//    private String adrNumero;
//    private String adrVoie;
//    private String adrCP;
//    private String adrVille;
//    private String adrPays;

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

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }
}
