/*
»”„ «··Â «·—Õ„‰ «·—ÕÌ„
CPCS-391 Final Group Project
==============BreakOut Video Game=================
Students names: 
1- Ammar Joharji (1742559)
2- Mohammed Algafili (1741679)
3- Abdulrahman Alharithi (1741096)
Note: PLEASE launch the game from the MainMenuScreen.java!
*/
package ui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.yourorghere.Main;

public class MainMenuController implements Initializable {
    @FXML private Button startButton;
    @FXML private Button aboutButton;
    @FXML private Button exitButton;
    
    @FXML
    public void startAction(ActionEvent event){
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.close();
        Main.init();
    }
    
    @FXML
    public void exitAction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    public void aboutAction(ActionEvent event) throws IOException{
        loadAbout(event);
    }
    
    private void loadAbout(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/about.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
