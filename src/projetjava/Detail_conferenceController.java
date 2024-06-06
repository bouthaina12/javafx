/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class Detail_conferenceController implements Initializable {

    @FXML
    public TextField conf_id;

    @FXML
    private AnchorPane conference_details_form;

    @FXML
    public TextField end_date;

    @FXML
    public TextField fees;

    @FXML
    public Button imprimer_conference_btn;

    @FXML
    public TextField institution;

    @FXML
    public Button list_invited_speakers_btn;

    @FXML
    public TextField president_id;

    @FXML
    public TextField registration;

    @FXML
    public TextField start_date;

    @FXML
    public TextField submission;

    @FXML
    public TextField title;
    @FXML
    private Button back_btn;
    
    
    
  
 
    // Tools for database
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private String query;
    private ConferencierInvite conferencier;
    private ObservableList<ConferencierInvite> ConferenciersList = FXCollections.observableArrayList();
    private Integer conferenceId;
  // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
private void go_back(ActionEvent event) throws IOException {
  String buttonId = ((Button) event.getSource()).getId();
  if (buttonId.equals("back_btn")) {
    // Load Main_dashbord.fxml
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
    Parent root = loader.load();
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();  } else if (buttonId.equals("list_invited_speakers_btn")) {
    // Load liste_conferenciers.fxml
      FXMLLoader loader = new FXMLLoader(getClass().getResource("liste_conferenciers.fxml"));
      Parent root = loader.load();
      Liste_conferenciersController ListeController = loader.getController();
 // ...
  String conferenceIdStr = conf_id.getText();
  try {
    conferenceId = Integer.parseInt(conferenceIdStr); // Parse to integer
  } catch (NumberFormatException e) {
    // Handle invalid input in conf_id (e.g., display an error message)
    System.out.println("Invalid conference ID format.");
  }
      try (Connection connection = dbconnexion.getConnection()) {
        // Filter data by conferenceId (approach b)
        query = "SELECT * FROM ConferencierInvite WHERE conference_id = ?";
        prepare = connection.prepareStatement(query);
        prepare.setInt(1, conferenceId); // Assuming conferenceId is populated

        result = prepare.executeQuery();
        System.out.println("Executing query: " + query);

        ConferenciersList.clear(); // Clear existing data before adding new results
        while (result.next()) {
          ConferenciersList.add(new ConferencierInvite(
              result.getInt(1),
              result.getInt(2),
              result.getString(3),
              result.getString(4),
              result.getString(5),
              result.getString(6)
          ));
        }
      } catch (SQLException ex) {
      }

      // Set TableView cell value factories (assuming column names match)
      ListeController.conference_id.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, Integer>("conferenceId"));
      ListeController.conferencier_id.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, Integer>("conferencierInviteId"));
      ListeController.nom.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("nom"));
      ListeController.origine.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("paysOrigine"));
      ListeController.institution.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("institution"));
      ListeController.titre_presentation.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("titrePresentation"));

      ListeController.confirenciersTableView.setItems(ConferenciersList);

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Set the scene on the stage and show it
      stage.setScene(new Scene(root));
      stage.show();
    } else {
      // Handle clicks on other buttons (optional)
      System.out.println("Unidentified button clicked: " + buttonId);
    }
  }
    
    
    
  /*  @FXML
     void go_back(ActionEvent) throws IOException {
 // Load Main_dashbord.fxml
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
    Parent root = loader.load();

    // Get the stage from the current scene
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();}

    
    
        

    @FXML
    private void go_to_list_speakers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("liste_conferenciers.fxml"));
       Parent root = loader.load();

    // Get the stage from the current scene
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();
    
    
    
    
    }*/

// ... (imprimer function code)

// Separate file writing method
public void writeConferenceDetailsToFile(String conferenceDetails,String invitedSpeakers) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("conference_details.txt"))) {
        writer.write(conferenceDetails);
        writer.write("\n\n");
        writer.write(invitedSpeakers);
        writer.write("\n\n");
        writer.flush();
        showAlert("Conference details printed to conference_details.txt");
        System.out.println(new File("conference_details.txt").getAbsolutePath());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   @FXML
    void imprimer(ActionEvent event) {
        // Get the current conference details
        String conferenceDetails = "Title Of The Conference: " + title.getText() + "\n" +
                                   "Start Date: " + start_date.getText() + "\n" +
                                   "End Date: " + end_date.getText() + "\n" +
                                   "Registration Fees: " + fees.getText() + "\n" +
                                   "Registration deadline: " + registration.getText() + "\n" +
                                   "Submission deadline: " + submission.getText() + "\n" +
                                   "Organizing Institution : " + institution.getText() + "\n" +
                                   "President ID: " + president_id.getText() + "\n";
System.out.println("Number of speakers: " + ConferenciersList.size());

        // Get the list of invited speakers
        StringBuilder invitedSpeakers = new StringBuilder();
        for (ConferencierInvite conferencier : ConferenciersList) {
            invitedSpeakers.append("Name: ").append(conferencier.getNom()).append(", \n");
            invitedSpeakers.append("Origin: ").append(conferencier.getPaysOrigine()).append(", \n");
            invitedSpeakers.append("Institution: ").append(conferencier.getInstitution()).append(", \n");
            invitedSpeakers.append("Presentation Title: ").append(conferencier.getTitrePresentation()).append("\n");
        }
                System.out.println(invitedSpeakers.toString());


         writeConferenceDetailsToFile(conferenceDetails,invitedSpeakers.toString());



    }
        @Override

    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
