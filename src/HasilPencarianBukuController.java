import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Kelas controller untuk tampilan hasil pencarian buku.
 * Mengelola komponen UI dan menangani logika untuk pencarian dan penampilan buku.
 */
public class HasilPencarianBukuController implements Initializable {

    @FXML
    private TextField txtCariJudul;
    @FXML
    private Hyperlink tombolKembali;
    @FXML
    private Label labelKataKunci;
    @FXML
    private ListView<String> listHasilPencarian;
    @FXML
    private ImageView searchButton;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dblibraree";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "orgazor914";

    private String lastSearchQuery;
    private List<String> lastSearchResults;

    /**
     * Inisialisasi kelas controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Kode inisialisasi dapat ditambahkan di sini jika diperlukan
    }

    /**
     * Mencari buku di database yang sesuai dengan judul yang diberikan.
     *
     * @param judul_buku Judul buku yang akan dicari.
     */
    public void searchBooks(String judul_buku) {
        lastSearchQuery = judul_buku;

        List<String> results = new ArrayList<>();
        String query = "SELECT judul_buku FROM tb_buku WHERE judul_buku LIKE ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + judul_buku + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                results.add(resultSet.getString("judul_buku"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        listHasilPencarian.getItems().clear();
        lastSearchResults = results;

        if (results.isEmpty()) {
            labelKataKunci.setText("Buku Tidak ditemukan... | '" + judul_buku + "'");
        } else {
            listHasilPencarian.getItems().setAll(results);
            labelKataKunci.setText(results.size() + " Jumlah Buku Ditemukan | '" + judul_buku + "'");
        }
    }

    /**
     * Menangani aksi kembali ke halaman utama.
     *
     * @param event Event yang dipicu oleh klik tombol.
     * @throws Exception Jika terjadi kesalahan saat mengganti scene.
     */
    @FXML
    private void returnToMainPageButtonAction(ActionEvent event) throws Exception {
        PindahScene.changeToScene(getClass(), event, "MainPage.fxml");
    }

    /**
     * Menampilkan detail buku yang dipilih.
     *
     * @param event Event yang dipicu oleh klik pada buku di daftar.
     */
    @FXML
    private void showBookDetail(MouseEvent event) {
        String selectedBook = listHasilPencarian.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                DetailBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "DetailBuku.fxml");
                if (controller != null) {
                    controller.setBookDetails(selectedBook);
                    controller.setPreviousFormState(lastSearchQuery, lastSearchResults);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Memulai pencarian buku ketika tombol pencarian diklik.
     *
     * @param event Event yang dipicu oleh klik tombol pencarian.
     * @throws Exception Jika terjadi kesalahan saat mengganti scene.
     */
    @FXML
    private void mencariBuku(MouseEvent event) throws Exception {
        String judul_buku = txtCariJudul.getText();

        if (judul_buku.isEmpty()) {
            showAlert("Pencarian Gagal!", "TextField is Empty...", "Silakan Masukan judul buku terlebih dahulu!");
            return;
        }

        try {
            HasilPencarianBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "HasilPencarianBuku.fxml");
            if (controller != null) {
                controller.searchBooks(judul_buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Memulai pencarian buku ketika tombol enter ditekan pada field pencarian.
     *
     * @param event Event yang dipicu oleh penekanan tombol enter.
     */
    @FXML
    void enterPressedOnTextField(ActionEvent event) {
        String judul_buku = txtCariJudul.getText();

        if (judul_buku.isEmpty()) {
            showAlert("Pencarian Gagal!", "TextField is Empty...", "Silakan Masukan judul buku terlebih dahulu!");
            return;
        }

        try {
            HasilPencarianBukuController controller = PindahScene.changeToSceneWithController(getClass(), event, "HasilPencarianBuku.fxml");
            if (controller != null) {
                controller.searchBooks(judul_buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mengatur status form sebelumnya, termasuk query pencarian dan hasil.
     *
     * @param query   Query pencarian terakhir.
     * @param results Hasil pencarian terakhir.
     */
    public void setPreviousFormState(String query, List<String> results) {
        this.lastSearchQuery = query;
        this.lastSearchResults = results;
        restoreFormState();
    }

    /**
     * Mengembalikan status form untuk mencerminkan query pencarian dan hasil sebelumnya.
     */
    private void restoreFormState() {
        if (lastSearchQuery != null) {
            txtCariJudul.setText(lastSearchQuery);
            listHasilPencarian.getItems().setAll(lastSearchResults);
            labelKataKunci.setText(lastSearchResults.size() + " Jumlah Buku Ditemukan | '" + lastSearchQuery + "'");
        }
    }

    /**
     * Menampilkan alert dengan judul, header, dan konten yang diberikan.
     *
     * @param title   Judul dari alert.
     * @param header  Header teks dari alert.
     * @param content Konten teks dari alert.
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}