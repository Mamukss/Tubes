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
    protected String idKendaraan;
    protected String nama;
    protected String jenis;
    protected float harga;
    protected byte[] gambar; // untuk menyimpan BLOB

    public Kendaraan(String idKendaraan, String nama, String jenis, float harga, byte[] gambar) {
        this.idKendaraan = idKendaraan;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public float getHarga() {
        return harga;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    @Override
    public String toString(){
        return getNama();
    }
    public abstract String getSpecial();
}
