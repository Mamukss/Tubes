/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public class Mobil extends Kendaraan {
   private String jenis_mesin;
    private byte[] gambar;
    
    public Mobil(String jenis_mesin, String id_kendaraan, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(id_kendaraan, nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jenis_mesin = jenis_mesin;
    } //Konstruktor lengkap
    
    public Mobil(String jenis_mesin, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jenis_mesin = jenis_mesin;
    } //Konstruktor tanpa id

    //getter
    public String getJenis_mesin() {
        return jenis_mesin;
    }
    
    //setter
    public void setJenis_mesin(String jenis_mesin) {
        this.jenis_mesin = jenis_mesin;
    }

    @Override
    public String getSpecial() {
        return jenis_mesin;
    }

    @Override
    public String toString() {
        return "Mobil{" + "jenis_mesin=" + jenis_mesin + '}';
    }
}