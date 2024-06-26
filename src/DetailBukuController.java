/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Razor914
 */
public class DetailBukuController implements Initializable {

    @FXML private ImageView imgBuku;
    @FXML private Label labelJudulBuku;
    @FXML private Label labelLokasiBuku;
    @FXML private TextField txtCariJudul;
    @FXML private ImageView searchButton;
    @FXML private Hyperlink tombolKembali;
    @FXML private Hyperlink linkDaftarAkun;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dblibraree";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "orgazor914";
    
    private String previousSearchQuery;
    private List<String> previousSearchResults;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

public void setBookDetails(String judulBuku){
        String query = "select * from tb_buku where judul_buku = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)){
            
             statement.setString(1, judulBuku);
             ResultSet resultSet = statement.executeQuery();
             if(resultSet.next()){
                 labelJudulBuku.setText(resultSet.getString("judul_buku"));
                 String imagePath = resultSet.getString("path_image");
                 if (imagePath != null && !imagePath.isEmpty()){
                     Image image =  new Image(imagePath);
                     imgBuku.setImage(image);
                 } else {
                     imgBuku.setImage(null);
                 }
                 labelLokasiBuku.setText(resultSet.getString("lokasi_buku"));
             } else {
                 System.out.println("Tidak ada hasil untuk judul buku: " + judulBuku);
             } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPreviousFormState(String query, List<String> results){
        this.previousSearchQuery = query;
        this.previousSearchResults = results;
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
    void enterPressedOnTextField(ActionEvent event) {
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
    private void backToPreviousPage(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HasilPencarianBuku.fxml"));
        Parent root = loader.load();
        
        HasilPencarianBukuController controller = loader.getController();
        controller.setPreviousFormState(previousSearchQuery, previousSearchResults);
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
    }
    
    @FXML
    void mendaftarAkun(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fitur Belum Tersedia");
            alert.setHeaderText("Versi Libraree saat ini: Libraree V0.1");
            alert.setContentText("Coming soon...?");
            alert.showAndWait();
            return;
    }
    
}
