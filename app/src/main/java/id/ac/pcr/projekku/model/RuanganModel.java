package id.ac.pcr.projekku.model;

import java.util.List;

public class RuanganModel {
    private List<Data> data;

    public RuanganModel(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {
        private String id_ruangan, nama_ruangan, keterangan;

        public Data(String id_ruangan, String nama_ruangan, String keterangan) {
            this.id_ruangan = id_ruangan;
            this.nama_ruangan = nama_ruangan;
            this.keterangan = keterangan;
        }

        public String getId_ruangan() {
            return id_ruangan;
        }

        public void setId_ruangan(String id_ruangan) {
            this.id_ruangan = id_ruangan;
        }

        public String getNama_ruangan() {
            return nama_ruangan;
        }

        public void setNama_ruangan(String nama_ruangan) {
            this.nama_ruangan = nama_ruangan;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }
    }
}
