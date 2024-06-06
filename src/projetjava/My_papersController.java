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
public class My_papersController implements Initializable {
    @FXML
    private TableView<Article> articleTableView;

    @FXML
    private TableColumn<Article,Integer> comit_id;

    @FXML
    private TableColumn<Article,Integer> id_article;

    @FXML
    private TableColumn<Article,Integer> id_conf;

    @FXML
    private Button inscription_btn;

    @FXML
    private AnchorPane inscription_form;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button main_screen_btn;

    @FXML
    private Button my_papers_btn;

    @FXML
    private TableColumn<Article,String> pdf_file;

    @FXML
    private Button soumission_btn;

    @FXML
    private Button statistique_btn;

    @FXML
    private TableColumn<Article,String> statu;

    @FXML
    private TableColumn<Article,String> title;

     //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    String query2;

    ObservableList<Article>ArticleList=FXCollections.observableArrayList();
     @FXML
    void go_to(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(" Main_dashbord.fxml"));
        Parent root = loader.load();
        Main_dashbordController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }

    @FXML
    void go_to_inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(" inscription.fxml"));
        Parent root = loader.load();
        InscriptionController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage and show it
       stage.setScene(new Scene(root));
       stage.show();
    }
   private void afficher() throws IOException, SQLException{
   
     // Load liste_conferenciers.fxml
      FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
      Parent root = loader.load();
      InscriptionController ListeController = loader.getController();
      Integer userId=ListeController.userId;
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
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (IOException ex) {
        } catch (SQLException ex) {
        }
 }    
    
}
