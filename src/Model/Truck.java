/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public class Truck extends Kendaraan {
   private String jenis_roda;
    private byte[] gambar;
    
    public Truck(String jenis_roda, String id_kendaraan, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(id_kendaraan, nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jenis_roda = jenis_roda;
    } //Konstruktor lengkap
    
    public Truck(String jenis_roda, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jenis_roda = jenis_roda;
    } //Konstruktor tanpa id

    //getter
    public String getJenis_roda() {
        return jenis_roda;
    }
    
    //setter
    public void setJenis_roda(String jenis_roda) {
        this.jenis_roda = jenis_roda;
    }

    @Override
    public String getSpecial() {
        return jenis_roda;
    }

    @Override
    public String toString() {
        return "Truck{" + "jenis_roda=" + jenis_roda + '}';
    }
}