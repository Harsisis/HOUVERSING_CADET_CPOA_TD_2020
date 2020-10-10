package main;

//import main.ui.menuTD1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.ui.menuTD2;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            URL fxmlURL=getClass().getResource("/fenetre.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((VBox) root, 600, 400);

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Ma première fenêtre JavaFX");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        //create window TD1
        //new menuTD1();

        //create window TD2
        //new menuTD2();

        // launch application project
        Application.launch(args);
    }
}