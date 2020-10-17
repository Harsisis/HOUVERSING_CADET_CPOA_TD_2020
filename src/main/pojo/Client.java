package main.pojo;

import java.util.List;
import java.util.Objects;

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

//    private List<Commande> commandeList;


    public Client(int id, String nom, String prenom, String identifiant, String mdp, String adrNumero, String adrVoie, String adrCP, String adrVille, String adrPays) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.adrNumero = adrNumero;
        this.adrVoie = adrVoie;
        this.adrCP = adrCP;
        this.adrVille = adrVille;
        this.adrPays = adrPays;
    }

    public Client(){
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " | " + nom + " | " + prenom + "\n " + adrNumero + " " + adrVoie + ", " + adrVille + " " + adrCP + ", " + adrPays;
    }
}
