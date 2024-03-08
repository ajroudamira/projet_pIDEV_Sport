package utils;

import com.twilio.rest.chat.v1.service.User;
import controllers.signupController;
import entities.Utilisateur;
import javafx.event.ActionEvent;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

    public class SendMail {
        public static void SendMail(ActionEvent event, int Rand, Utilisateur user) {
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port",465);
            props.put("mail.smtp.user","malek.benjalloul@esprit.tn");
            props.put("mail.smtp.auth",true);
            props.put("mail.smtp.starttls.enable",true);
            props.put("mail.smtp.debug",true);
            props.put("mail.smtp.socketFactory.port",465);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback",false);

            try {
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("malek.benjalloul@esprit.tn"));
                message.setText("Message");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                try
                {
                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com","malek.benjalloul@esprit.tn","211JMT9399");
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    smtp.gmail.com
}
