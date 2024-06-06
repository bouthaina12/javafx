
package projetjava;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

/**
 *
 * @author bouth
 */
public class Projetjava extends Application {
    
    @Override
    
    
    
    public void start(Stage stage) throws Exception {
         // Load the FXML file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dashbord.fxml"));
    Parent root = loader.load();
    // Get the controller associated with the FXML file
      Main_dashbordController controller = loader.getController();
    
        Scene scene = new Scene(root);
        // Show the signup form
        stage.setScene(scene);
        stage.show();
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
