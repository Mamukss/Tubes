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
import DAO.MobilDAO;
import java.util.List;
import Model.Mobil;

public class MobilControl {
    private KendaraanDAO kendaraanDAO = new KendaraanDAO();
    private MobilDAO motorDAO = new MobilDAO();

    public void insertMobil(Mobil m) {
        int idNumber = kendaraanDAO.generateId();
        String id = "M" + idNumber;
        m.setIdKendaraan(id);
        kendaraanDAO.insert(m);
        motorDAO.insert(m);
    }

    public void updateMobil(Mobil m, String id) {
        kendaraanDAO.update(m, id);
        motorDAO.update(m, id);
    }

    public void deleteMobil(String id) {
        motorDAO.delete(id);
        kendaraanDAO.delete(id);
    }

    public List<Mobil> getMobilList(String keyword) {
        return motorDAO.showData(keyword);
    }
}
