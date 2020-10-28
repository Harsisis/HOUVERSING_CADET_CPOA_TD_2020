package main.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controller_accueil implements Initializable {

    //table des categories
    @FXML
    private TableView<Categorie> tableCategorie;

    @FXML
    private TableColumn<Categorie, Integer> colCatId;

    @FXML
    private TableColumn<Categorie, String> colCatTitre;

    @FXML
    private TableColumn<Categorie, String> colCatVisuel;

    //table des produits
    @FXML
    private TableView<Produit> tableProduit;

    @FXML
    private TableColumn<Produit, Integer> colProdId;

    @FXML
    private TableColumn<Produit, String> colProdNom;

    @FXML
    private TableColumn<Produit, String> colProdDescription;

    @FXML
    private TableColumn<Produit, Float> colProdTarif;

    @FXML
    private TableColumn<Produit, String> colProdVisuel;

    @FXML
    private TableColumn<Produit, Categorie> colProdIdCat;

    //table des clients
    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Client, Integer> colClientId;

    @FXML
    private TableColumn<Client, String> colClientNom;

    @FXML
    private TableColumn<Client, String> colClientPrenom;

    @FXML
    private TableColumn<Client, String> colClientNumero;

    @FXML
    private TableColumn<Client, String> colClientVoie;

    @FXML
    private TableColumn<Client, String> colClientCP;

    @FXML
    private TableColumn<Client, String> colClientVille;

    @FXML
    private TableColumn<Client, String> colClientPays;

    //table des commandes
    @FXML
    private TableView<Commande> tableCommande;

    @FXML
    private TableColumn<Commande, Integer> colCommandeId;

    @FXML
    private TableColumn<Commande, LocalDate> colCommandeDate;

    @FXML
    private TableColumn<Commande, Client> colCommandeClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //definition de la table des categories
        tableCategorie.setVisible(false);
        colCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCatTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colCatVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        this.tableCategorie.getColumns().setAll(colCatId, colCatTitre, colCatVisuel);
        this.tableCategorie.getItems().addAll(DAOFactory.getDAOFactory(EPersistence.MYSQL).getCategorieDAO().findAll());

        //definition de la table des produits
        tableProduit.setVisible(false);
        colProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProdNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colProdDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProdTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        colProdVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        colProdIdCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        this.tableProduit.getColumns().setAll(colProdId, colProdNom, colProdDescription, colProdTarif, colProdVisuel, colProdIdCat);
        this.tableProduit.getItems().addAll(DAOFactory.getDAOFactory(EPersistence.MYSQL).getProduitDAO().findAll());

        //defintion de la table des clients
        tableClient.setVisible(false);
        colClientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClientNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colClientPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colClientNumero.setCellValueFactory(new PropertyValueFactory<>("adrNumero"));
        colClientVoie.setCellValueFactory(new PropertyValueFactory<>("adrVoie"));
        colClientCP.setCellValueFactory(new PropertyValueFactory<>("adrCP"));
        colClientVille.setCellValueFactory(new PropertyValueFactory<>("adrVille"));
        colClientPays.setCellValueFactory(new PropertyValueFactory<>("adrPays"));
        this.tableClient.getColumns().setAll(colClientId, colClientNom, colClientPrenom, colClientNumero, colClientVoie, colClientCP, colClientVille, colClientPays);
        this.tableClient.getItems().addAll(DAOFactory.getDAOFactory(EPersistence.MYSQL).getClientDAO().findAll());

        //definition de la table des commandes
        tableCommande.setVisible(false);
        colCommandeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCommandeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCommandeClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        this.tableCommande.getColumns().setAll(colCommandeId, colCommandeDate, colCommandeClient);
        this.tableCommande.getItems().addAll(DAOFactory.getDAOFactory(EPersistence.MYSQL).getCommandeDAO().findAll());
    }

    @FXML
    void showTableCategorie(MouseEvent event) {
        this.tableCategorie.refresh();
        tableProduit.setVisible(false);
        tableClient.setVisible(false);
        tableCommande.setVisible(false);
        tableCategorie.setVisible(true);
    }

    @FXML
    void showTableProduit(MouseEvent event) {
        this.tableProduit.refresh();
        tableCategorie.setVisible(false);
        tableCommande.setVisible(false);
        tableClient.setVisible(false);
        tableProduit.setVisible(true);
    }

    @FXML
    void showTableClient(MouseEvent event) {
        this.tableClient.refresh();
        tableCommande.setVisible(false);
        tableProduit.setVisible(false);
        tableCategorie.setVisible(false);
        tableClient.setVisible(true);
    }

    @FXML
    void showTableCommande(MouseEvent event) {
        this.tableCommande.refresh();
        tableClient.setVisible(false);
        tableCategorie.setVisible(false);
        tableProduit.setVisible(false);
        tableCommande.setVisible(true);
    }
}