package main.ui.controller;

import javafx.application.Platform;
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
import java.util.*;
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

    @FXML
    private Button btnPersistance;

    private ToggleGroup persistanceToggleGroup;

    public static EPersistence choix;

    //boutons affichage
    @FXML
    private Button btnCommande;

    @FXML
    private Button btnCategorie;

    @FXML
    private Button btnProduit;

    @FXML
    private Button btnClient;

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

    private static DAOFactory daoFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //disable, wait for the persistance
        btnCommande.setDisable(true);
        btnCategorie.setDisable(true);
        btnProduit.setDisable(true);
        btnClient.setDisable(true);

        createTable();
        //definition radio group
        persistanceToggleGroup = new ToggleGroup();
        this.rbDatab.setToggleGroup(persistanceToggleGroup);
        this.rbListeM.setToggleGroup(persistanceToggleGroup);

        //bouttons CRUD
        btnAdd.setDisable(true);
        btnEdit.setDisable(true);
        btnSuppr.setDisable(true);
    }

    private void createTable(){
        //definition de la table des categories
        tableCategorie.setVisible(false);
        colCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCatTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colCatVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        this.tableCategorie.getColumns().setAll(colCatId, colCatTitre, colCatVisuel);

        //definition de la table des produits
        tableProduit.setVisible(false);
        colProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProdNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colProdDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProdTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        colProdVisuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        colProdIdCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        this.tableProduit.getColumns().setAll(colProdId, colProdNom, colProdDescription, colProdTarif, colProdVisuel, colProdIdCat);

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

        //definition de la table des commandes
        tableCommande.setVisible(false);
        colCommandeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCommandeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCommandeClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        this.tableCommande.getColumns().setAll(colCommandeId, colCommandeDate, colCommandeClient);
    }

    @FXML
    void showTableCategorie(MouseEvent event) {
        tableProduit.setVisible(false);
        tableClient.setVisible(false);
        tableCommande.setVisible(false);
        tableCategorie.setVisible(true);
        visible = 1;
        btnAdd.setDisable(false);
        btnSuppr.setDisable(false);
    }

    @FXML
    void showTableProduit(MouseEvent event) {
        tableCategorie.setVisible(false);
        tableCommande.setVisible(false);
        tableClient.setVisible(false);
        tableProduit.setVisible(true);
        visible = 2;
        btnAdd.setDisable(false);
        btnSuppr.setDisable(false);
    }

    @FXML
    void showTableClient(MouseEvent event) {
        tableCommande.setVisible(false);
        tableProduit.setVisible(false);
        tableCategorie.setVisible(false);
        tableClient.setVisible(true);
        visible = 3;
        btnAdd.setDisable(false);
        btnSuppr.setDisable(false);
    }

    @FXML
    void showTableCommande(MouseEvent event) {
        tableClient.setVisible(false);
        tableCategorie.setVisible(false);
        tableProduit.setVisible(false);
        tableCommande.setVisible(true);
        visible = 4;
        btnAdd.setDisable(false);
        btnSuppr.setDisable(false);
    }

    @FXML
    void persistance_onClick(MouseEvent event){
        btnCommande.setDisable(false);
        btnCategorie.setDisable(false);
        btnProduit.setDisable(false);
        btnClient.setDisable(false);
        if (persistanceToggleGroup.getSelectedToggle().equals(rbDatab)){
            choix = EPersistence.MYSQL;
        }
        else {
            choix = EPersistence.LISTEMEMORY;
        }

        daoFactory = DAOFactory.getDAOFactory(choix);
        this.tableCategorie.getItems().addAll(daoFactory.getCategorieDAO().findAll());
        this.tableCommande.getItems().addAll(daoFactory.getCommandeDAO().findAll());
        this.tableClient.getItems().addAll(daoFactory.getClientDAO().findAll());
        this.tableProduit.getItems().addAll(daoFactory.getProduitDAO().findAll());

        rbListeM.setDisable(true);
        rbDatab.setDisable(true);
        btnPersistance.setDisable(true);
    }

    @FXML
    void btnAdd_onClick(MouseEvent event) {
        Scene scene = null;
        Stage stage = null;
        stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root;
            switch (visible){
                case 1:
                    //PRODUIT
                    if (DAOFactory.getDAOFactory(choix).getCategorieDAO().findAll() != null){
                        fxmlLoader = new FXMLLoader(getClass().getResource("../sample/addProduit.fxml"));
                        root = fxmlLoader.load();
                        controller_addProduit controller_addProduit = fxmlLoader.getController();
                        controller_addProduit.setupEnum(choix);
                        scene = new Scene(root);
                        stage = new Stage();
                        stage.setTitle("Ajouter un produit");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attention");
                        alert.setHeaderText("Impossible de créer le produit !");
                        alert.setContentText("il faut au moins une catégorie pour créer un produit");
                        alert.showAndWait();
                    }
                    break;
                case 2:
                    //CATEGORIE
                        fxmlLoader = new FXMLLoader(getClass().getResource("../sample/addCategorie.fxml"));
                        root = fxmlLoader.load();
                        controller_addCategorie controller_addCategorie = fxmlLoader.getController();
                        controller_addCategorie.setupEnum(choix);
                        scene = new Scene(root);
                        stage.setTitle("Ajouter une catégorie");
                    break;
                case 3:
                    //CLIENT
                    fxmlLoader = new FXMLLoader(getClass().getResource("../sample/addClient.fxml"));
                    root = fxmlLoader.load();
                    controller_addClient controller_addClient = fxmlLoader.getController();
                    controller_addClient.setupEnum(choix);
                    scene = new Scene(root);
                    stage.setTitle("Ajouter un client");
                    break;
                case 4:
                    //COMMANDE
                    if (DAOFactory.getDAOFactory(choix).getProduitDAO().findAll() != null && DAOFactory.getDAOFactory(choix).getClientDAO().findAll() != null){
                        fxmlLoader = new FXMLLoader(getClass().getResource("../sample/addCommande.fxml"));
                        root = fxmlLoader.load();
                        controller_addCommande controller_addCommande = fxmlLoader.getController();
                        controller_addCommande.setupEnum(choix);
                        scene = new Scene(root);
                        stage = new Stage();
                        stage.setTitle("Ajouter une commande");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attention");
                        alert.setHeaderText("Impossible de créer la commande !");
                        alert.setContentText("il faut au moins un produit et un client pour créer une commande");
                        alert.showAndWait();
                    }
                    break;
            }

            Image icon = new Image(getClass().getResourceAsStream("../images/iconTest.png"));
            assert stage != null;
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
        if (tableClient.getSelectionModel().getSelectedItem() != null) {

        } else if (tableCommande.getSelectionModel().getSelectedItem() != null) {

        } else if (tableProduit.getSelectionModel().getSelectedItem() != null) {

        } else if (tableCategorie.getSelectionModel().getSelectedItem() != null) {

        }
    }

    @FXML
    void btnSuppr_onClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText("Êtes-vous sûr de supprimer cette donnée ?");

        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Information");

        if (tableClient.getSelectionModel().getSelectedItem() != null) {
            Client client = daoFactory.getClientDAO().getById(tableClient.getSelectionModel().getSelectedItem().getId());
            if (containsClient(client, daoFactory.getCommandeDAO().findAll())) {
                informationAlert.setHeaderText("Ce client appartient à une commande");
                informationAlert.showAndWait();
            } else {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    daoFactory.getClientDAO().delete(client);
                    deleteTableData(tableClient);
                    this.tableClient.getItems().addAll(daoFactory.getClientDAO().findAll());
                    tableClient.getSelectionModel().clearSelection();
                }
            }
        }

        if (tableCommande.getSelectionModel().getSelectedItem() != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Commande commande = daoFactory.getCommandeDAO().getById(tableCommande.getSelectionModel().getSelectedItem().getId());
                daoFactory.getCommandeDAO().delete(commande);
                deleteTableData(tableCommande);
                this.tableCommande.getItems().addAll(daoFactory.getCommandeDAO().findAll());
                tableCommande.getSelectionModel().clearSelection();
            }
        }

        if (tableProduit.getSelectionModel().getSelectedItem() != null) {
            Produit produit = daoFactory.getProduitDAO().getById(tableProduit.getSelectionModel().getSelectedItem().getId());
            if (containsProduit(produit, daoFactory.getCommandeDAO().findAll())) {
                informationAlert.setHeaderText("Ce produit appartient à des commandes");
                informationAlert.showAndWait();
            } else {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    daoFactory.getProduitDAO().delete(produit);
                    deleteTableData(tableProduit);
                    this.tableProduit.getItems().addAll(daoFactory.getProduitDAO().findAll());
                    this.tableProduit.getSelectionModel().clearSelection();
                }
            }
        }

        if (tableCategorie.getSelectionModel().getSelectedItem() != null) {
            Categorie categorie = daoFactory.getCategorieDAO().getById(tableCategorie.getSelectionModel().getSelectedItem().getId());
            if (containsCategorie(categorie, daoFactory.getProduitDAO().findAll())) {
                informationAlert.setHeaderText("Cette catégorie appartient à des produits");
                informationAlert.showAndWait();
            }
            else {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    daoFactory.getCategorieDAO().delete(categorie);
                    deleteTableData(tableCategorie);
                    this.tableCategorie.getItems().addAll(daoFactory.getCategorieDAO().findAll());
                    this.tableCategorie.getSelectionModel().clearSelection();
                }
            }
        }
    }

    @FXML
    void mnuAbout_onClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message informatif");
        alert.setHeaderText(null);
        alert.setContentText("Application développée par Irma Houver Sing et Gauthier Cadet.\nEn cas de problème veuillez nous contacter.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("../images/iconTest.png").toString()));
        alert.showAndWait();
    }

    @FXML
    void mnuQuitter_onClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter la fenêtre");
        alert.setHeaderText("Êtes-vous sûr de vouloir quitter l'application ?");
        alert.setContentText("Toute progression non sauvegardée sera perdue");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("../images/iconTest.png").toString()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    //tools
    private void deleteTableData(TableView table) {
        table.getItems().clear();
    }

    //functions boolean
    private boolean containsClient(Client client, ArrayList<Commande> listCommande) {
        boolean res = false;
        for (Commande commande : listCommande) {
            if (commande.getClient().equals(client)) {
                res = true;
            }
        }
        return res;
    }

    private boolean containsCategorie(Categorie categorie, ArrayList<Produit> listProduit) {
        boolean res = false;
        for (Produit produit : listProduit) {
            if (produit.getCategory().equals(categorie)) {
                res = true;
            }
        }
        return res;
    }

    private boolean containsProduit(Produit produit, ArrayList<Commande> listCommande) {
        boolean res = false;
        for (Commande commande : listCommande) {
            Map<Produit, Integer> listProduit = commande.getProduits();
            Iterator iterator = listProduit.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();
                if (pair.getKey().equals(produit)) {
                    res = true;
                }
            }
        }
        return res;
    }
}