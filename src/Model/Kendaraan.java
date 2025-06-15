/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public abstract class Kendaraan {
private String id_kendaraan;
    private String nama_kendaraan;
    private String jenis_kendaraan;
    private float harga;
    private byte[] gambar;

    public Kendaraan(String id_kendaraan, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        this.id_kendaraan = id_kendaraan;
        this.nama_kendaraan = nama_kendaraan;
        this.jenis_kendaraan = jenis_kendaraan;
        this.harga = harga;
        this.gambar = gambar;
    } //Konstruktor lengkap

    public Kendaraan(String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        this.nama_kendaraan = nama_kendaraan;
        this.jenis_kendaraan = jenis_kendaraan;
        this.harga = harga;
        this.gambar = gambar;
    } //Konstukror tanpa id
    
    //getter and setter
    public String getId_kendaraan() {
        return id_kendaraan;
    }

    public void setId_kendaraan(String id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public String getNama_kendaraan() {
        return nama_kendaraan;
    }

    public void setNama_kendaraan(String nama_kendaraan) {
        this.nama_kendaraan = nama_kendaraan;
    }

    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }

    public void setJenis_kendaraan(String jenis_kendaraan) {
        this.jenis_kendaraan = jenis_kendaraan;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
    
    @Override
    public String toString(){
        return getNama_kendaraan();
    }
    
    public abstract String getSpecial();
}
