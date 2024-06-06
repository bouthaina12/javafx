/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ParticipantsController implements Initializable {

        @FXML
    private Button assign_btn;

    @FXML
    private TableColumn<participant,Integer> conf_id;

    @FXML
    private TextField conf_id_textbox;

    @FXML
    private Button create_conf_btn;

    @FXML
    private AnchorPane create_conference_form;

    @FXML
    private TableColumn<participant,String> email;

    @FXML
    private Button imprimer;

    @FXML
    private TableColumn<participant,String> institution;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button main_screen_btn;
    @FXML
    private Button search_btn;

   

    @FXML
    private TableColumn<participant,String> mode_paiment;

    @FXML
    private TableColumn<participant,String> nom;

    @FXML
    private TableView<participant> participantTableView;

    @FXML
    private TableColumn<participant,Integer> participant_id;

    @FXML
    private Button participants_btn;
 
     //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query2;

    ObservableList<participant>participantsList=FXCollections.observableArrayList();
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
    void go_to_conference(ActionEvent event) throws IOException {
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
    void imprimer(ActionEvent event) {
          // Nom du fichier de sortie
    String fileName = "participants_details.txt";

    // Créer une instance de FileWriter
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        // Écrire les détails des participants dans le fichier
        writer.write("Liste des Participants:\n");
        for (participant participant : participantsList) {
            writer.write("Inscription ID: " + participant.getInscription_id() + "\n");
            writer.write("Conference ID: " + participant.getConference_id() + "\n");
            writer.write("Mode de paiement: " + participant.getMode_paiement() + "\n");
            writer.write("Nom: " + participant.getNom() + "\n");
            writer.write("Email: " + participant.getAdresse_email() + "\n");
            writer.write("Institution: " + participant.getInstitution() + "\n");
            writer.write("\n"); // Ligne vide pour séparer les participants
        }

        // Afficher une alerte pour indiquer que l'impression est terminée
        showAlert("Les détails des participants ont été imprimés dans " + fileName);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
        @FXML
    void search(ActionEvent event) throws SQLException {
     // Get the conference ID from the text box
  Integer confId = Integer.parseInt(conf_id_textbox.getText());

  // Clear any existing participants data
  participantsList.clear();

  // Create the SQL query to retrieve participants
  String query2 ="SELECT cs.inscription_id, cs.utilisateur_id, cs.mode_paiement, " +
                "u.nom, u.adresse_email, u.institution, u.username, u.pswrd, u.est_administrateur " +
                "FROM inscriptionparticipant cs " +
                "INNER JOIN utilisateur u ON cs.utilisateur_id = u.utilisateur_id " +
                "WHERE cs.conference_id = ?";

  connection = dbconnexion.getConnection(); // Assuming dbconnexion provides a connection
  prepare = connection.prepareStatement(query2);
  prepare.setInt(1, confId);

  result = prepare.executeQuery();

  // Process the result set and add participants to the list
  while (result.next()) {
    participant p = new participant(
        result.getInt("inscription_id"),
        confId,
        result.getString("mode_paiement"),
        result.getInt("utilisateur_id"),
        result.getString("nom"),
        result.getString("adresse_email"),
        result.getString("institution"),
        result.getString("username"),
        result.getString("pswrd"),
        result.getInt("est_administrateur")

    );
 
        
        participant_id.setCellValueFactory(new PropertyValueFactory<participant,Integer>("inscription_id"));
        conf_id.setCellValueFactory(new PropertyValueFactory<participant,Integer>("conference_id"));
        mode_paiment.setCellValueFactory(new PropertyValueFactory<participant,String>("mode_paiement"));
        nom.setCellValueFactory(new PropertyValueFactory<participant,String>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<participant,String>("adresse_email"));
        institution.setCellValueFactory(new PropertyValueFactory<participant,String>("institution"));



      

        
        participantsList.add(p);
    }

    // Set items for ComiteScientifique TableView
    participantTableView.setItems(participantsList);

    // Close connection
    connection.close();
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAlert("enter conference id to see the participants");
        
        
    }    
    
}
