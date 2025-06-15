package Model;

public class Karyawan { // OK
    private String id_karyawan;
    private String nama_karyawan;
    private String jabatan;
    private float gaji;
    private String username;
    private String password;

    public Karyawan(String id_karyawan, String nama_karyawan, String jabatan, float gaji, String username, String password) {
        this.id_karyawan = id_karyawan;
        this.nama_karyawan = nama_karyawan;
        this.jabatan = jabatan;
        this.gaji = gaji;
        this.username = username;
        this.password = password;
    } // konstruktor lengkap
    
    public Karyawan(String id_karyawan, String nama_karyawan, String jabatan, float gaji) {
        this.id_karyawan = id_karyawan;
        this.nama_karyawan = nama_karyawan;
        this.jabatan = jabatan;
        this.gaji = gaji;
    } // konstruktor tanpa user/pass
    
    // getter
    public String getId_karyawan() {
        return id_karyawan;
    }

    public String getNama_karyawan() {
        return nama_karyawan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public float getGaji() {
        return gaji;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    // setter
    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public void setNama_karyawan(String nama_karyawan) {
        this.nama_karyawan = nama_karyawan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setGaji(float gaji) {
        this.gaji = gaji;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return getNama_karyawan() ;
    } // toString untuk return nama karyawan
    
}