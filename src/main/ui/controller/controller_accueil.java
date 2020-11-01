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

    private static controller_accueil INSTANCE;

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

        INSTANCE = this;
    }

    public static controller_accueil getInstance() {
        return INSTANCE;
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

    private void btnCrud() {
        btnAdd.setDisable(false);
        btnEdit.setDisable(true);
        btnSuppr.setDisable(true);
    }

    @FXML
    void showTableProduit(MouseEvent event) {
        refreshProduit();
        tableCategorie.setVisible(false);
        tableCommande.setVisible(false);
        tableClient.setVisible(false);
        tableProduit.setVisible(true);
        visible = 1;
        btnCrud();
    }

    @FXML
    void showTableCategorie(MouseEvent event) {
        refreshCategorie();
        tableProduit.setVisible(false);
        tableClient.setVisible(false);
        tableCommande.setVisible(false);
        tableCategorie.setVisible(true);
        visible = 2;
        btnCrud();
    }

    @FXML
    void showTableClient(MouseEvent event) {
        refreshClient();
        tableCommande.setVisible(false);
        tableProduit.setVisible(false);
        tableCategorie.setVisible(false);
        tableClient.setVisible(true);
        visible = 3;
        btnCrud();
    }

    @FXML
    void showTableCommande(MouseEvent event) {
        refreshCommande();
        tableClient.setVisible(false);
        tableCategorie.setVisible(false);
        tableProduit.setVisible(false);
        tableCommande.setVisible(true);
        visible = 4;
        btnCrud();
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
        refreshCategorie();
        refreshCommande();
        refreshClient();
        refreshProduit();

        rbListeM.setDisable(true);
        rbDatab.setDisable(true);
        btnPersistance.setDisable(true);
    }

    @FXML
    void btnAdd_onClick(MouseEvent event) {
        Scene scene = null;
        Stage stage = new Stage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        try {
            switch (visible){
                case 1:
                    FXMLLoader fxmlLoaderProduit = new FXMLLoader();
                    Parent rootProduit;
                    //PRODUIT
                    if (daoFactory.getCategorieDAO().findAll().isEmpty()) {
                        alert.setHeaderText("Impossible de créer le produit !");
                        alert.setContentText("il faut au moins une catégorie pour créer un produit");
                        alert.showAndWait();
                    }
                    else {
                        fxmlLoaderProduit = new FXMLLoader(getClass().getResource("../sample/addProduct.fxml"));
                        rootProduit = fxmlLoaderProduit.load();
                        controller_addProduit controller_addProduit = fxmlLoaderProduit.getController();
                        controller_addProduit.setupEnum(choix);
                        scene = new Scene(rootProduit);
                        stage.setTitle("Ajouter un produit");
                        addFenetre(scene, stage);
                        refreshProduit();
                    }
                    break;
                case 2:
                    FXMLLoader fxmlLoaderCategorie = new FXMLLoader();
                    Parent rootCategorie;
                    //CATEGORIE
                    fxmlLoaderCategorie = new FXMLLoader(getClass().getResource("../sample/addCategorie.fxml"));
                    rootCategorie = fxmlLoaderCategorie.load();
                    controller_addCategorie controller_addCategorie = fxmlLoaderCategorie.getController();
                    controller_addCategorie.setupEnum(choix);
                    scene = new Scene(rootCategorie);
                    stage.setTitle("Ajouter une catégorie");
                    addFenetre(scene, stage);
                    refreshCategorie();
                    break;
                case 3:
                    FXMLLoader fxmlLoaderClient = new FXMLLoader();
                    Parent rootClient;
                    //CLIENT
                    fxmlLoaderClient = new FXMLLoader(getClass().getResource("../sample/addClient.fxml"));
                    rootClient = fxmlLoaderClient.load();
                    controller_addClient controller_addClient = fxmlLoaderClient.getController();
                    controller_addClient.setupEnum(choix);
                    scene = new Scene(rootClient);
                    stage.setTitle("Ajouter un client");
                    addFenetre(scene, stage);
                    refreshClient();
                    break;
                case 4:
                    FXMLLoader fxmlLoaderCommande = new FXMLLoader();
                    Parent rootCommande;
                    //COMMANDE
                    if (!daoFactory.getProduitDAO().findAll().isEmpty() && !daoFactory.getClientDAO().findAll().isEmpty()){
                        fxmlLoaderCommande = new FXMLLoader(getClass().getResource("../sample/addCommande.fxml"));
                        rootCommande = fxmlLoaderCommande.load();
                        controller_addCommande controller_addCommande = fxmlLoaderCommande.getController();
                        controller_addCommande.setupEnum(choix);
                        scene = new Scene(rootCommande);
                        stage.setTitle("Ajouter une commande");
                        addFenetre(scene, stage);
                        refreshCommande();
                    }
                    else {
                        alert.setHeaderText("Impossible de créer la commande !");
                        alert.setContentText("il faut au moins un produit et un client pour créer une commande");
                        alert.showAndWait();
                    }
                    break;
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    private void addFenetre(Scene scene, Stage stage) {
        Image icon = new Image(getClass().getResourceAsStream("../images/iconTest.png"));
        assert stage != null;
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnEdit_onClick(MouseEvent event) {
        Scene scene = null;
        Stage stage = new Stage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        try {
            switch (visible){
                case 1:
                    Produit produit = tableProduit.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoaderProduit = new FXMLLoader();
                    Parent rootProduit;
                    //PRODUIT
                    fxmlLoaderProduit = new FXMLLoader(getClass().getResource("../sample/addProduct.fxml"));
                    rootProduit = fxmlLoaderProduit.load();
                    controller_addProduit controller_addProduit = fxmlLoaderProduit.getController();
                    controller_addProduit.setupEnum(choix);
                    controller_addProduit.setupProduit(produit);
                    scene = new Scene(rootProduit);
                    stage.setTitle("Modifier un produit");
                    addFenetre(scene, stage);
                    refreshProduit();
                    break;
                case 2:
                    Categorie categorie = tableCategorie.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoaderCategorie = new FXMLLoader();
                    Parent rootCategorie;
                    //CATEGORIE
                    fxmlLoaderCategorie = new FXMLLoader(getClass().getResource("../sample/addCategorie.fxml"));
                    rootCategorie = fxmlLoaderCategorie.load();
                    controller_addCategorie controller_addCategorie = fxmlLoaderCategorie.getController();
                    controller_addCategorie.setupEnum(choix);
                    controller_addCategorie.setupCateg(categorie);
                    scene = new Scene(rootCategorie);
                    stage.setTitle("Modifier une catégorie");
                    addFenetre(scene, stage);
                    refreshCategorie();
                    break;
                case 3:
                    Client client = tableClient.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoaderClient = new FXMLLoader();
                    Parent rootClient;
                    //CLIENT
                    fxmlLoaderClient = new FXMLLoader(getClass().getResource("../sample/addClient.fxml"));
                    rootClient = fxmlLoaderClient.load();
                    controller_addClient controller_addClient = fxmlLoaderClient.getController();
                    controller_addClient.setupEnum(choix);
                    controller_addClient.setupClient(client);
                    scene = new Scene(rootClient);
                    stage.setTitle("Modifier un client");
                    addFenetre(scene, stage);
                    refreshClient();
                    break;
                case 4:
                    Commande commande = tableCommande.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoaderCommande = new FXMLLoader();
                    Parent rootCommande;
                    //COMMANDE
                    fxmlLoaderCommande = new FXMLLoader(getClass().getResource("../sample/addCommande.fxml"));
                    rootCommande = fxmlLoaderCommande.load();
                    controller_addCommande controller_addCommande = fxmlLoaderCommande.getController();
                    controller_addCommande.setupEnum(choix);
                    controller_addCommande.setupCommande(commande);
                    scene = new Scene(rootCommande);
                    stage.setTitle("Modifier une commande");
                    addFenetre(scene, stage);
                    refreshCommande();
                    break;
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
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
                    refreshClient();
                }
            }
        }

        if (tableCommande.getSelectionModel().getSelectedItem() != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Commande commande = daoFactory.getCommandeDAO().getById(tableCommande.getSelectionModel().getSelectedItem().getId());
                daoFactory.getCommandeDAO().delete(commande);
                refreshCommande();
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
                    refreshProduit();
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
                    refreshCategorie();
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

    //refresh table
    public void refreshClient() {
        deleteTableData(tableClient);
        this.tableClient.getItems().addAll(daoFactory.getClientDAO().findAll());
        tableClient.getSelectionModel().clearSelection();
    }

    public void refreshCommande() {
        deleteTableData(tableCommande);
        this.tableCommande.getItems().addAll(daoFactory.getCommandeDAO().findAll());
        tableCommande.getSelectionModel().clearSelection();
    }

    public void refreshProduit() {
        deleteTableData(tableProduit);
        this.tableProduit.getItems().addAll(daoFactory.getProduitDAO().findAll());
        this.tableProduit.getSelectionModel().clearSelection();
    }

    public void refreshCategorie() {
        deleteTableData(tableCategorie);
        this.tableCategorie.getItems().addAll(daoFactory.getCategorieDAO().findAll());
        this.tableCategorie.getSelectionModel().clearSelection();
    }

    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) {
            btnEdit.setDisable(false);
            btnSuppr.setDisable(false);
        }
        else {
            btnEdit.setDisable(true);
            btnSuppr.setDisable(true);
        }
    }
}