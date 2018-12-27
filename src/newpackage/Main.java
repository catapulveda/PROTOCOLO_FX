package newpackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Protocolo.fxml"));
            StackPane root = (StackPane) loader.load();            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("newpackage/protocolo.css");
            primaryStage.setTitle("CDM");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();           
        } catch (IOException e) {
            e.printStackTrace();
        }                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        launch(args);
    }
    
}
