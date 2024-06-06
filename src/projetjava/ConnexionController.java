/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bouth
 */
public class ConnexionController implements Initializable {

   @FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;
//tools for database 
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
   

    @FXML
    private Button login_btn;

    @FXML
    private Button login_btn1;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

    @FXML
    private TextField username11;

    @FXML
    private TextField username12;

    @FXML
    private TextField username13;
@FXML
   private ImageView image2;
@FXML
    private ImageView image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public int comite_id;

   @FXML
    private void changeForm(ActionEvent event) {
        if(event.getSource()==create_acc){
        login_form.setVisible(false);
        signup_form.setVisible(true);
    }else if(event.getSource()==login_acc){
        login_form.setVisible(true);
        signup_form.setVisible(false);}
        }
    
    
        // Méthode de validation des champs de formulaire
    private boolean validateFields(TextField a ,TextField b) {
        if (a.getText().isEmpty() || b.getText().isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return false;
        }
        // Vous pouvez ajouter d'autres validations ici selon vos besoins
        return true;
    }

    // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   public void login() throws IOException {
    connect = dbconnexion.getConnection();
    String sqlUser = "SELECT utilisateur_id, est_administrateur FROM utilisateur WHERE username=? AND pswrd=?";
    
    try {
        prepare = connect.prepareStatement(sqlUser);
        prepare.setString(1, username.getText());
        prepare.setString(2, password.getText());
        result = prepare.executeQuery();
        
        if (result.next()) {
            int utilisateurId = result.getInt("utilisateur_id");
            int isAdmin = result.getInt("est_administrateur");
            
            // Vérifier si l'utilisateur est membre du comité scientifique
            if (isAdmin == 1) {
                // Si l'utilisateur est administrateur, charger create_conference.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("create_conference.fxml"));
                Parent root = loader.load();
                Create_conferenceController controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                // Si l'utilisateur n'est pas administrateur, vérifier s'il est membre du comité scientifique
                String sqlCommittee = "SELECT comite_scientifique_id FROM comitescientifique WHERE utilisateur_id=?";
                prepare = connect.prepareStatement(sqlCommittee);
                prepare.setInt(1, utilisateurId);
                result = prepare.executeQuery();
                
                if (result.next()) {
                   comite_id = result.getInt("comite_scientifique_id");
                   System.out.println("id: " +comite_id);

                    // Si l'utilisateur est membre du comité scientifique, charger comite_dash.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("comite_dash.fxml"));
                    Parent root = loader.load();
                    Comite_dashController controller = loader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                    Parent root = loader.load();
                    InscriptionController controller = loader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    
                }
            }

            // Fermer la fenêtre de connexion
            Stage loginStage = (Stage) login_form.getScene().getWindow();
            loginStage.close();
        } else {
            // Si les identifiants sont incorrects, afficher un message d'erreur
            showAlert("Nom d'utilisateur ou mot de passe incorrect.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }




public void signup() {
    connect = dbconnexion.getConnection();
    String sql = "INSERT INTO Utilisateur (nom, adresse_email, institution, username, pswrd, est_administrateur) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        if (validateFields(username1, password1)) {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username11.getText());
            prepare.setString(2, username12.getText());
            prepare.setString(3, username13.getText());
            prepare.setString(4, username1.getText());
            prepare.setString(5, password1.getText());
            // Par défaut, un nouvel utilisateur n'est pas un administrateur
            prepare.setBoolean(6, false);
            prepare.executeUpdate();
            
            // Afficher une alerte de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your account has been successfully created!");
            alert.showAndWait(); 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Fermer les ressources
        try {
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    }
    
    
    
    


 

