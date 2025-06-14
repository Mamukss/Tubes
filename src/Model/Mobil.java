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
    private String jenisMesin;

    public Mobil(String id, String nama, float harga, byte[] gambar, String jenisMesin) {
        super(id, nama, "Mobil", harga, gambar);
        this.jenisMesin = jenisMesin;
    }

    public String getJenisMesin() {
        return jenisMesin;
    }
    
    public void setJenisMesin(String jenisMesin) {
        this.jenisMesin = jenisMesin;
    }

    @Override
    public String toString(){
        return getNama();
    }

    @Override
    public String getSpecial() {
        return jenisMesin;
    }
}