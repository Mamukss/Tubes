/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public class Customer {
   private String id_customer; // SQL : id_customer
    private String nama_customer; // SQL : nama_customer
    private String alamat_customer; // SQL : alamat
    private String nomor_telepon; // SQL : nomor_telepon

    public Customer(String id_customer, String nama_customer, String alamat_customer, String nomor_telepon) {
        this.id_customer = id_customer;
        this.nama_customer = nama_customer;
        this.alamat_customer = alamat_customer;
        this.nomor_telepon = nomor_telepon;
    } // konstruktor lengkap

    public Customer(String nama_customer, String alamat_customer, String nomor_telepon) {
        this.nama_customer = nama_customer;
        this.alamat_customer = alamat_customer;
        this.nomor_telepon = nomor_telepon;
    } // konstruktor tanpa id_customer

    // Getter
    public String getId_customer() {
        return id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }
    
    // Setter
    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public void setAlamat_customer(String alamat_customer) {
        this.alamat_customer = alamat_customer;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    @Override
    public String toString() {
        return nama_customer + ' ';
    } // toString untuk menampilkan nama customer
    
    
}
