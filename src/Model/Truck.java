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
    private String tipeRoda;

    public Truck(String id, String nama, float harga, byte[] gambar, String tipeRoda) {
        super(id, nama, "Truck", harga, gambar);
        this.tipeRoda = tipeRoda;
    }

    public String getJenisRoda() {
        return tipeRoda;
    }

    public void setJumlahBerat(String tipeRoda) {
        this.tipeRoda = tipeRoda;
    }

    @Override
    public String getSpecial() {
        return tipeRoda;
    }

    
}