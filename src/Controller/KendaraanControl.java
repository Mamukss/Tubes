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
import interface_Control.ICRUDControl;
import java.util.List;
import Model.Kendaraan;

public abstract class KendaraanControl <T extends Kendaraan> implements ICRUDControl<T, String>{
    protected KendaraanDAO kDao;
    
    public KendaraanControl(KendaraanDAO KDao) {
        this.kDao = KDao;
    }
    
    @Override
    public void insert(T kendaraan) {
        kendaraan.setId_kendaraan(generateId());
        kDao.insert(kendaraan);
    }

    @Override
    public void update(T kendaraan) {
        kDao.update(kendaraan, kendaraan.getId_kendaraan());
    }

    @Override
    public void delete(String id) {
        kDao.delete(id);
    }

    @Override
    public String generateId() {
        return "K" + kDao.generateId();
    }

    // Metode abstrak untuk memastikan jenis menu yang tepat
    protected abstract boolean cekJenis(Kendaraan kendaraan);

    // Metode abstrak untuk mengembalikan daftar menu yang sesuai
    public abstract List<T> showListKendaraan();

    // Metode untuk mencari harga berdasarkan id menu
    public float searchHarga(String id_kendaraan) {
        return kDao.searchHarga(id_kendaraan);
    }
} 
