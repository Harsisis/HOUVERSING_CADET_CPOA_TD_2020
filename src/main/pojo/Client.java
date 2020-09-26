package main.pojo;

import java.util.List;
import java.util.Objects;

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


    public Client(int id, String nom, String prenom, List<Commande> commandeList) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.commandeList = commandeList;
    }

    private List<Commande> commandeList;

    public int getId() { return id; }

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
}
