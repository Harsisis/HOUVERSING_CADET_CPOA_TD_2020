package main.pojo;

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
}
