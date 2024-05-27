/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Razor914
 */
public class MainPageController implements Initializable {

    @FXML
    private TextField txtCariJudul;
    @FXML
    private ImageView searchButton;
    @FXML
    private Hyperlink linkDaftarAkun;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mencariBuku(MouseEvent event) throws Exception {
        String judul_buku = txtCariJudul.getText();
        
        if (judul_buku.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pencarian Gagal!");
            alert.setHeaderText("TextField is Empty...");
            alert.setContentText("Silakan Masukan judul buku terlebih dahulu!");
            alert.showAndWait();
            return;
        }
        
        try {
            HasilPencarianBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "HasilPencarianBuku.fxml");
            if (controller != null){
                controller.searchBooks(judul_buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mendaftarAkun(ActionEvent event) {
    }
    
}
