package controllers;

import javafx.scene.Node;
import javafx.scene.Scene;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Cours;
import entities.Goals;
import entities.TypeCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.ServiceGoals;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;


public class AffichageJointureController {

    public Label hom;
    public ImageView adm;
    @FXML
    private TableView<Goals> tv_Join;
    @FXML
    private ImageView qrcode;
    @FXML
    private Pagination pagination;

    private final int itemsPerPage = 3;
    private ObservableList<Goals> displayedData; // Les données à afficher sur la page actuelle

    private int totalPages;
    public TextField tf_recherche;
    public ImageView rech;
    public TextField tf_ask;
    public Button btnenvoyer;
    public TextField tfto;
    public TextField tfmsg;

    public HBox paginationContainer;

    @FXML
    private TableColumn<TypeCours, Integer> col_calories;

    @FXML
    private TableColumn<Cours, String> col_cours;

    @FXML
    private TableColumn<TypeCours, String> col_objectif;

    @FXML
    private TableColumn<Cours, String> col_salle;
    @FXML
    private TableColumn<Cours, Integer> col_duree;

    @FXML
    private TableColumn<Cours, String> col_horaire;
    @FXML
    private Button btnReserver;

    @FXML
    private TextField tf_rep;


    private Goals selectedGoal;

    @FXML
    private ComboBox<String> comboBoxTri;
    ServiceGoals serviceGoals = new ServiceGoals();

    public ObservableList<Goals> data = FXCollections.observableArrayList();

