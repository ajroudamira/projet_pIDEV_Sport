package tn.esprit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
       FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/ajouter-equipement-view.fxml"));//ADMIN
        //FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/user-equipement-view.fxml"));//USER

        Scene scene=new Scene(fxmlLoader.load());
        primaryStage.setTitle("Gym Sync");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
