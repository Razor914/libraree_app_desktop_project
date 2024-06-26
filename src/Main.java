import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    // Metode start() yang dioverride dari kelas Application untuk memulai aplikasi
    @Override
    public void start(Stage stage) throws Exception {
        // Memuat root (akar) dari MainPage.fxml menggunakan FXMLLoader
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        // Membuat objek Scene dengan root yang dimuat
        Scene scene = new Scene(root);
        
        // Mengatur scene ke dalam stage (panggung)
        stage.setScene(scene);
        // Mengatur judul jendela aplikasi
        stage.setTitle("Libraree");
        
        // Mengatur mode layar penuh
        stage.setFullScreen(true);
        // Mengatur hint untuk keluar dari mode layar penuh (dalam hal ini, tidak ada pesan)
        stage.setFullScreenExitHint("");
        // Menampilkan stage
        stage.show();  
    }
    
    // Metode main() yang merupakan titik masuk utama aplikasi
    public static void main(String[] args) {
        // Memulai aplikasi JavaFX
        launch(args);
    }
}