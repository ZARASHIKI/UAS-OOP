import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {
    public TableView<makanan> tableView = new TableView<makanan>();

    Table() {
        TableColumn<makanan, String> columnID = new TableColumn<>("No");
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<makanan, String> columnNama = new TableColumn<>("Menu");
        columnNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));

        TableColumn<makanan, String> columnHarga = new TableColumn<>("Harga");
        columnHarga.setCellValueFactory(new PropertyValueFactory<>("Harga"));

        tableView.getColumns().add(columnID);
        tableView.getColumns().add(columnNama);
        tableView.getColumns().add(columnHarga);

        tableView.setRowFactory(tv -> {
            TableRow<makanan> row = new TableRow<makanan>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem edit = new MenuItem("Edit");
            MenuItem delete = new MenuItem("Delete");

            edit.setOnAction(e -> doEdit(row));
            delete.setOnAction(e -> doDelete(row));

            contextMenu.getItems().addAll(edit, delete);
            row.setContextMenu(contextMenu);
            System.out.println("test");
            return row;
        });
    }

    private Object doDelete(TableRow<makanan> row) {
        return null;
    }

    private Object doEdit(TableRow<makanan> row) {
        return null;
    }
}
