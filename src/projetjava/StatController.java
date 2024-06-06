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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class StatController implements Initializable {

    @FXML
    private TextArea text_area;
@FXML
private BarChart<String, Number> barchart;
    @FXML
    private PieChart chart;

    @FXML
    private Button go_to;
      @FXML
    private TextField pourcentage;

    @FXML
    private TableColumn<String,String> titre_article;
         //tools for database 
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    String query;
    
    
    public void afficher() throws SQLException{
      connection = dbconnexion.getConnection();
        // Récupérer la liste des papiers acceptés (titres avec noms d'auteurs)
        String sqlAcceptedPapers = "SELECT titre, utilisateur.nom FROM article JOIN utilisateur ON article.utilisateur_id = utilisateur.utilisateur_id WHERE etat = 'ACC'";
        prepare = connection.prepareStatement(sqlAcceptedPapers);
        result = prepare.executeQuery();
         StringBuilder stringBuilder = new StringBuilder();
       while (result.next()) {

    stringBuilder.append("Title: ").append(result.getString("titre")).append(" - ");
    stringBuilder.append("Author: ").append(result.getString("nom")).append("\n");    }
    text_area.setText(stringBuilder.toString());
     // Récupérer le nombre total de papiers soumis
        String sqlTotalPapers = "SELECT COUNT(*) AS total FROM article";
        prepare = connection.prepareStatement(sqlTotalPapers);
        result = prepare.executeQuery();
        int totalPapers = 0;
        if (result.next()) {
            totalPapers = result.getInt("total");
        }
        System.out.println("Nombre total de papiers soumis: " + totalPapers);
        
        // Récupérer le nombre de papiers acceptés
        String sqlAcceptedCount = "SELECT COUNT(*) AS accepted FROM article WHERE etat = 'ACC'";
        prepare = connection.prepareStatement(sqlAcceptedCount);
        result = prepare.executeQuery();
        int acceptedPapers = 0;
        if (result.next()) {
            acceptedPapers = result.getInt("accepted");
        }
        System.out.println("Nombre de papiers acceptés: " + acceptedPapers);
        
        // Calculer le pourcentage de papiers acceptés par rapport aux soumis
        double acceptanceRate = (double) acceptedPapers / totalPapers * 100;
         String acceptanceRateString = String.format("%.2f", acceptanceRate); // Formater avec deux décimales
        pourcentage.setText(acceptanceRateString);
        // Créer les séries de données
    XYChart.Series<String, Number> totalSeries = new XYChart.Series<>();
    XYChart.Series<String, Number> acceptedSeries = new XYChart.Series<>();

    // Ajouter les données aux séries
    totalSeries.getData().add(new XYChart.Data<>("Total Papers", totalPapers));
    acceptedSeries.getData().add(new XYChart.Data<>("Accepted Papers", acceptedPapers));

    // Ajouter les séries au graphique à barres
    barchart.getData().addAll(totalSeries, acceptedSeries);
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              afficher();
          } catch (SQLException ex) {
              Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
}
