package controllers;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import services.ServiceUtilisateurs;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private Button loginButton, signupLogin;
    @FXML
    private Label echecLoginLabel;
    @FXML
    private TextField pseudoLogin, mdpTextLogin;
    @FXML
    private PasswordField mdpLogin;
    @FXML
    private Button eyeIconLogin;

    ServiceUtilisateurs serviceUtilisateurs = new ServiceUtilisateurs();

    private void handleLogin(ActionEvent event) {
        try {
            if (!serviceUtilisateurs.utilisateurLoggedIn(pseudoLogin.getText(), mdpLogin.getText())) {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo ou mot de passe incorrect");
            } else {
                Utilisateur utilisateur = serviceUtilisateurs.afficherParPseudo(pseudoLogin.getText());
                echecLoginLabel.setTextFill(Color.GREEN);
                echecLoginLabel.setText("Bienvenue, " + utilisateur.getNom());

                switch (utilisateur.getRole()) {
                    case "Admin":
                        serviceUtilisateurs.changeScreen(event, "/dashboard.fxml", "Admin");
                        break;
                    case "Client":
                        serviceUtilisateurs.changeScreen(event, "/clientFront.fxml", "Client");
                        break;
                       default:
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage());
        }
    }

    public void loginButtonOnClick(ActionEvent event){
        if (!mdpLogin.isVisible()){
            if (!pseudoLogin.getText().isBlank() && !mdpTextLogin.getText().isBlank()) {
                mdpLogin.setText(mdpTextLogin.getText());
                handleLogin(event);
            } else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Entrer correctement votre pseudo et mot de passe");
            }
        }
        else {
            if (!pseudoLogin.getText().isBlank()&& !mdpLogin.getText().isBlank()){
                handleLogin(event);
            }
            else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Entrer correctement votre pseudo et mot de passe");
            }

        }

    }

    public void signupLoginButtonOnClick(ActionEvent event){
        serviceUtilisateurs.changeScreen(event, "/signup.fxml", "Sign Up");
    }
    @FXML
    void eyeIconLoginButtonOnClick(ActionEvent event) {
        // Vérifier si le mot de passe est actuellement visible ou non
        boolean isPasswordVisible = mdpLogin.isVisible();
        mdpLogin.setVisible(!isPasswordVisible);

        // Changer l'image du bouton en conséquence
        if (isPasswordVisible) {
            mdpTextLogin.setText(mdpLogin.getText());
            mdpTextLogin.setVisible(true);
            eyeIconLogin.setStyle("-fx-background-image : url('../../resources/Design/eyeIcon1.png')");
        } else {
            mdpLogin.setText(mdpTextLogin.getText());
            eyeIconLogin.setStyle("-fx-background-image: url('../../resources/Design/eyeIcon.png')");
        }
    }
    @FXML
    void GoogleLogin(ActionEvent event) {
        // Your updated JSON data
        // Parse JSON
        String jsonData = "{\"web\":{\"client_id\":\"34198700278-5o1n0105jn1u2m8odioepf3kavqh04dn.apps.googleusercontent.com\",\"project_id\":\"batahapp-415619\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost/google/callback/\"]}}";

        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        JsonObject web = jsonObject.getAsJsonObject("web");
        String gClientId = web.get("client_id").getAsString();
        String gRedir = web.get("redirect_uris").getAsJsonArray().get(0).getAsString(); // Assuming there's only one redirect URI
        String gScope = "https://www.googleapis.com/auth/userinfo.profile " +
                "https://www.googleapis.com/auth/userinfo.email " +
                "https://www.googleapis.com/auth/user.phonenumbers.read";
        String gSecret = web.get("client_secret").getAsString();

        OAuthAuthenticator auth = new OAuthGoogleAuthenticator(gClientId, gRedir, gSecret, gScope);
        auth.startLogin(() -> {
            serviceUtilisateurs.changeScreen(event, "/dashboard.fxml", "Admin");


        });
    }
//    @FXML
//    void closeClicked(ActionEvent event) {
//        Node source = (Node) event.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
//
//    }

    }


