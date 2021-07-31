package id.ac.pcr.projekku.model;

public class UserModel {
    private String email, id_pengguna, status, message;

    public UserModel(String email, String id_pengguna, String status, String message) {
        this.email = email;
        this.id_pengguna = id_pengguna;
        this.status = status;
        this.message = message;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return status;
    }

    public void setLevel(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
