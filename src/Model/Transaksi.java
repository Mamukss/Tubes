/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import Model.Karyawan;

public class Transaksi {
    private String id_pembelian;      // SQL : id_pembelian;
    private String id_karyawan;     // SQL : id_karyawan;
    private String id_customer;    // SQL : id_customer;
    private String tanggal_pesanan; // SQL : tanggal_pesanan;
    private float total_harga;      // SQL : total_harga;
    private Customer customer;
    private Karyawan karyawan;

    public Transaksi(String id_pembelian, String id_karyawan, String id_customer, String tanggal_pesanan, float total_harga) {
        this.id_pembelian = id_pembelian;
        this.id_karyawan = id_karyawan;
        this.id_customer = id_customer;
        this.tanggal_pesanan = tanggal_pesanan;
        this.total_harga = total_harga;
    } // konstruktor lengkap

    public Transaksi(String id_karyawan, String id_customer, String tanggal_pesanan, float total_harga) {
        this.id_karyawan = id_karyawan;
        this.id_customer = id_customer;
        this.tanggal_pesanan = tanggal_pesanan;
        this.total_harga = total_harga;
    } // konstuktor tanpa id_pembelian

    public Transaksi(String id_customer, String tanggal_pesanan, float total_harga) {
        this.id_customer = id_customer;
        this.tanggal_pesanan = tanggal_pesanan;
        this.total_harga = total_harga;
    } // konstruktor tanpa id_pembelian dan id_karyawan

    public Transaksi(String id_pembelian, String id_karyawan, String id_customer, String tanggal_pesanan, float total_harga, 
            Karyawan k, Customer c) {
        this.id_pembelian = id_pembelian;
        this.id_karyawan = id_karyawan;
        this.id_customer = id_customer;
        this.tanggal_pesanan = tanggal_pesanan;
        this.total_harga = total_harga;
        this.karyawan = k;
        this.customer = c;     
    } // konstruktor untuk show Table

    // getter
    public String getId_pembelian() {
        return id_pembelian;
    }

    public String getId_karyawan() {
        return id_karyawan;
    }

    public String getId_customer() {
        return id_customer;
    }

    public String getTanggal_pesanan() {
        return tanggal_pesanan;
    }

    public float getTotal_harga() {
        return total_harga;
    }
    
    public Karyawan getKaryawan(){
        return karyawan;
    }
    
    public Customer getCustomer(){
        return customer;
    }

    // setter
    public void setId_pembelian(String id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public void setTanggal_pesanan(String tanggal_pesanan) {
        this.tanggal_pesanan = tanggal_pesanan;
    }

    public void setTotal_harga(float total_harga) {
        this.total_harga = total_harga;
    }

    @Override
    public String toString() {
        return "Transaksi{" + "id_pembelian=" + id_pembelian + ", id_karyawan=" + id_karyawan + ", id_customer=" + id_customer + ", tanggal_pesanan=" + tanggal_pesanan + ", total_harga=" + total_harga + '}';
    }
    
}
    
    