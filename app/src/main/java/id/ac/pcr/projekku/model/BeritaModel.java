package id.ac.pcr.projekku.model;

public class BeritaModel {
    private int id_berita, status;
    private String headline, pengirim, penerima, isi;

    public int getId_berita() {
        return id_berita;
    }

    public void setId_berita(int id_berita) {
        this.id_berita = id_berita;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
