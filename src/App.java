import javafx.application.Application;
import javafx.scene.Scene;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.scene.control.*;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("UAS OOP");

        TableView<makanan> tableView = new TableView<makanan>();

        TableColumn<makanan, String> columnID = new TableColumn<>("No");
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<makanan, String> columnNama = new TableColumn<>("Menu");
        columnNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));

        TableColumn<makanan, String> columnHarga = new TableColumn<>("Harga");
        columnHarga.setCellValueFactory(new PropertyValueFactory<>("Harga"));

        tableView.getColumns().add(columnID);
        tableView.getColumns().add(columnNama);
        tableView.getColumns().add(columnHarga);

        ToolBar toolBar = new ToolBar();

        Button button1 = new Button("Add Menu");
        toolBar.getItems().add(button1);
        button1.setOnAction(e -> add());

        Button button2 = new Button("Delete ");
        toolBar.getItems().add(button2);
        button2.setOnAction(e -> add());

        VBox vbox = new VBox(tableView, toolBar);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();

        Statement stmt;
        try {
            Database db = new Database();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from menu");
            tableView.getItems().clear();
            // tampilkan hasil query
            while (rs.next()) {
                tableView.getItems().add(new makanan(rs.getInt("Id"), rs.getString("Nama"), rs.getString("Harga")));
            }

            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        Stage addStage = new Stage();
        Button save = new Button("simpan");

        addStage.setTitle("add data Menu");

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
