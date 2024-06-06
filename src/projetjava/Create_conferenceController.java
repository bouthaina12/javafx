/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class Create_conferenceController implements Initializable {

    @FXML
    private Button add_conf_btn;
    @FXML
    private Button assign_btn;

    @FXML
    private TableView<conference> conferenceTableView;

    @FXML
    private AnchorPane create_conference_form;

    @FXML
    private Button delete_conf_btn;
   @FXML
    private Button participants_btn;
    @FXML
    private TextField end_date_textbox;

    @FXML
    private TableColumn<conference,String> enddate_conference;

    @FXML
    private TableColumn<conference,String> fees_conference;

    @FXML
    private TextField fees_textbox;

    @FXML
    private TableColumn<conference,Integer> id_conference;

    @FXML
    private TextField institution_conf_textbox;

    @FXML
    private TableColumn<conference,String> institution_conference;

    @FXML
    private TableColumn<conference,String> location_conference;

    @FXML
    private Button log_out_btn;

    
    @FXML
    private TextField location_textbox;

    @FXML
    private Button modify_conf_btn;

    @FXML
    private TableColumn<conference,Integer> president_conference;

    @FXML
    private TextField registration_textbox;

    @FXML
    private TableColumn<conference,String> registrationddline_conference;

    @FXML
    private TextField start_date_textbox;
    @FXML
    private Button main_screen_btn;
    @FXML
    private Button manage_key_speaker_btn;

    @FXML
    private TableColumn<conference,String> startdate_conference;

    @FXML
    private TextField submission_textbox;

    @FXML
    private TableColumn<conference,String> submissionddline_conference;

    @FXML
    private TextField title_conf_textbox;
     @FXML
    private TextField president_id_textbox;

    @FXML
    private TableColumn<conference,String> title_conference;
   //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    conference conference;
    ObservableList<conference>ConferenceList=FXCollections.observableArrayList();
    
    
    
      // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    
     @FXML
    void go_to(ActionEvent event) throws IOException {
        String buttonId = ((Button) event.getSource()).getId();
      if (buttonId.equals("main_screen_btn")) {
         // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }}
  @FXML
    void go_to_participants(ActionEvent event) throws IOException {
 // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("participants.fxml"));
        Parent root = loader.load();
        ParticipantsController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    
    }

    @FXML
    void go_to_manage_speakers(ActionEvent event) throws IOException {
        // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manage_key_speakers.fxml"));
        Parent root = loader.load();
        Manage_key_speakersController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    
    
    
    }

    @FXML
    void go_to_assign(ActionEvent event) throws IOException {
        // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assign_papers.fxml"));
        Parent root = loader.load();
        Assign_papersController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    
    
    
    } 
    
 @FXML
    void delete_conference(ActionEvent event) throws SQLException {
// Get the selected conference
  conference selectedConference = conferenceTableView.getSelectionModel().getSelectedItem();
  if (selectedConference != null) {

    // Delete conference from database
    connection = dbconnexion.getConnection();
    query = "DELETE FROM conference WHERE conference_id = ?";
    prepare = connection.prepareStatement(query);
    prepare.setInt(1, selectedConference.getConference_id());
    int rowsAffected = prepare.executeUpdate();
    prepare.close();
    connection.close();

    // Update table view if deletion was successful
    if (rowsAffected > 0) {
      ConferenceList.remove(selectedConference); // Remove from ObservableList
      conferenceTableView.refresh(); // Refresh table view
      System.out.println("Conference deleted successfully!");
      showAlert("Conference deleted successfully!");

    } else {
      System.out.println("Error deleting conference!");
    }
  } else {
    System.out.println("Please select a conference to delete.");  
    showAlert("Please select a conference to delete.");

  }
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

}
   
 @FXML
    void modify_confrence(ActionEvent event) throws SQLException {
      // Get data from TextFields
  String title = title_conf_textbox.getText();
  String institution = institution_conf_textbox.getText();
  Integer presidentId = Integer.valueOf(president_id_textbox.getText()); 
  // Convert date strings to appropriate format
  LocalDate startDate = LocalDate.parse(start_date_textbox.getText()); // Assuming format is YYYY-MM-DD
  LocalDate endDate = LocalDate.parse(end_date_textbox.getText());
  LocalDate submissionDeadline = LocalDate.parse(submission_textbox.getText());
  LocalDate registrationDeadline = LocalDate.parse(registration_textbox.getText());
  String fees = fees_textbox.getText();
  String location =location_textbox.getText(); // Add logic to get location if needed

  // Get the selected conference (assuming it hasn't changed)
  conference selectedConference = conferenceTableView.getSelectionModel().getSelectedItem();
  // Get the selected conference (assuming it hasn't changed)
// Update conference object with new data
  selectedConference.setNom(title);
  selectedConference.setInstitution_organisatrice(institution);
  selectedConference.setUtilisateur_id(presidentId);
  selectedConference.setDate_debut(String.valueOf(startDate));
  selectedConference.setDate_fin(String.valueOf(endDate));
  selectedConference.setDate_limite_soumission_articles(String.valueOf(submissionDeadline));
  selectedConference.setDate_limite_inscriptions(String.valueOf(registrationDeadline));
  selectedConference.setFrais_inscription(fees);
  selectedConference.setLieu(location);


  // Database connection and query
  connection = dbconnexion.getConnection();
  query = "UPDATE conference SET nom = ?, institution_organisatrice = ?, utilisateur_id = ?, date_debut = ?, date_fin = ?, date_limite_soumission_articles = ?, date_limite_inscriptions = ?, frais_inscription = ?, lieu = ? WHERE conference_id = ?";
  prepare = connection.prepareStatement(query);
  prepare.setString(1, title);
  prepare.setString(2, institution);
  prepare.setInt(3, presidentId);
  prepare.setDate(4, java.sql.Date.valueOf(startDate)); // Convert LocalDate to java.sql.Date
  prepare.setDate(5, java.sql.Date.valueOf(endDate));
  prepare.setDate(6, java.sql.Date.valueOf(submissionDeadline));
  prepare.setDate(7, java.sql.Date.valueOf(registrationDeadline));
  prepare.setString(8, fees);
  prepare.setString(9, location);
      prepare.setInt(10, selectedConference.getConference_id());


  // Execute update and handle success/failure
  int rowsAffected = prepare.executeUpdate();
  if (rowsAffected > 0) {
  showAlert("Conference modified successfully!");
      
  // Clear TextFields (optional)
    title_conf_textbox.clear();
    institution_conf_textbox.clear();
    president_id_textbox.clear(); 
    start_date_textbox.clear();
    end_date_textbox.clear();
    submission_textbox.clear();
    registration_textbox.clear();
    fees_textbox.clear();
    location_textbox.clear(); 
    
    // Refresh table view
    ConferenceList.clear();
    afficher_conference(); // Call your existing method to fetch and display conferences
  } else {
    System.out.println("Error modifying conference!");
  }

  connection.close();
    
        

        
    }     
@FXML
private void create_conference(ActionEvent event) throws IOException, SQLException {
  // Get data from TextFields
  String title = title_conf_textbox.getText();
  String institution = institution_conf_textbox.getText();
  Integer presidentId = Integer.valueOf(president_id_textbox.getText()); 
  // Convert date strings to appropriate format
  LocalDate startDate = LocalDate.parse(start_date_textbox.getText()); // Assuming format is YYYY-MM-DD
  LocalDate endDate = LocalDate.parse(end_date_textbox.getText());
  LocalDate submissionDeadline = LocalDate.parse(submission_textbox.getText());
  LocalDate registrationDeadline = LocalDate.parse(registration_textbox.getText());
  String fees = fees_textbox.getText();
  String location =location_textbox.getText(); 
  
    // Input validation (optional, add checks for empty fields or invalid formats)

  // Database connection and query
  connection = dbconnexion.getConnection();
  query = "INSERT INTO conference (nom, institution_organisatrice, utilisateur_id, date_debut, date_fin, date_limite_soumission_articles, date_limite_inscriptions, frais_inscription, lieu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  prepare = connection.prepareStatement(query);
  prepare.setString(1, title);
  prepare.setString(2, institution);
  prepare.setInt(3, presidentId);
  prepare.setDate(4, java.sql.Date.valueOf(startDate)); // Convert LocalDate to java.sql.Date
  prepare.setDate(5, java.sql.Date.valueOf(endDate));
  prepare.setDate(6, java.sql.Date.valueOf(submissionDeadline));
  prepare.setDate(7, java.sql.Date.valueOf(registrationDeadline));
  prepare.setString(8, fees);
  prepare.setString(9, location);

  // Execute update and handle success/failure
  int rowsAffected = prepare.executeUpdate();
  if (rowsAffected > 0) {
  showAlert("Conference created successfully!");
      
  // Clear TextFields (optional)
    title_conf_textbox.clear();
    institution_conf_textbox.clear();
    president_id_textbox.clear(); 
    start_date_textbox.clear();
    end_date_textbox.clear();
    submission_textbox.clear();
    registration_textbox.clear();
    fees_textbox.clear();
    location_textbox.clear(); 
    
    // Refresh table view
    ConferenceList.clear();
    afficher_conference(); // Call your existing method to fetch and display conferences
  } else {
    System.out.println("Error creating conference!");
  }

  connection.close();
}
    

    
private void loadDetailConference(conference selectedConference) {
  // Set TextFields with data from the selected conference object

  title_conf_textbox.setText(selectedConference.getNom());
  institution_conf_textbox.setText(selectedConference.getInstitution_organisatrice());
  president_id_textbox.setText(String.valueOf(selectedConference.getUtilisateur_id()));  // Convert int to String
  start_date_textbox.setText(selectedConference.getDate_debut().toString()); // Assuming date stored as String
  end_date_textbox.setText(selectedConference.getDate_fin().toString());  // Assuming date stored as String
  submission_textbox.setText(selectedConference.getDate_limite_soumission_articles().toString());  // Assuming date stored as String
  registration_textbox.setText(selectedConference.getDate_limite_inscriptions().toString());  // Assuming date stored as String
  fees_textbox.setText(selectedConference.getFrais_inscription());
  location_textbox.setText(selectedConference.getLieu());
}

  @FXML
    private void display_conference(MouseEvent event) {
        if(event.getClickCount()>1){
        if(conferenceTableView.getSelectionModel().getSelectedItem()!=null){
           conference selectedconference=conferenceTableView.getSelectionModel().getSelectedItem();
           
        loadDetailConference(selectedconference);
        }
        }
        
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_conference();
    }    
    
}
