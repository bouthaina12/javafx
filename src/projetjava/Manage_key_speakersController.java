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


public class Manage_key_speakersController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private TableColumn<ConferencierInvite,Integer> conf_id;

    

    @FXML
    private TextField conf_id_textbox;

    @FXML
    private TableView<ConferencierInvite> conferenciersTableView;

    @FXML
    private Button delete_btn;

    @FXML
    private TableColumn<ConferencierInvite,Integer> id_key_speaker;

    @FXML
    private TextField institut_textbox;

    @FXML
    private TableColumn<ConferencierInvite,String> institution;

    @FXML
    private Label log_in_conference_btn11;

    @FXML
    private AnchorPane magane_key_speakers_form;

    @FXML
    private Button main_screen_btn;

    @FXML
    private Button assign_btn;
    @FXML
    private Button modify_btn;

    @FXML
    private TableColumn<ConferencierInvite,String> name_key_speaker;

    @FXML
    private TextField name_textbox;

    @FXML
    private TableColumn<ConferencierInvite,String> origine;

    @FXML
    private TextField origine_textbox;

    @FXML
    private TableColumn<ConferencierInvite,String> titre_presentation;

    @FXML
    private TextField titre_textbox;
   //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    conference conference;
    ObservableList<ConferencierInvite>ConferencierList=FXCollections.observableArrayList();
    @FXML
    private Button create_conf_btn;
    @FXML
    private Button manage_key_btn;
    @FXML
    private Button manage_commitee_btn;
 
    @FXML
    private Button participants_btn;
    
    // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
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
    void go_to_create(ActionEvent event) throws IOException {
        // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create_conference.fxml"));
        Parent root = loader.load();
        Create_conferenceController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    
    
    
    }


    
    
  @FXML
    void go_to_assignpage(ActionEvent event) throws IOException {
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
    void add(ActionEvent event) throws SQLException {
       // Get data from TextFields
  String titre = titre_textbox.getText();
  String name = name_textbox.getText();
  String origine = origine_textbox.getText();

  String institution = institut_textbox.getText();
  Integer confid = Integer.valueOf(conf_id_textbox.getText()); 
  
  
    // Input validation (optional, add checks for empty fields or invalid formats)

  // Database connection and query
  connection = dbconnexion.getConnection();
  query = "INSERT INTO conferencierInvite(titre_presentation,institution,conference_id,nom,pays_origine) VALUES (?, ?, ?, ?, ?)";
  prepare = connection.prepareStatement(query);
  prepare.setString(1, titre);
  prepare.setString(2, institution);
  prepare.setInt(3,confid);

  prepare.setString(4, name);
  prepare.setString(5, origine);

  // Execute update and handle success/failure
  int rowsAffected = prepare.executeUpdate();
  if (rowsAffected > 0) {
  showAlert("key speaker added  successfully!");
      
  // Clear TextFields (optional)
    titre_textbox.clear();
    institut_textbox.clear();
    conf_id_textbox.clear(); 
    name_textbox.clear();
    origine_textbox.clear();
    
    
    // Refresh table view
    ConferencierList.clear();
    afficher(); // Call your existing method to fetch and display conferences
  } else {
    System.out.println("Error creating conference!");
  }

  connection.close();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
            // Get the selected conference
  ConferencierInvite selectedConferencier = conferenciersTableView.getSelectionModel().getSelectedItem();
  if (selectedConferencier != null) {

    // Delete conference from database
    connection = dbconnexion.getConnection();
    query = "DELETE FROM ConferencierInvite WHERE conferencier_invite_id = ?";
    prepare = connection.prepareStatement(query);
    prepare.setInt(1, selectedConferencier.getConferencierInviteId());
    int rowsAffected = prepare.executeUpdate();
    prepare.close();
    connection.close();

    // Update table view if deletion was successful
    if (rowsAffected > 0) {
      ConferencierList.remove(selectedConferencier); // Remove from ObservableList
      conferenciersTableView.refresh(); // Refresh table view
      System.out.println("Conference deleted successfully!");
      showAlert("key speakers deleted successfully!");

    } else {
      System.out.println("Error deleting key speaker!");
    }
  } else {
    System.out.println("Please select a key speaker to delete.");  
    showAlert("Please select a key speaker to delete.");

  }
    }

    @FXML
    void go_to(ActionEvent event) throws IOException {
          // Load main_screen.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

    @FXML
    void modify(ActionEvent event) throws SQLException {
                 // Get data from TextFields
  String titre = titre_textbox.getText();
  String name = name_textbox.getText();
  String origine = origine_textbox.getText();

  String institution = institut_textbox.getText();
  Integer confid = Integer.valueOf(conf_id_textbox.getText()); 


  // Get the selected conference (assuming it hasn't changed)
  ConferencierInvite selectedConferencier = conferenciersTableView.getSelectionModel().getSelectedItem();
  // Get the selected conference (assuming it hasn't changed)
// Update conference object with new data
  selectedConferencier.setNom(name);
  selectedConferencier.setConferenceId(confid);
  selectedConferencier.setPaysOrigine(origine);
  selectedConferencier.setTitrePresentation(titre);
  selectedConferencier.setInstitution(institution);


  // Database connection and query
  connection = dbconnexion.getConnection();
  query = "UPDATE   conferencierinvite SET nom = ?, institution= ?,pays_origine=?,titre_presentation=? conference_id=? WHERE conferencier_invite_id = ?";
  prepare = connection.prepareStatement(query);
  prepare.setString(1, name);
  prepare.setString(2, institution);
  prepare.setString(3, origine);
 
  prepare.setString(4,titre);
  prepare.setInt(5,confid);
  prepare.setInt(6, selectedConferencier.getConferencierInviteId());


  // Execute update and handle success/failure
  int rowsAffected = prepare.executeUpdate();
  if (rowsAffected > 0) {
  showAlert("key speaker modified successfully!");
      
  // Clear TextFields (optional)
    titre_textbox.clear();
    institut_textbox.clear();
    conf_id_textbox.clear(); 
    name_textbox.clear();
 
    // Refresh table view
    ConferencierList.clear();
    afficher(); // Call your existing method to fetch and display conferences
  } else {
    System.out.println("Error modifying key speaker!");
  }

  connection.close();
    }
  public void afficher(){
      
      try{
     connection=dbconnexion.getConnection();
     query="SELECT * FROM conferencierInvite";
     prepare=connection.prepareStatement(query);
     result=prepare.executeQuery();
     while(result.next()){
     ConferencierList.add(new ConferencierInvite(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6)));
     
     }
     connection.close();
     
     
     }catch(Exception e){
     e.printStackTrace();}
     
    conf_id.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,Integer>("conferenceId"));
    id_key_speaker.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,Integer>("conferencierInviteId"));

    name_key_speaker.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,String>("nom"));
    institution.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,String>("institution"));
    origine.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,String>("paysOrigine"));
    titre_presentation.setCellValueFactory(new PropertyValueFactory<ConferencierInvite,String>("titrePresentation"));
   
    conferenciersTableView.setItems(ConferencierList);

}
  
  
   
private void loadDetailConference(ConferencierInvite selectedConferencier) {
  // Set TextFields with data from the selected conference object

  titre_textbox.setText(selectedConferencier.getTitrePresentation());
  institut_textbox.setText(selectedConferencier.getInstitution());
  conf_id_textbox.setText(String.valueOf(selectedConferencier.getConferenceId())); 
  name_textbox.setText(selectedConferencier.getNom());
  origine.setText(selectedConferencier.getPaysOrigine());  
}

 @FXML
    void display_conferencier(MouseEvent event) {
if(event.getClickCount()>1){
        if(conferenciersTableView.getSelectionModel().getSelectedItem()!=null){
           ConferencierInvite selectedconferencier=conferenciersTableView.getSelectionModel().getSelectedItem();
           
        loadDetailConference(selectedconferencier);
        }}}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
    }    
    
}
