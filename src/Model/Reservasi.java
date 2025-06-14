/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;

public class Reservasi {
    private String idReservasi;
    private String idKaryawan;
    private String idCustomer;
    private Date tanggalReservasi;
    private String jenisReservasi;
    private String paketReservasi;
    private float totalHarga;

    public Reservasi(String idReservasi, String idKaryawan, String idCustomer, Date tanggalReservasi,
                     String jenisReservasi, String paketReservasi, float totalHarga) {
        this.idReservasi = idReservasi;
        this.idKaryawan = idKaryawan;
        this.idCustomer = idCustomer;
        this.tanggalReservasi = tanggalReservasi;
        this.jenisReservasi = jenisReservasi;
        this.paketReservasi = paketReservasi;
        this.totalHarga = totalHarga;
    }

    public String getIdReservasi() {
        return idReservasi;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public Date getTanggalReservasi() {
        return tanggalReservasi;
    }

    public String getJenisReservasi() {
        return jenisReservasi;
    }

    public String getPaketReservasi() {
        return paketReservasi;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public void setIdReservasi(String idReservasi) {
        this.idReservasi = idReservasi;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setTanggalReservasi(Date tanggalReservasi) {
        this.tanggalReservasi = tanggalReservasi;
    }

    public void setJenisReservasi(String jenisReservasi) {
        this.jenisReservasi = jenisReservasi;
    }

    public void setPaketReservasi(String paketReservasi) {
        this.paketReservasi = paketReservasi;
    }

    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }

    
}

