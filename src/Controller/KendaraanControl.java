/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.KendaraanDAO;
import java.util.List;
import Model.Kendaraan;
import Model.Mobil;
import Model.Motor;
import Model.Truck;

public class KendaraanControl {
    private KendaraanDAO kendaraanDAO = new KendaraanDAO();

    public void insertKendaraan(Kendaraan k) {
        kendaraanDAO.insert(k);
    }

    public void updateKendaraan(Kendaraan k, String id) {
        kendaraanDAO.update(k, id);
    }

    public void deleteKendaraan(String id) {
        kendaraanDAO.delete(id);
    }

    public List<Kendaraan> showKendaraan(String keyword) {
        return kendaraanDAO.showData(keyword);
    }

    public List<Kendaraan> showAllKendaraan() {
        return kendaraanDAO.showDataList();
    }

    public int generateId() {
        return kendaraanDAO.generateId();
    }

    // Optional: Factory methods jika ingin membuat objek dengan cepat
    public Mobil createMobil(String id, String nama, float harga, byte[] gambar, String jenisMesin) {
        return new Mobil(id, nama, harga, gambar, jenisMesin);
    }

    public Motor createMotor(String id, String nama, float harga, byte[] gambar, int jumlahTak) {
        return new Motor(id, nama, harga, gambar, jumlahTak);
    }

    public Truck createTruck(String id, String nama, float harga, byte[] gambar, String jenisRoda) {
        return new Truck(id, nama, harga, gambar, jenisRoda);
    }
} 
