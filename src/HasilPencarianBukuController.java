/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Razor914
 */
public class HasilPencarianBukuController implements Initializable {

    @FXML private TextField txtfieldjudulBuku;
    @FXML private Hyperlink tombolKembali;
    @FXML private Label labelKataKunci; 
    @FXML private ListView<String> listHasilPencarian;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dblibraree";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "orgazor914";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void searchBooks(String judul_buku){
        
        List<String> results = new ArrayList<>();
        String query = "select judul_buku from tb_buku where judul_buku like ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, "%" + judul_buku + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                results.add(resultSet.getString("judul_buku"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        listHasilPencarian.getItems().clear();
        if (results.isEmpty()){
            labelKataKunci.setText("Buku Tidak ditemukan..." + " | " + "'" + judul_buku + "'");
        } else{
            listHasilPencarian.getItems().setAll(results);
            labelKataKunci.setText(results.size() + " Jumlah Buku Ditemukan | '" + judul_buku + "'");
        }
    }

    @FXML
    private void returnToMainPageButtonAction(ActionEvent event) throws Exception {
        PindahScene.changeToScene (getClass(), event, "MainPage.fxml");
 
    }
    
    @FXML
    private void showBookDetail(ActionEvent event) throws Exception{
        String selectedBook = listHasilPencarian.getSelectionModel().getSelectedItem();
        if (selectedBook != null){
            DetailBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "DetailBuku.fxml");
            if(controller != null){
                controller.setBookDetails(selectedBook);
            }
        }
    }
    
}
