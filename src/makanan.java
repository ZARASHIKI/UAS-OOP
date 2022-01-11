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
}
