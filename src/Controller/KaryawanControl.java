/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.KaryawanDAO;
import java.util.List;
import Model.Karyawan;

public class KaryawanControl {
    private KaryawanDAO karyawanDAO = new KaryawanDAO();

    public void insertKaryawan(Karyawan k) {
        karyawanDAO.insert(k);
    }

    public void updateKaryawan(Karyawan k, String id) {
        karyawanDAO.update(k, id);
    }

    public void deleteKaryawan(String id) {
        karyawanDAO.delete(id);
    }

    public List<Karyawan> getKaryawanList() {
        return karyawanDAO.showDataList();
    }

    public List<Karyawan> searchKaryawan(String keyword) {
        return karyawanDAO.showData(keyword);
    }

    public Karyawan searchExact(String keyword) {
        return karyawanDAO.search(keyword);
    }

    public int generateId() {
        return karyawanDAO.generateId();
    }
} 