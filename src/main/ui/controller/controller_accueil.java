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
import main.pojo.Produit;

import java.net.URL;
import java.util.ResourceBundle;

public class controller_accueil implements Initializable {

    @FXML
    private TableView<Categorie> tableCategorie;

    @FXML
    private TableColumn<Categorie, Integer> colCatId;

    @FXML
    private TableColumn<Categorie, String> colCatTitre;

    @FXML
    private TableColumn<Categorie, String> colCatVisuel;

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
        colProdIdCat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        this.tableProduit.getColumns().setAll(colProdId, colProdNom, colProdDescription, colProdTarif, colProdVisuel, colProdIdCat);
        this.tableProduit.getItems().addAll(DAOFactory.getDAOFactory(EPersistence.MYSQL).getProduitDAO().findAll());
    }

    @FXML
    void showTableCategorie(MouseEvent event) {
        this.tableCategorie.refresh();
        tableProduit.setVisible(false);
        tableCategorie.setVisible(true);
    }

    @FXML
    void showTableProduit(MouseEvent event) {
        this.tableProduit.refresh();
        tableCategorie.setVisible(false);
        tableProduit.setVisible(true);
    }
}