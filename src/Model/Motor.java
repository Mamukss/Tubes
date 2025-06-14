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
    private int jumlahTak;

    public Motor(String id, String nama, float harga, byte[] gambar, int jumlahTak) {
        super(id, nama, "Motor", harga, gambar);
        this.jumlahTak = jumlahTak;
    }


    @Override
    public String getSpecial() {
        return jumlahTak + " Tak";
    }

    public int getJumlahTak() {
        return jumlahTak;
    }

    public void setJumlahTak(int jumlahTak) {
        this.jumlahTak = jumlahTak;
    }

   
}
