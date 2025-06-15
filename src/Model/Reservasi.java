/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;

public class Reservasi {
    private String id_reservasi; // SQL : id_reservasi
    private String id_customer; // SQL : id_customer
    private String id_karyawan;
    private String tanggal_reservasi; // SQL : tanggal_reservasi
    private String jenis_reservasi; // SQL : jenis_reservasi
    private String paket_reservasi; // SQL : paket_reservasi
    private float total_harga; // SQL : total_harga
    private Customer customer;
    
    
    public Reservasi(String id_reservasi, String id_customer, String tanggal_reservasi, String jenis_reservasi, String paket_reservasi, float total_harga, Customer customer) {
        this.id_reservasi = id_reservasi;
        this.id_customer = id_customer;
        this.tanggal_reservasi = tanggal_reservasi;
        this.jenis_reservasi = jenis_reservasi;
        this.paket_reservasi = paket_reservasi;
        this.total_harga = total_harga;
        this.customer = customer;
    } // konstruktor lengkap
    
    public Reservasi(String id_reservasi, String id_karyawan,String id_customer, String tanggal_reservasi, String jenis_reservasi, String paket_reservasi, float total_harga, Customer customer) {
        this.id_reservasi = id_reservasi;
        this.id_customer = id_customer;
        this.tanggal_reservasi = tanggal_reservasi;
        this.jenis_reservasi = jenis_reservasi;
        this.paket_reservasi = paket_reservasi;
        this.total_harga = total_harga;
        this.customer = customer;
    }
    public Reservasi(String id_customer, String tanggal_reservasi, String jenis_reservasi, String paket_reservasi, float total_harga, Customer customer) {
        this.id_customer = id_customer;
        this.tanggal_reservasi = tanggal_reservasi;
        this.jenis_reservasi = jenis_reservasi;
        this.paket_reservasi = paket_reservasi;
        this.total_harga = total_harga;
        this.customer = customer;
    } // konstruktor tanpa id_reservasi

    public Reservasi(String id_karyawan, String id_customer, String tanggal_reservasi, String jenis_reservasi, String paket_reservasi, float total_harga) {
        this.id_karyawan = id_karyawan;
        this.id_customer = id_customer;
        this.tanggal_reservasi = tanggal_reservasi;
        this.jenis_reservasi = jenis_reservasi;
        this.paket_reservasi = paket_reservasi;
        this.total_harga = total_harga;
    } 

    // getter
    public String getId_reservasi() {
        return id_reservasi;
    }

    public String getId_customer() {
        return id_customer;
    }
    
    public String getId_karyawan() {
        return id_karyawan;
    }
    
    public String getTanggal_reservasi() {
        return tanggal_reservasi;
    }

    public String getJenis_reservasi() {
        return jenis_reservasi;
    }

    public String getPaket_reservasi() {
        return paket_reservasi;
    }

    public float getTotal_harga() {
        return total_harga;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    // setter
    public void setId_reservasi(String id_reservasi) {
        this.id_reservasi = id_reservasi;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }
    
    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }
    
    public void setTanggal_reservasi(String tanggal_reservasi) {
        this.tanggal_reservasi = tanggal_reservasi;
    }

    public void setJenis_reservasi(String jenis_reservasi) {
        this.jenis_reservasi = jenis_reservasi;
    }

    public void setPaket_reservasi(String paket_reservasi) {
        this.paket_reservasi = paket_reservasi;
    }

    public void setTotal_harga(float total_harga) {
        this.total_harga = total_harga;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String toString() {
        return "Reservasi{" + "id_reservasi=" + id_reservasi + ", id_customer=" + id_customer + ", tanggal_reservasi=" + tanggal_reservasi + ", jenis_reservasi=" + jenis_reservasi + ", paket_reservasi=" + paket_reservasi + ", total_harga=" + total_harga + '}';
    } // toString menampilkan seluruh variabel
    
    
}


