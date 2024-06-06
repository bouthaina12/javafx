/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;



import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author bouth
 */
public class InscriptionController implements Initializable {

    
    @FXML
    private TextField conf_id_textbox;

    @FXML
    private TableView<conference> conferenceTableView;

    @FXML
    private TextField email_textbox;

    @FXML
    private TableColumn<conference,String> enddate_conference;

    @FXML
    private TableColumn<conference,String> fees_conference;

    @FXML
    private TableColumn<conference,Integer> id_conference;

    @FXML
    private Button inscription_btn;

    @FXML
    private AnchorPane inscription_form;

    @FXML
    private Button inscrire_btn;

    @FXML
    private TableColumn<conference,String> institution_conference;

    @FXML
    private TextField institution_textbox;

    @FXML
    private TableColumn<conference,String> location_conference;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button main_screen_btn;

    @FXML
    private ComboBox<String> mode_paiment_combo;

    @FXML
    private Button my_papers_btn;

    @FXML
    private TextField name_textbox;
    @FXML
    private TextField titre_textbox;
       @FXML
    private Button submit;
      @FXML
    private TextField fichier_pdf_textbox;
      
       @FXML
    private TextField conf_id_textbox1;

    @FXML
    private TableColumn<conference,Integer> president_conference;

    @FXML
    private TableColumn<conference,String> registrationddline_conference;

    @FXML
    private Button soumission_btn;

    @FXML
    private TableColumn<conference,String> startdate_conference;

    @FXML
    private Button statistique_btn;

    @FXML
    private TableColumn<conference,String> submissionddline_conference;

    @FXML
    private TableColumn<conference,String> title_conference;
   //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private File selectedPdfFile;

    String query;
    conference conference;
     public Integer userId;
    ObservableList<conference>ConferenceList=FXCollections.observableArrayList();
    @FXML
    void display_conference(MouseEvent event) {

    }

