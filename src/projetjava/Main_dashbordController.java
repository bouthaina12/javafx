/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import java.sql.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author bouth
 */
public class Main_dashbordController implements  Initializable {

    @FXML
    private TableColumn<conference,String> enddate_conference;

    @FXML
    private TableColumn<conference,String> fees_conference;

    @FXML
    private TableColumn<conference,Integer> id_conference;

    @FXML
    private TableColumn<conference,String> institution_conference;

    @FXML
    private TableColumn<conference,Integer> president_conference;

    @FXML
    private TableColumn<conference,String> registrationddline_conference;
   @FXML
    private TableView<conference> conferenceTableView;  
      @FXML
    private TableColumn<conference,String> startdate_conference;

    @FXML
    private TableColumn<conference,String> submissionddline_conference;

    @FXML
    private TableColumn<conference,String> title_conference;
   
    @FXML
    private TableColumn<conference,String> location_conference;
    //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    conference conference;
    ObservableList<conference>ConferenceList=FXCollections.observableArrayList();
    
     @FXML
    private Button log_in_conference_btn;
    @FXML
    private Button statistic_btn;


      @FXML
    void stat(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
    Parent root = loader.load();
    StatController controller = loader.getController();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();
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

  /*  @FXML
    void search_conference(ActionEvent event) {

                 try{
             
              // Get the current search text
        String searchText = serach_conference.getText();

        // Clear the table if search text is empty
        if (searchText.isEmpty()) {
            conferenceTableView.setItems(ConferenceList);
            return;
        }

        // Filter the conference list based on search text
        ObservableList<conference> filteredList = FXCollections.observableArrayList();
        for (conference conf : ConferenceList) {
            if (conf.getNom().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(conf);
            }
        }

        // Set the filtered list to the table view
        conferenceTableView.setItems(filteredList);
    
             
             }catch(Exception e){
                 System.err.println("Error searching conferences: " + e.getMessage());
}
    }*/
    
private void loadDetailConference(conference selectedConference) {
    try {
        // Load Detail_conference.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail_conference.fxml"));
        Parent root = loader.load();
        Detail_conferenceController detailController = loader.getController();

        // Set conference details in Detail_conferenceController
     detailController.conf_id.setText(String.valueOf(selectedConference.getConference_id()));
     detailController.title.setText(selectedConference.getNom());
     detailController.end_date.setText(selectedConference.getDate_fin()); 
     detailController.fees.setText(selectedConference.getFrais_inscription()); 
     detailController.institution.setText(selectedConference.getInstitution_organisatrice()); 
     detailController.president_id.setText(String.valueOf(selectedConference.getUtilisateur_id())); 
     detailController.registration.setText(selectedConference.getDate_limite_inscriptions()); 
     detailController.start_date.setText(selectedConference.getDate_debut());
     detailController.submission.setText(selectedConference.getDate_limite_soumission_articles()); 


        // Switch scene to Detail_conference
        Stage stage = (Stage) conferenceTableView.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        // Handle FXML loading exception
        System.err.println("Error loading Detail_conference.fxml: " + e.getMessage());
        // You can display an error message to the user here (e.g., Alert dialog)
    } catch (Exception e) {
        // Handle other potential exceptions
        System.err.println("Unexpected error: " + e.getMessage());
        // You can display a generic error message to the user here
    }
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
  
    
    
   
    @FXML
    void log_in(ActionEvent event) throws IOException {
String buttonId = ((Button) event.getSource()).getId();
  if (buttonId.equals("log_in_conference_btn")) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
    Parent root = loader.load();
    ConnexionController controller = loader.getController();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();
    }}
    
@Override

public void initialize(URL url, ResourceBundle rb) {
    
  afficher_conference();

}

    

  
}
    

