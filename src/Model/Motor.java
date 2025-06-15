/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public class Motor extends Kendaraan {
   private String jumlah_tak;
    private byte[] gambar;
    
    public Motor(String jumlah_tak, String id_kendaraan, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(id_kendaraan, nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jumlah_tak = jumlah_tak;
    } //Konstruktor lengkap
    
    public Motor(String jumlah_tak, String nama_kendaraan, String jenis_kendaraan, float harga,  byte[] gambar) {
        super(nama_kendaraan, jenis_kendaraan, harga, gambar);
        this.jumlah_tak = jumlah_tak;
    } //Konstruktor tanpa id

    //getter
    public String getJumlah_tak() {
        return jumlah_tak;
    }
    
    //setter
    public void setJumlah_tak(String jumlah_tak) {
        this.jumlah_tak = jumlah_tak;
    }

    @Override
    public String getSpecial() {
        return jumlah_tak;
    }

    @Override
    public String toString() {
        return "Motor{" + "jumlah_tak=" + jumlah_tak + '}';
    }
   
}
