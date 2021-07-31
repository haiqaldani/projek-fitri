package id.ac.pcr.projekku.model;

import java.util.List;

public class ScheduleModel {
    private List<College> jadwalkuliah;

    public ScheduleModel(List<College> jadwalkuliah) {
        this.jadwalkuliah = jadwalkuliah;
    }

    public List<College> getJadwalkuliah() {
        return jadwalkuliah;
    }

    public void setJadwalkuliah(List<College> jadwalkuliah) {
        this.jadwalkuliah = jadwalkuliah;
    }

    public class College {
        private String id, hari, kelas, semester, matkul, sks, dosen, ruangan, jam, tanggal;

        public College(String id, String hari, String kelas, String semester, String matkul, String sks, String dosen, String ruangan, String jam, String tanggal) {
            this.id = id;
            this.hari = hari;
            this.kelas = kelas;
            this.semester = semester;
            this.matkul = matkul;
            this.sks = sks;
            this.dosen = dosen;
            this.ruangan = ruangan;
            this.jam = jam;
            this.tanggal = tanggal;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHari() {
            return hari;
        }

        public void setHari(String hari) {
            this.hari = hari;
        }

        public String getKelas() {
            return kelas;
        }

        public void setKelas(String kelas) {
            this.kelas = kelas;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getMatkul() {
            return matkul;
        }

        public void setMatkul(String matkul) {
            this.matkul = matkul;
        }

        public String getSks() {
            return sks;
        }

        public void setSks(String sks) {
            this.sks = sks;
        }

        public String getDosen() {
            return dosen;
        }

        public void setDosen(String dosen) {
            this.dosen = dosen;
        }

        public String getRuangan() {
            return ruangan;
        }

        public void setRuangan(String ruangan) {
            this.ruangan = ruangan;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }
    }
}
