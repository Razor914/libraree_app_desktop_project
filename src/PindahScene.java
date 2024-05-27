
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


public class PindahScene {
    
    // Cara menggunakan:
    // PindahScene.changeToScene (Class, event, "file.fxml");
    public static void changeToScene(Class aClass, Event aEvent, String sceneFileStr) throws Exception{
    Parent root = FXMLLoader.load (aClass.getResource(sceneFileStr));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) aEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
    }
    
    public static <T> T changeToSceneWithController(Class aClass, Event aEvent, String sceneFileStr) throws IOException{
        FXMLLoader loader = new FXMLLoader(aClass.getResource(sceneFileStr));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) aEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
        return loader.getController();
    }
    
}
