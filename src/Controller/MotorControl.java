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
import DAO.MotorDAO;
import java.util.List;
import Model.Motor;

public class MotorControl {
    private KendaraanDAO kendaraanDAO = new KendaraanDAO();
    private MotorDAO motorDAO = new MotorDAO();

    public void insertMotor(Motor m) {
        int idNumber = kendaraanDAO.generateId();
        String id = "R" + idNumber;
        m.setIdKendaraan(id);
        kendaraanDAO.insert(m);
        motorDAO.insert(m);
    }

    public void updateMotor(Motor m, String id) {
        kendaraanDAO.update(m, id);
        motorDAO.update(m, id);
    }

    public void deleteMotor(String id) {
        motorDAO.delete(id);
        kendaraanDAO.delete(id);
    }

    public List<Motor> getMotorList(String keyword) {
        return motorDAO.showData(keyword);
    }
}

