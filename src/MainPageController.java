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
import javafx.scene.input.KeyEvent;
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

    // Metode untuk mencari buku ketika tombol pencarian diklik
    @FXML
    private void mencariBuku(MouseEvent event) throws Exception {
        String judul_buku = txtCariJudul.getText();
        
        // Memeriksa apakah field judul_buku kosong
        if (judul_buku.isEmpty()){
            // Menampilkan pesan peringatan jika kosong
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pencarian Gagal!");
            alert.setHeaderText("TextField is Empty...");
            alert.setContentText("Silakan Masukan judul buku terlebih dahulu!");
            alert.showAndWait();
            return;
        }
        
        try {
            // Memanggil metode untuk berpindah ke halaman hasil pencarian buku
            HasilPencarianBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "HasilPencarianBuku.fxml");
            if (controller != null){
                controller.searchBooks(judul_buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode untuk mencari buku ketika tombol 'Enter' ditekan pada field judul buku
    @FXML
    void enterPressedOnTextField(ActionEvent event) {
        String judul_buku = txtCariJudul.getText();
        
        // Memeriksa apakah field judul_buku kosong
        if (judul_buku.isEmpty()){
            // Menampilkan pesan peringatan jika kosong
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pencarian Gagal!");
            alert.setHeaderText("TextField is Empty...");
            alert.setContentText("Silakan Masukan judul buku terlebih dahulu!");
            alert.showAndWait();
            return;
        }
        
        try {
            // Memanggil metode untuk berpindah ke halaman hasil pencarian buku
            HasilPencarianBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "HasilPencarianBuku.fxml");
            if (controller != null){
                controller.searchBooks(judul_buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Metode untuk menangani saat hyperlink 'Daftar Akun' ditekan
    @FXML
    private void mendaftarAkun(ActionEvent event) {
        // Menampilkan informasi bahwa fitur belum tersedia
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fitur Belum Tersedia");
            alert.setHeaderText("Versi Libraree saat ini: Libraree V0.1");
            alert.setContentText("Coming soon...?");
            alert.showAndWait();
            return;
    }
    
}