    @FXML
    void go_to(ActionEvent event) throws IOException {
// Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource(" Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }
    
        @FXML
    void go_to_my_papers(ActionEvent event) throws IOException {
  // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("my_papers.fxml"));
        Parent root = loader.load();
        My_papersController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }
       

     // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
// Method to retrieve user ID from user table based on email
    private int getUserIdFromEmail(String email) throws SQLException {
        connection=dbconnexion.getConnection();

        // Prepare and execute query to get user ID
        query = "SELECT utilisateur_id FROM utilisateur WHERE adresse_email = ?";
        prepare= connection.prepareStatement(query);
        prepare.setString(1, email);
        result = prepare.executeQuery();

        // Extract user ID if found
        Integer userId = -1;
        if (result.next()) {
            userId = result.getInt("utilisateur_id");
        }

        // Close resources
        result.close();
        prepare.close();
        connection.close();

        return userId;
    }
// Method to check if registration is open for a conference
    private boolean isRegistrationOpen(int conferenceId) throws SQLException {
        connection=dbconnexion.getConnection();

        // Prepare and execute query to get registration deadline
        query = "SELECT date_limite_inscriptions FROM conference WHERE conference_id = ?";
        prepare= connection.prepareStatement(query);
        prepare.setInt(1, conferenceId);
        result = prepare.executeQuery();

        // Check if deadline exists and compare with current date
        boolean isOpen = false;
        if (result.next()) {
            Date deadline =result.getDate("date_limite_inscriptions");
            LocalDate today = LocalDate.now();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String deadlineString = sdf.format(deadline);

LocalDate deadlineDate = LocalDate.parse(deadlineString);
isOpen = today.isBefore(deadlineDate);
        }

        // Close resources
        result.close();
        prepare.close();
        connection.close();

        return isOpen;
    }
    @FXML
    void inscrire(ActionEvent event) throws SQLException {
          // Get user data from textboxes
        String email = email_textbox.getText();
        String name = name_textbox.getText();
        String institution = institution_textbox.getText();
        Integer confId=Integer.valueOf(conf_id_textbox.getText());
    

        String modePaiement = mode_paiment_combo.getValue();
        userId = getUserIdFromEmail(email);
         // Check if registration deadline has passed
        if (!isRegistrationOpen(confId)) {
            showAlert("Registration deadline has passed for this conference.");
            return;
        }
         // Insert new participant into participant table
        insertParticipant(userId, confId, modePaiement);
      showAlert("inscription success");

    }
    // Method to insert new participant into participant table
    private void insertParticipant(Integer userId, Integer conferenceId, String modePaiement) throws SQLException {
        connection=dbconnexion.getConnection();

        // Prepare and execute query to insert participant
        query = "INSERT INTO inscriptionparticipant (conference_id, utilisateur_id, mode_paiement) VALUES (?, ?, ?)";
        prepare= connection.prepareStatement(query);
        prepare.setInt(1, conferenceId);
        prepare.setInt(2, userId);
        prepare.setString(3, modePaiement);
        prepare.executeUpdate();

        // Close resources
        prepare.close();
        prepare.close();
    }
    
    
    
    public void afficher_conference(){
        try{
     connection=dbconnexion.getConnection();
     query="SELECT * FROM conference";
     prepare=connection.prepareStatement(query);
     result=prepare.executeQuery();
     while(result.next()){
     ConferenceList.add(new conference(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10)));
     
     }
     connection.close();
     
     
     }catch(Exception e){
     e.printStackTrace();}
     
    id_conference.setCellValueFactory(new PropertyValueFactory<conference,Integer>("conference_id"));
    title_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("nom"));
    institution_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("institution_organisatrice"));
    president_conference.setCellValueFactory(new PropertyValueFactory<conference,Integer>("utilisateur_id"));
    startdate_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("date_debut"));
    enddate_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("date_fin"));
    submissionddline_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("date_limite_soumission_articles"));
    registrationddline_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("date_limite_inscriptions"));
    fees_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("frais_inscription"));
    location_conference.setCellValueFactory(new PropertyValueFactory<conference,String>("lieu"));

    conferenceTableView.setItems(ConferenceList);

}    private File choosePdfFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf"));
        return fileChooser.showOpenDialog(null);
    }
    @FXML
    void pdf(ActionEvent event) {

         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose PDF File");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
    );
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
        // Handle the selected PDF file, such as displaying its path or storing it
        System.out.println("Selected PDF file: " + selectedFile.getAbsolutePath());
        // Insert article data into database
        String filePath = selectedFile.getAbsolutePath();
        fichier_pdf_textbox.setText(filePath);

    }

    }
    

       @FXML
    void submit(ActionEvent event) throws SQLException {
        
          
          

            // Get article title from text box
             String titre = titre_textbox.getText();
             String filePath = fichier_pdf_textbox.getText();

            // Check if submission deadline has passed (optional, implement logic)

            

            // Show success message

       
          Integer confId=Integer.valueOf(conf_id_textbox1.getText());
          connection=dbconnexion.getConnection();
          // Prepare and execute query to get registration deadline
        query = "SELECT date_limite_soumission_articles FROM conference WHERE conference_id = ?";
        prepare= connection.prepareStatement(query);
        prepare.setInt(1, confId);
        result = prepare.executeQuery();

        // Check if deadline exists and compare with current date
        boolean isOpen = false;
        if (result.next()) {
            Date deadline =result.getDate("date_limite_soumission_articles");
            LocalDate today = LocalDate.now();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String deadlineString = sdf.format(deadline);

LocalDate deadlineDate = LocalDate.parse(deadlineString);
isOpen = today.isBefore(deadlineDate);}
if(!isOpen){showAlert("date of submission has been passed");}
else{
        
        // Prepare and execute query to insert participant
        query = "INSERT INTO article (conference_id,titre,fichier_pdf,etat,utilisateur_id) VALUES (?, ?, ?, ?,?)";
        prepare= connection.prepareStatement(query);
        prepare.setInt(1,confId);
        prepare.setString(2, titre);
        prepare.setString(3, filePath);
        prepare.setString(4,"NA");
        prepare.setInt(5,userId);


        prepare.executeUpdate();

        // Close resources
        prepare.close();
           showAlert("Article submitted successfully!");

        
    }}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_conference();
 // Populate the mode_paiment_combo
        List<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("Carte Bancaire");
        paymentMethods.add("Virement");
        paymentMethods.add("Paypal");
        paymentMethods.add("Chèque"); // Added Cheque
        paymentMethods.add("Virement Bancaire"); // Added Virement Bancaire
        paymentMethods.add("Bon de Commande d'une Institution"); // Added Bon de Commande d'une Institution
        paymentMethods.add("En Espèce sur Place"); // Added En Espèce sur Place
        // You can add more payment methods here

        mode_paiment_combo.setItems(FXCollections.observableList(paymentMethods));    }    
          
}
