package main.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.pojo.Categorie;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controller_accueil implements Initializable {

    //boutons CRUD
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSuppr;

    @FXML
    private Button btnEdit;

    int visible = 0;

    //radio bouttons Epersistance
    @FXML
    private RadioButton rbDatab;

    @FXML
    private RadioButton rbListeM;

    private ToggleGroup persistanceToggleGroup;

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

        //definition radio group
        persistanceToggleGroup = new ToggleGroup();
        this.rbDatab.setToggleGroup(persistanceToggleGroup);
        this.rbListeM.setToggleGroup(persistanceToggleGroup);

        //bouttons CRUD
        btnAdd.setDisable(true);
        btnEdit.setDisable(true);
        btnSuppr.setDisable(true);

    }

    @FXML
    void showTableCategorie(MouseEvent event) {
        this.tableCategorie.refresh();
        tableProduit.setVisible(false);
        tableClient.setVisible(false);
        tableCommande.setVisible(false);
        tableCategorie.setVisible(true);
        visible = 1;
        btnAdd.setDisable(false);
    }

    @FXML
    void showTableProduit(MouseEvent event) {
        this.tableProduit.refresh();
        tableCategorie.setVisible(false);
        tableCommande.setVisible(false);
        tableClient.setVisible(false);
        tableProduit.setVisible(true);
        visible = 2;
        btnAdd.setDisable(false);
    }

    @FXML
    void showTableClient(MouseEvent event) {
        this.tableClient.refresh();
        tableCommande.setVisible(false);
        tableProduit.setVisible(false);
        tableCategorie.setVisible(false);
        tableClient.setVisible(true);
        visible = 3;
        btnAdd.setDisable(false);
    }

    @FXML
    void showTableCommande(MouseEvent event) {
        this.tableCommande.refresh();
        tableClient.setVisible(false);
        tableCategorie.setVisible(false);
        tableProduit.setVisible(false);
        tableCommande.setVisible(true);
        visible = 4;
        btnAdd.setDisable(false);
    }

    public void radioButtonChanged(){
        EPersistence choix;
        if (this.persistanceToggleGroup.getSelectedToggle().equals(this.rbDatab)){
            choix = EPersistence.MYSQL;
        }
        else{
            choix = EPersistence.LISTEMEMORY;
        }
        DAOFactory daoFactory = DAOFactory.getDAOFactory(choix);
    }

    @FXML
    void btnAdd_onClick(MouseEvent event) {
        Scene scene = null;
        Stage stage = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            switch (visible){
                case 1:
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../sample/addCategorie.fxml")));
                    stage = new Stage();
                    stage.setTitle("Ajouter une Catégorie");
                    break;
                case 2:
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../sample/addProduct.fxml")));
                    stage = new Stage();
                    stage.setTitle("Ajouter un Produit");
                    break;
                case 3:
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../sample/addClient.fxml")));
                    stage = new Stage();
                    stage.setTitle("Ajouter un Client");
                    break;
                case 4:
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../sample/addCommande.fxml")));
                    stage = new Stage();
                    stage.setTitle("Ajouter une Commande");
                    break;
            }

            Image icon = new Image(getClass().getResourceAsStream("../images/iconTest.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void btnEdit_onClick(MouseEvent event) {

    }

    @FXML
    void btnSuppr_onClick(MouseEvent event) {

    }
}