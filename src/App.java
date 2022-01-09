import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    TableView<makanan> tableView = new TableView<makanan>();

    @Override
    public void start(Stage primaryStage) throws SQLException {

        Table table = new Table();

        Button add = new Button("add pengunjung");

        add.setOnAction(e -> add());

        loadData();
        VBox vbox = new VBox(20, add, table.tableView);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void loadData() {
        Statement stmt;
        try {
            Database db = new Database();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from menu");
            tableView.getItems().clear();
            // tampilkan hasil query
            while (rs.next()) {
                tableView.getItems()
                        .add(new makanan(rs.getInt("Id"), rs.getString("Nama"), rs.getString("Harga")));
            }

            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doEdit(TableRow<makanan> item) {
        makanan menu = item.getItem();
        Stage editState = new Stage();
        Button save = new Button("simpan");

        editState.setTitle("edit data makanan");

        TextField namaField = new TextField();
        TextField hargaField = new TextField();
        Label labelNama = new Label("Menu");
        Label labelHarga = new Label("Harga");

        namaField.setText(menu.getNama());
        hargaField.setText(menu.getHarga());

        VBox hbox1 = new VBox(5, labelNama, namaField);
        VBox hbox2 = new VBox(5, labelHarga, hargaField);
        VBox vbox = new VBox(20, hbox1, hbox2, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            if (menu.update(namaField.getText(), hargaField.getText())) {
                loadData();
                editState.close();
            }
        });

        editState.setScene(scene);
        editState.show();
    }

    private void doDelete(TableRow<makanan> item) {
        makanan kamar = item.getItem();
        if (kamar.delete()) {
            loadData();
        }
    }

    private void add() {
        Stage addStage = new Stage();
        Button save = new Button("simpan");

        addStage.setTitle("add data kamar");

        TextField namaField = new TextField();
        TextField hargaField = new TextField();
        Label labelNama = new Label("Menu");
        Label labelharga = new Label("Harga");

        VBox hbox1 = new VBox(5, labelNama, namaField);
        VBox hbox2 = new VBox(5, labelharga, hargaField);
        VBox vbox = new VBox(20, hbox1, hbox2, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "insert into menu SET nama='%s', harga='%s'";
                sql = String.format(sql, namaField.getText(), hargaField.getText());
                state.execute(sql);
                loadData();
                addStage.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

}