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
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class Comite_dashController implements Initializable {
  @FXML
private ComboBox<String> combobox;
    
    @FXML
    private TextField etat_textbox;
    @FXML
    private TableView<Article> articleTableView;
    @FXML
    private TextField email;
    @FXML
    private TextField article_id_textbox;

    @FXML
    private TableColumn<Article,Integer> comit_id;

    @FXML
    private AnchorPane create_conference_form;

    @FXML
    private TableColumn<Article,Integer> id_article;

    @FXML
    private TableColumn<Article,Integer> id_conf;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button main_screen_btn;

    @FXML
    private TableColumn<Article,String> pdf_file;

    @FXML
    private TableColumn<Article,String> statu;

    @FXML
    private Button submit_btn;

    @FXML
    private TableColumn<Article,String> title;
     //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    

    ObservableList<Article>ArticleList=FXCollections.observableArrayList();
    // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
   
    @FXML
    void submit(ActionEvent event) throws SQLException {
         // Récupérer l'ID de l'article à partir de la zone de texte
         int articleId = Integer.parseInt(article_id_textbox.getText());

    // Récupérer la valeur sélectionnée dans le ComboBox
        String newStatus = etat_textbox.getText();
       // Mettre à jour l'état de l'article dans la base de données
    
        // Connexion à la base de données
        connection = dbconnexion.getConnection();

        // Préparer la requête SQL pour mettre à jour l'état de l'article
        String sqlUpdate = "UPDATE article SET etat=? WHERE article_id=?";
        prepare = connection.prepareStatement(sqlUpdate);
        prepare.setString(1, newStatus);
        prepare.setInt(2, articleId);

        // Exécuter la requête de mise à jour
        int rowsAffected = prepare.executeUpdate();

        if (rowsAffected > 0) {
            // Afficher une confirmation si la mise à jour est réussie
            showAlert("updated successfuly.");
            ArticleList.clear();
            connection = dbconnexion.getConnection();
         String sqlUser = "SELECT utilisateur_id FROM utilisateur WHERE adresse_email=?";
    
        prepare = connection.prepareStatement(sqlUser);
        prepare.setString(1, email.getText());
        result = prepare.executeQuery();
        
        if (result.next()) {
            int utilisateurId = result.getInt("utilisateur_id");
    
        String sqlCommittee = "SELECT comite_scientifique_id FROM comitescientifique WHERE utilisateur_id=?";
                prepare = connection.prepareStatement(sqlCommittee);
                prepare.setInt(1, utilisateurId);
                result = prepare.executeQuery();
                
                if (result.next()) {
                  int comite_id = result.getInt("comite_scientifique_id");
                   System.out.println("id: " +comite_id);
     query="SELECT * FROM article WHERE comite_scientifique_id=? ";
     prepare=connection.prepareStatement(query);
     prepare.setInt(1, comite_id);

     result=prepare.executeQuery();
     while(result.next()){
     ArticleList.add(new Article(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getInt(6)));

     }
     
     id_conf.setCellValueFactory(new PropertyValueFactory<Article,Integer>("conferenceId"));
     id_article.setCellValueFactory(new PropertyValueFactory<Article,Integer>("articleId"));

    title.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
    pdf_file.setCellValueFactory(new PropertyValueFactory<Article,String>("fichierPdf"));
    statu.setCellValueFactory(new PropertyValueFactory<Article,String>("etat"));
    comit_id.setCellValueFactory(new PropertyValueFactory<Article,Integer>("comiteScientifiqueId"));



      articleTableView.setItems(ArticleList);

            
        } else {
            // Afficher un message d'erreur si la mise à jour a échoué
            showAlert("La mise à jour de l'état de l'article a échoué.");
        }

    
    }}}
       @FXML
    void search(ActionEvent event) throws SQLException {
         connection = dbconnexion.getConnection();
         String sqlUser = "SELECT utilisateur_id FROM utilisateur WHERE adresse_email=?";
    
        prepare = connection.prepareStatement(sqlUser);
        prepare.setString(1, email.getText());
        result = prepare.executeQuery();
        
        if (result.next()) {
            int utilisateurId = result.getInt("utilisateur_id");
    
        String sqlCommittee = "SELECT comite_scientifique_id FROM comitescientifique WHERE utilisateur_id=?";
                prepare = connection.prepareStatement(sqlCommittee);
                prepare.setInt(1, utilisateurId);
                result = prepare.executeQuery();
                
                if (result.next()) {
                  int comite_id = result.getInt("comite_scientifique_id");
                   System.out.println("id: " +comite_id);
     query="SELECT * FROM article WHERE comite_scientifique_id=? ";
     prepare=connection.prepareStatement(query);
     prepare.setInt(1, comite_id);

     result=prepare.executeQuery();
     while(result.next()){
     ArticleList.add(new Article(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getInt(6)));

     }
     
     id_conf.setCellValueFactory(new PropertyValueFactory<Article,Integer>("conferenceId"));
     id_article.setCellValueFactory(new PropertyValueFactory<Article,Integer>("articleId"));

    title.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
    pdf_file.setCellValueFactory(new PropertyValueFactory<Article,String>("fichierPdf"));
    statu.setCellValueFactory(new PropertyValueFactory<Article,String>("etat"));
    comit_id.setCellValueFactory(new PropertyValueFactory<Article,Integer>("comiteScientifiqueId"));



      articleTableView.setItems(ArticleList);

                   
    }}}
       @FXML
    void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
     
    }    
    
}
