/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;

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
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class Assign_papersController implements Initializable {

    
  @FXML
    private Button add_btn;
   @FXML
    private Button assignpage_btn;

    @FXML
    private TableView<Article> articleTableView;

    @FXML
    private TextField article_id_textbox;

    @FXML
    private Button assign_btn;

    @FXML
    private AnchorPane assignement_papers_form;

    @FXML
    private TableColumn<Article,Integer> comit_id;

    @FXML
    private TextField comit_id_textbox;

    @FXML
    private TableView<ComiteScientifique> comiteTableView;

    @FXML
    private TableColumn<ComiteScientifique,Integer> conf_id;
      @FXML
    private TableColumn<Article,Integer> id_conf;

    @FXML
    private TextField conf_id_textbox;

    @FXML
    private Button create_conf_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private TableColumn<ComiteScientifique,String> email;

    @FXML
    private TableColumn<Article,Integer> id_article;

    @FXML
    private TableColumn<ComiteScientifique,Integer> id_comit;

    @FXML
    private TableColumn<ComiteScientifique,String> institut;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button main_screen_btn;

    @FXML
    private Button manage_commitee_btn;

    @FXML
    private Button manage_key_btn;

    @FXML
    private Button modify_btn;

    @FXML
    private TableColumn<ComiteScientifique,String> name;

    @FXML
    private Button participants_btn;

    @FXML
    private TableColumn<Article,String> pdf_file;

    @FXML
    private TableColumn<Article,String> statu;

    @FXML
    private TableColumn<Article,String> title;

    @FXML
    private TableColumn<ComiteScientifique,Integer> user_id;

    @FXML
    private TextField user_id_textbox;

    
    
     //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    String query2;

    ObservableList<ComiteScientifique>ComitList=FXCollections.observableArrayList();
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
    void go_to(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

    @FXML
    void go_to_create_conf(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create_conference.fxml"));
        Parent root = loader.load();
        Create_conferenceController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

    @FXML
    void go_to_manage_key(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Manage_key_speakers.fxml"));
        Parent root = loader.load();
        Manage_key_speakersController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

    @FXML
    void go_to_participants(ActionEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("participants.fxml"));
        Parent root = loader.load();
        ParticipantsController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

  
    

  
    
  public void afficher() throws SQLException{
      
      try{
     connection=dbconnexion.getConnection();
     query="SELECT * FROM article ";
     prepare=connection.prepareStatement(query);
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

     
    // Execute the SQL query
query2 = "SELECT cs.comite_scientifique_id, cs.conference_id, cs.utilisateur_id, u.nom, u.adresse_email, u.institution, u.username, u.pswrd, u.est_administrateur " +
         "FROM comitescientifique cs " +
         "INNER JOIN utilisateur u ON cs.utilisateur_id = u.utilisateur_id";
prepare = connection.prepareStatement(query2);
result = prepare.executeQuery();

// Process the result set
while (result.next()) {
    ComiteScientifique comiteScientifique = new ComiteScientifique(
        result.getInt("comite_scientifique_id"),
        result.getInt("conference_id"),
        result.getInt("utilisateur_id"),
        result.getString("nom"),
        result.getString("adresse_email"),
        result.getString("institution"),
        result.getString("username"),
        result.getString("pswrd"),
        result.getInt("est_administrateur")
    );
        
        conf_id.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,Integer>("conferenceId"));
        id_comit.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,Integer>("comiteScientifiqueId"));
        user_id.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,Integer>("utilisateur_id"));
        name.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,String>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,String>("adresse_email"));
        institut.setCellValueFactory(new PropertyValueFactory<ComiteScientifique,String>("institution"));



      

        
        ComitList.add(comiteScientifique);
    }

    // Set items for ComiteScientifique TableView
    comiteTableView.setItems(ComitList);

    // Close connection
    connection.close();

}catch(Exception e){
     e.printStackTrace();}
  }
  
  
   @FXML
    void assign(ActionEvent event) {
           // Récupérer les valeurs des zones de texte article_id et comit_id
    Integer articleId = Integer.parseInt(article_id_textbox.getText());
    Integer comiteId = Integer.parseInt(comit_id_textbox.getText());

    
    connection=dbconnexion.getConnection();


    try {
       
       

      // Prepare the statement for the select query
query = "SELECT etat FROM article WHERE article_id = ?";
prepare = connection.prepareStatement(query);
prepare.setInt(1, articleId);

// Execute the select query
result = prepare.executeQuery();

// Check the state of the article
if (result.next()) {
    String etat = result.getString("etat");
    if (!etat.equals("NA")) {
        System.out.println("L'article est déjà affecté.");
        showAlert("L'article est déjà affecté.");
        return;
    }
}

// Prepare the statement for the update query
query2 = "UPDATE article SET etat = 'UE', comite_scientifique_id = ? WHERE article_id = ?";
prepare = connection.prepareStatement(query2);
prepare.setInt(1, comiteId);
prepare.setInt(2, articleId);

// Execute the update query
int rowsAffected = prepare.executeUpdate();
        if (rowsAffected > 0) {
            // Si la mise à jour est réussie, afficher un message de succès
            System.out.println("L'article a été affecté avec succès.");
            showAlert("L'article a été affecté avec succès.");
              article_id_textbox.clear();
              comit_id_textbox.clear();
// Refresh table view
    ArticleList.clear();
    ComitList.clear();

    afficher();
             
 
        } else {
            // Si la mise à jour a échoué, afficher un message d'erreur
            System.out.println("La mise à jour de l'article a échoué.");
            showAlert("La mise à jour de l'article a échoué.");

        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Fermer les ressources JDBC
        try {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
    
    
   @FXML
    void add(ActionEvent event) {
           try {
        // Récupérer les valeurs des zones de texte conf_id_textbox et user_id_textbox
        int conferenceId = Integer.parseInt(conf_id_textbox.getText());
        int utilisateurId = Integer.parseInt(user_id_textbox.getText());

        // Obtenir la connexion à la base de données
        connection = dbconnexion.getConnection();

        // Insérer les données dans la table comitescientifique
        String insertQuery = "INSERT INTO comitescientifique (conference_id, utilisateur_id) VALUES (?, ?)";
        prepare= connection.prepareStatement(insertQuery);
        prepare.setInt(1, conferenceId);
        prepare.setInt(2, utilisateurId);
        prepare.executeUpdate();

        // Exécuter la requête SQL pour récupérer les informations sur le membre du comité
        String selectQuery = "SELECT cs.comite_scientifique_id, cs.utilisateur_id, u.nom, u.adresse_email, u.institution, u.username, u.pswrd, u.est_administrateur " +
                             "FROM comitescientifique cs " +
                             "INNER JOIN utilisateur u ON cs.utilisateur_id = u.utilisateur_id " +
                             "WHERE cs.utilisateur_id = ?";
        prepare= connection.prepareStatement(selectQuery);
        prepare.setInt(1, utilisateurId);
        result = prepare.executeQuery();

        // Afficher toutes les informations dans la TableView
        while (result.next()) {
            ComiteScientifique comiteScientifique = new ComiteScientifique(
                result.getInt("comite_scientifique_id"),
                conferenceId,
                utilisateurId,
                result.getString("nom"),
                result.getString("adresse_email"),
                result.getString("institution"),
                result.getString("username"),
                result.getString("pswrd"),
                result.getInt("est_administrateur")
            );

            // Ajouter l'objet ComiteScientifique à la liste
            ComitList.add(comiteScientifique);
            showAlert("Membre du comité ajouté avec succès.");
        }

        // Mettre à jour la TableView
        comiteTableView.setItems(ComitList);

        // Nettoyer les zones de texte
        conf_id_textbox.clear();
        user_id_textbox.clear();

        // Rafraîchir la table view
        ArticleList.clear();
        ComitList.clear();
        afficher();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            // Fermer la connexion à la base de données
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
      try {
          afficher();
      } catch (SQLException ex) {
      }
      }}












