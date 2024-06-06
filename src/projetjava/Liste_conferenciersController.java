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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class Liste_conferenciersController implements Initializable {

    @FXML
    private Button Back_conference_btn;

    public TableColumn<ConferencierInvite, Integer> conference_id;

    @FXML
    public TableColumn<ConferencierInvite, Integer> conferencier_id;

    @FXML
    public TableView<ConferencierInvite> confirenciersTableView;

    @FXML
    public TableColumn<ConferencierInvite, String> institution;

    @FXML
    public AnchorPane liste_invited_speakers_form;

    @FXML
    public TableColumn<ConferencierInvite, String> nom;

    @FXML
    public TableColumn<ConferencierInvite, String> origine;

    @FXML
    public TableColumn<ConferencierInvite, String> titre_presentation;

    // Tools for database
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private String query;
    private ConferencierInvite conferencier;
    private ObservableList<ConferencierInvite> ConferenciersList = FXCollections.observableArrayList();
    

   
    
    /*public void afficher_conferenciers() throws IOException {
            try (
        Connection connection = dbconnexion.getConnection()) { // Use try-with-resources
        query = "SELECT * FROM ConferencierInvite ";
        prepare = connection.prepareStatement(query);
        /*prepare.setInt(1, conferenceId);
        result = prepare.executeQuery();
        System.out.println("Executing query: " + query);



            
               

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
                connection.close();

            } catch (Exception e) {
                // Handle exception here (log error or display user message)
                e.printStackTrace();
            }
        

    // Set TableView cell value factories (assuming column names match)
    conference_id.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, Integer>("conferencierInviteId"));
    conferencier_id.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, Integer>("conferenceId"));
    nom.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("nom"));
    origine.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("paysOrigine"));
    institution.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("institution"));
    titre_presentation.setCellValueFactory(new PropertyValueFactory<ConferencierInvite, String>("titrePresentation"));

    confirenciersTableView.setItems(ConferenciersList);
}*/

       @FXML
    void go_back(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail_conference.fxml"));
    Parent root = loader.load();

    // Get the stage from the current scene
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
    stage.setScene(new Scene(root));
    stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
 }   

    
}
