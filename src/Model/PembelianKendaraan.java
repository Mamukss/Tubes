/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */

import java.util.ArrayList;
import java.util.List;
import Table.TabelPembelianKendaraan;


public class PembelianKendaraan {
    private String id_pembelian;
    private String id_customer;
    private String id_kendaraan;
    private int jumlah;
    private String tambahan;
    private String metodePembayaran;
    private float subTotal;
    private String namaKendaraan;
    private String jenisKendaraan;
    private Kendaraan kendaraan;

    public PembelianKendaraan(String id_pembelian, String id_customer, String id_kendaraan, int jumlah,
                               String tambahan, String metodePembayaran, float subTotal, Kendaraan kendaraan) {
        this.id_pembelian = id_pembelian;
        this.id_customer = id_customer;
        this.id_kendaraan = id_kendaraan;
        this.jumlah = jumlah;
        this.tambahan = tambahan;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
        this.kendaraan = kendaraan;
    }
    
    public PembelianKendaraan(String id_pembelian, String id_customer, String id_kendaraan, int jumlah,
                               String tambahan, String metodePembayaran, float subTotal) {
        this.id_pembelian = id_pembelian;
        this.id_customer = id_customer;
        this.id_kendaraan = id_kendaraan;
        this.jumlah = jumlah;
        this.tambahan = tambahan;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
    }
        
            
    public PembelianKendaraan(String id_pembelian, String id_customer, String namaKendaraan, String jenisKendaraan, String id_kendaraan, int jumlah,
                               String tambahan, String metodePembayaran, float subTotal) {
        this.id_pembelian = id_pembelian;
        this.id_customer = id_customer;
        this.id_kendaraan = id_kendaraan;
        this.jumlah = jumlah;
        this.tambahan = tambahan;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        
    }
    
      public PembelianKendaraan(String id_customer, String id_kendaraan, int jumlah,
                               String tambahan, String metodePembayaran, float subTotal) {
        this.id_customer = id_customer;
        this.id_kendaraan = id_kendaraan;
        this.jumlah = jumlah;
        this.tambahan = tambahan;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
    } // konstruktor tanpa id_pembelian
    
    

    public void setid_kendaraan(String id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public void setIdKendaraan(String id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
    
    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public String getId_pembelian() {
        return id_pembelian;
    }

    public String getId_customer() {
        return id_customer;
    }

    public String getId_kendaraan() {
        return id_kendaraan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getTambahan() {
        return tambahan;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public float getSubTotal() {
        return subTotal;
    }
    
    public Kendaraan getKendaraan(){
        return kendaraan;
    }
    public String getNamaKendaraan(){
        return namaKendaraan;
    }
    
    @Override
    public String toString() {
        return "PembelianKendaraan{" + "id_pembelian=" + id_pembelian + ", id_kendaraan=" + id_kendaraan + ", jumlah=" + jumlah + ", subTotal=" + subTotal + '}';
    } // toString menampilkan seluruh variabel
    
    public TabelPembelianKendaraan showTable(List<PembelianKendaraan> p) {
        TabelPembelianKendaraan tabelPembelianKendaraan = new TabelPembelianKendaraan(p);
        return tabelPembelianKendaraan;
    }
    
    
}
