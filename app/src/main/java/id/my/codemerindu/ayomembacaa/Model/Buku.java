package id.my.codemerindu.ayomembacaa.Model;

public class Buku {

    private int id_buku;
    private String judul;
    private  String pengarang;
    private  String isi;
    private ItemType type;

    public enum ItemType {
        ONE_ITEM, TWO_ITEM;
    }

    public Buku (int id_buku,String judul, String pengarang, String isi) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.isi = isi;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getId_buku() {
        return id_buku;
    }

    public void setId_buku(int id_buku) {
        this.id_buku = id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
