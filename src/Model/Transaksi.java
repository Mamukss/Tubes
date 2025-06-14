/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class Transaksi {
    private String noResi;
    private String idKaryawan;
    private String idCustomer;
    private Date tanggalPesanan;
    private float totalHarga;

    public Transaksi(String noResi, String idKaryawan, String idCustomer, Date tanggalPesanan, float totalHarga) {
        this.noResi = noResi;
        this.idKaryawan = idKaryawan;
        this.idCustomer = idCustomer;
        this.tanggalPesanan = tanggalPesanan;
        this.totalHarga = totalHarga;
    }

    public String getNoResi() {
        return noResi;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setTanggalPesanan(Date tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }

    
}