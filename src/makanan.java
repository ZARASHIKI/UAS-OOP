import java.sql.SQLException;
import java.sql.Statement;

public class makanan {

    private String nama = null;
    private int id;
    private String harga = null;

    public makanan(int id, String nama, String harga) {
        this.nama = nama;
        this.id = id;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }

    public String getHarga() {
        return harga;
    }

    public boolean update(String nama, String harga) {
        Database db = new Database();
        try {
            Statement state = db.conn.createStatement();
            String sql = "update menu SET nama='%s', harga='%s' WHERE id=%d";
            sql = String.format(sql, nama, id, harga);
            state.execute(sql);
            db.conn.close();
            return true;
        } catch (SQLException e1) {
            return false;
        }
    }

    public boolean delete(int id) {
        Database db = new Database();
        try {
            Statement state = db.conn.createStatement();
            String sql = "delete from menu WHERE id=%d";
            sql = String.format(sql, id);
            state.execute(sql);
            db.conn.close();
            return true;
        } catch (SQLException e1) {
            return false;
        }
    }
}
