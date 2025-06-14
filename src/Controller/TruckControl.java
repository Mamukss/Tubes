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
import DAO.TruckDAO;
import java.util.List;
import Model.Truck;

public class TruckControl {
    private KendaraanDAO kendaraanDAO = new KendaraanDAO();
    private TruckDAO motorDAO = new TruckDAO();

    public void insertTruck(Truck m) {
        int idNumber = kendaraanDAO.generateId();
        String id = "T" + idNumber;
        m.setIdKendaraan(id);
        kendaraanDAO.insert(m);
        motorDAO.insert(m);
    }

    public void updateTruck(Truck m, String id) {
        kendaraanDAO.update(m, id);
        motorDAO.update(m, id);
    }

    public void deleteTruck(String id) {
        motorDAO.delete(id);
        kendaraanDAO.delete(id);
    }

    public List<Truck> getTruckList(String keyword) {
        return motorDAO.showData(keyword);
    }
}
