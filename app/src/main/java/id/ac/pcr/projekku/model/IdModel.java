package id.ac.pcr.projekku.model;

import java.util.List;

public class IdModel {
    private List<IdList> listid;

    public IdModel(List<IdList> listid) {
        this.listid = listid;
    }

    public List<IdList> getListid() {
        return listid;
    }

    public void setListid(List<IdList> listid) {
        this.listid = listid;
    }

    public class IdList {
        private String id_kelas, id_matkul, id_ruangan;

        public IdList(String id_kelas, String id_matkul, String id_ruangan) {
            this.id_kelas = id_kelas;
            this.id_matkul = id_matkul;
            this.id_ruangan = id_ruangan;
        }

        public String getId_kelas() {
            return id_kelas;
        }

        public void setId_kelas(String id_kelas) {
            this.id_kelas = id_kelas;
        }

        public String getId_matkul() {
            return id_matkul;
        }

        public void setId_matkul(String id_matkul) {
            this.id_matkul = id_matkul;
        }

        public String getId_ruangan() {
            return id_ruangan;
        }

        public void setId_ruangan(String id_ruangan) {
            this.id_ruangan = id_ruangan;
        }
    }
}
