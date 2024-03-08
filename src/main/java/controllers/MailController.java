package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailController {


    public ImageView back;
    @FXML
    private Button btnenvoyer;

    @FXML
    private TextField tfmsg;

    @FXML
    private TextField tfto;

    @FXML
    private void envoyer(ActionEvent event) {
        String aemail = tfto.getText();
        String fromemail = "chikhaouifarah7@gmail.com";

        String Subject = "Modification de cours";
        String emailpassword = "nsuo ujst yvqk fzci"; // Votre mot de passe e-mail

        // Paramètres de la session SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Création de la session
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromemail, emailpassword);
            }
        });

        try {
            // Création du message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromemail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(aemail));
            message.setSubject(Subject);

            // Corps du message avec le texte supplémentaire sur les modifications du cours
            String messageText = tfmsg.getText() + "\n\nCours : Modifié";

            // Définition du corps du message
            message.setText(messageText);

            // Envoi du message
            Transport.send(message);
            System.out.println("Message envoyé!");

            // Fermeture de la fenêtre
            Stage stage = (Stage) btnenvoyer.getScene().getWindow();
            stage.close();

        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }


    @FXML
    void initialize() {
        assert btnenvoyer != null : "fx:id=\"btnenvoyer\" was not injected: check your FXML file 'mail.fxml'.";
        assert tfmsg != null : "fx:id=\"tfmsg\" was not injected: check your FXML file 'mail.fxml'.";
        assert tfto != null : "fx:id=\"tfto\" was not injected: check your FXML file 'mail.fxml'.";

    }

    public void backk(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageCours.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