   @FXML
   void initialize() {
       // Configurez les cellules de chaque colonne
       col_cours.setCellValueFactory(new PropertyValueFactory<>("nom"));
       col_salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
       col_objectif.setCellValueFactory(new PropertyValueFactory<>("objective"));
       col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
       col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       col_horaire.setCellValueFactory(new PropertyValueFactory<>("horaire"));

       // Chargement initial des données
       loadGoals();

       // Récupération des descriptions de tous les cours
       StringBuilder descriptions = new StringBuilder();
       for (Goals goal : data) {
           descriptions.append(goal.getNom()).append(": ").append(goal.getObjective()).append("\n");
       }

       // Générer l'image du code QR avec les descriptions des cours
       Image qrCodeImage = genererQRCode(descriptions.toString());

       // Attribuer l'image à votre ImageView qrcode
       qrcode.setImage(qrCodeImage);

       // Ajout d'un ChangeListener pour la recherche dynamique
       tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
           // Vérifiez si le champ de recherche n'est pas vide
           if (!newValue.isEmpty()) {
               // Filtrer les objectifs selon le critère de recherche
               ObservableList<Goals> filteredGoals = FXCollections.observableArrayList();

               for (Goals goal : data) {
                   // Recherche insensible à la casse dans l'attribut 'nom'
                   if (goal.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                       filteredGoals.add(goal);
                   }
               }

               // Mettre à jour les données affichées dans la TableView avec les résultats de la recherche
               tv_Join.setItems(filteredGoals);
           } else {
               // Si le champ de recherche est vide, réafficher tous les objectifs
               tv_Join.setItems(data);
           }
       });

       // Ajout du ComboBox pour le tri
       comboBoxTri.getItems().addAll("Tri par nom", "Tri par salle");

       // Ajout d'un écouteur d'événements au ComboBox pour détecter les changements de sélection
       comboBoxTri.setOnAction(this::handleTriSelection);
   }
    private void handleTriSelection(ActionEvent event) {
        String selectedTri = comboBoxTri.getValue();

        if (selectedTri != null) {
            // Effectuer le tri en fonction de l'option sélectionnée dans le ComboBox
            switch (selectedTri) {
                case "Tri par nom":
                    // Tri des données par nom dans l'ordre alphabétique
                    FXCollections.sort(data, Comparator.comparing(Goals::getNom));
                    break;
                case "Tri par salle":
                    // Tri des données par salle dans l'ordre alphabétique
                    FXCollections.sort(data, Comparator.comparing(Goals::getSalle));
                    break;
                default:
                    break;
            }

            // Actualiser la pagination après le tri
            // pagination.setPageFactory(this::createPage);
        }
    }


    // Méthode pour charger les objectifs
   private void loadGoals() {
        try {
            List<Goals> goalsList = serviceGoals.getAllGoals();
            data.addAll(goalsList); // Ajout des objectifs à la liste observable
            tv_Join.setItems(data); // Affichage initial des objectifs dans la TableView
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des objectifs : " + e.getMessage());
        }
    }

   public void ExporterCours(ActionEvent actionEvent) {
       Goals selectedGoal = tv_Join.getSelectionModel().getSelectedItem();

       if (selectedGoal != null) {
           Document document = new Document();
           try {
               String filePath = "1.pdf";
               PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
               document.open();

               // Ajout du titre
               Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.GRAY);
               Paragraph title = new Paragraph("Détails du cours", titleFont);
               title.setAlignment(Element.ALIGN_CENTER);
               document.add(title);

               // Ajout de l'image
              com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("Plan de travail 1.png"); // Remplacez "loog.png" par le chemin de votre image
               logo.setAlignment(Element.ALIGN_CENTER);
               logo.scaleToFit(100, 100);
               document.add(logo);

               PdfPTable table = new PdfPTable(2); // 2 colonnes
               table.setWidthPercentage(100);

               // Ajouter les données de chaque colonne
               table.addCell(createCell("Nom du cours", selectedGoal.getNom()));
               table.addCell(createCell("Salle", selectedGoal.getSalle()));
               table.addCell(createCell("Objectif", selectedGoal.getObjective()));
               table.addCell(createCell("Calories", String.valueOf(selectedGoal.getCalories())));
               table.addCell(createCell("Durée", String.valueOf(selectedGoal.getDuree())));
               table.addCell(createCell("Horaire", selectedGoal.getHoraire()));

               // Ajouter la bordure supérieure bleue
               PdfPCell cell = new PdfPCell(new Phrase(""));
               cell.setBorder(Rectangle.TOP);
               cell.setBorderColor(BaseColor.BLUE);
               table.addCell(cell);

               // Ajouter le message de bienvenue centré en dessous du tableau
               PdfPCell welcomeCell = new PdfPCell(new Phrase("Bienvenue ! "));
               welcomeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
              welcomeCell.setColspan(2);
               table.addCell(welcomeCell);

               // Ajouter la table au document PDF
               document.add(table);
               Font ruleFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
               Paragraph ruleParagraph = new Paragraph();
               ruleParagraph.add(new Chunk("Règlement intérieur\n\n", titleFont));
               ruleParagraph.add(new Chunk("1. Adhésion et Abonnement\nTous les membres doivent souscrire à un abonnement valide pour accéder à la salle de sport.\n" +
                       "Les tarifs d'abonnement sont définis selon les plans disponibles et sont sujets à des frais supplémentaires pour certains services.\n" +
                       "\n" +
                       "2. Horaires d'Ouverture\n" +

                       "Les horaires d'ouverture de la salle de sport sont affichés à l'entrée et peuvent varier en fonction des jours fériés ou des événements spéciaux.\n" +
                       "\n" +
                       "3. Utilisation des Équipements\n" +

                       "Les membres doivent utiliser les équipements de la salle de sport de manière responsable et suivre les instructions affichées pour leur utilisation.\n" +
                       "Les poids et autres équipements doivent être rangés après utilisation.\n" +
                       "Il est interdit d'utiliser les équipements de manière abusive ou dangereuse.\n" +
                       "\n" +
                       "4. Hygiène et Propreté\n" +

                       "Les membres sont tenus de maintenir un niveau d'hygiène approprié en utilisant des serviettes pour s'essuyer et en nettoyant les équipements après utilisation.\n" +
                       "Les bouteilles d'eau doivent être refermées en tout temps pour éviter les déversements.\n" +
                       "\n" +
                       "5. Comportement\n" +

                       "Tout comportement perturbateur ou agressif envers les autres membres ou le personnel de la salle de sport est strictement interdit.\n" +
                       "Il est interdit de fumer, de boire de l'alcool ou de consommer des drogues dans les locaux de la salle de sport.\n" +
                       "\n" +
                       "6. Sécurité\n" +

                       "Les membres doivent respecter toutes les consignes de sécurité affichées dans la salle de sport.\n" +
                       "En cas d'urgence ou de blessure, les membres doivent contacter immédiatement le personnel de la salle de sport.\n" +
                       "\n" +
                       "7. Responsabilité\n" +

                       "La direction de la salle de sport n'est pas responsable des pertes, vols ou dommages matériels subis par les membres.\n" +
                       "Chaque membre est responsable de ses effets personnels et de sa sécurité pendant son séjour à la salle de sport.\n" +

                       "\n" +


                       "En signant cet accord, je confirme avoir lu et accepté les termes et conditions du règlement de la salle de sport.\n\n"));
               document.add(ruleParagraph);

               // Ajouter l'espace pour la signature
               Paragraph signature = new Paragraph("Signature : ________________________________________", ruleFont);
               signature.setAlignment(Element.ALIGN_RIGHT);
               document.add(signature);
               document.close();
               writer.close();
               System.out.println("PDF généré avec succès !");
               openPDF(filePath);
           } catch (DocumentException | FileNotFoundException e) {
               e.printStackTrace();
               System.out.println("Erreur lors de la génération du PDF : " + e.getMessage());
           } catch (IOException e) {
               e.printStackTrace();
               System.out.println("Erreur lors de l'ouverture du PDF : " + e.getMessage());
           }
       } else {
           System.out.println("Aucune ligne sélectionnée !");
       }
   }
    private PdfPCell createCell(String label, String value) {
        // Créer une cellule pour contenir à la fois le label et la valeur
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);

        // Créer un paragraphe pour le label
        Paragraph labelParagraph = new Paragraph(label + ": ");
        labelParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD)); // Mettre le label en gras
        cell.addElement(labelParagraph);

        // Ajouter la valeur à la cellule
        cell.addElement(new Phrase(value));

        return cell;
    }



    private void openPDF(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("Le fichier PDF n'existe pas : " + filePath);
        }
    }

    // Méthode pour ouvrir un fichier PDF avec le lecteur par défaut du système


    public void ImprimerCours(ActionEvent actionEvent) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Récupérer la fenêtre principale
            Stage primaryStage = (Stage) tv_Join.getScene().getWindow();

            // Afficher la boîte de dialogue d'impression
            boolean printDialogShown = job.showPrintDialog(primaryStage);

            if (printDialogShown) {
                // Imprimer la page
                boolean success = job.printPage(tv_Join);

                if (success) {
                    job.endJob();
                    System.out.println("Impression terminée avec succès!");
                } else {
                    System.out.println("Erreur lors de l'impression!");
                }
            } else {
                System.out.println("L'utilisateur a annulé l'impression!");
            }
        }
    }

   public Image genererQRCode(String recherche) {
       try {
           // Créer l'URL de recherche Google
           String urlGoogle = "https://www.google.com/search?q=" + URLEncoder.encode(recherche, "UTF-8");

           // Définir les paramètres pour la génération du code QR
           Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
           hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

           // Générer le code QR
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
           BitMatrix bitMatrix = qrCodeWriter.encode(urlGoogle, BarcodeFormat.QR_CODE, 200, 200, hints);

           // Créer une BufferedImage pour le code QR
           int width = bitMatrix.getWidth();
           int height = bitMatrix.getHeight();
           BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

           // Remplir la BufferedImage avec les données du BitMatrix
           for (int x = 0; x < width; x++) {
               for (int y = 0; y < height; y++) {
                  qrImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
               }
           }

           // Convertir BufferedImage en Image JavaFX
           Image image = SwingFXUtils.toFXImage(qrImage, null);
           return image;
       } catch (WriterException | UnsupportedEncodingException e) {
           e.printStackTrace();
           return null;
       }
   }

    public void GoHome(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
            hom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }

    public void godash(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/bg.fxml"));
            hom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}







