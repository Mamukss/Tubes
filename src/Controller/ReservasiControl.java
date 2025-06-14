/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.ReservasiDAO;
import java.util.List;
import Model.Reservasi;

public class ReservasiControl {
    private ReservasiDAO reservasiDAO = new ReservasiDAO();

    public void insertReservasi(Reservasi r) {
        reservasiDAO.insert(r);
    }

    public void updateReservasi(Reservasi r, String id) {
        reservasiDAO.update(r, id);
    }

    public void deleteReservasi(String id) {
        reservasiDAO.delete(id);
    }

    public List<Reservasi> getReservasiList() {
        return reservasiDAO.showDataList();
    }

    public List<Reservasi> searchReservasi(String keyword) {
        return reservasiDAO.showData(keyword);
    }
} 

