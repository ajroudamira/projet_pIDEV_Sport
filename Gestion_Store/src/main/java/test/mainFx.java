package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
     try {
//         Parent root = FXMLLoader.load(getClass().getResource("/Front_Produit.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/BackHome.fxml"));
        primaryStage.setTitle("FXML Welcome");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }catch (IOException e){
         System.out.println(e.getMessage());
     }
    }
}
