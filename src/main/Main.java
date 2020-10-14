package main;

//import main.ui.menuTD1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.ui.menuTD2;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ui/sample/addProduct.fxml"));
            Scene scene = new Scene(root, 374, 400);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter un Produit");
            Image icon = new Image(getClass().getResourceAsStream("ui/images/iconTest.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create window TD1
        //new menuTD1();

        //create window TD2
        //new menuTD2();

        // launch application project
        Application.launch(args);
    }
}